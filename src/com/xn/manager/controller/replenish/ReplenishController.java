package com.xn.manager.controller.replenish;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.constant.CachedKeyUtils;
import com.xn.common.util.constant.DyCacheKey;
import com.xn.manager.method.PublicMethod;
import com.xn.manager.model.inorder.InOrderModel;
import com.xn.manager.model.replenish.ReplenishModel;
import com.xn.manager.service.InOrderService;
import com.xn.manager.service.RedisIdService;
import com.xn.manager.service.ReplenishService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 补单的Controller层
 * @Author yoko
 * @Date 2020/8/6 10:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/replenish")
public class ReplenishController extends BaseController {

    private static Logger log = Logger.getLogger(ReplenishController.class);

    @Autowired
    private RedisIdService redisIdService;

    @Autowired
    private ReplenishService<ReplenishModel> replenishService;

    @Autowired
    private InOrderService<InOrderModel> inOrderService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/replenish/replenishIndex";
    }



    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, InOrderModel model) throws Exception {
        List<InOrderModel> dataList = new ArrayList<InOrderModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            InOrderModel dataModel = new InOrderModel();
            dataModel = inOrderService.queryByCondition(model);
//            if (dataModel != null && dataModel.getId() > 0){
//                if (dataModel.getOrderStatus() == 4){
////                    sendFailureMessage(response, "订单是成功的,无需进行补单操作!~");
////                    return;
//                    HtmlUtil.writerJson(response, model.getPage(), dataList);
//                    return;
//                }
//            }else {
////                sendFailureMessage(response, "没找到对应的订单信息,核实!~");
//                HtmlUtil.writerJson(response, model.getPage(), dataList);
//                return;
//            }
//
//            // check查询这个单子是否已被补单过：怕重复补单
//            ReplenishModel replenishModel = new ReplenishModel();
//            if (!StringUtils.isBlank(model.getMyTradeNo())){
//                replenishModel.setMyTradeNo(model.getMyTradeNo());
//            }
//            if (!StringUtils.isBlank(model.getOutTradeNo())){
//                replenishModel.setOutTradeNo(model.getOutTradeNo());
//            }
//            replenishModel = replenishService.queryByCondition(replenishModel);
//            if (replenishModel != null && replenishModel.getId() > 0){
//               HtmlUtil.writerJson(response, model.getPage(), dataList);
//               return;
//            }
            if (dataModel != null && dataModel.getId() > 0){
                dataList.add(dataModel);
            }

        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }



    /**
     * @Description: 获取提现详情
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getId")
    public void getId(Long id, HttpServletResponse response) throws Exception {
        InOrderModel query = new InOrderModel();
        query.setId(id);
        InOrderModel bean = inOrderService.queryById(query);
        if (bean == null) {
            sendFailureMessage(response, "没有找到对应的记录!");
            return;
        }
        sendSuccessMessage(response, "", bean);
    }




    /**
     * 补单
     */
    @RequestMapping("/check")
    public void check(HttpServletRequest request, HttpServletResponse response, ReplenishModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                sendFailureMessage(response, "您无权补单订单!");
                return;
            }

            if (bean.getIsProfit() == 0){
                sendFailureMessage(response, "请选择是否计算收益!");
                return;
            }


            InOrderModel dataModel = new InOrderModel();
            if (bean.getId() > 0){
                dataModel.setId(bean.getId());
            }
            if (!StringUtils.isBlank(bean.getMyTradeNo())){
                dataModel.setMyTradeNo(bean.getMyTradeNo());
            }
            if (!StringUtils.isBlank(bean.getOutTradeNo())){
                dataModel.setOutTradeNo(bean.getOutTradeNo());
            }
            dataModel = inOrderService.queryByCondition(dataModel);
            if (dataModel != null && dataModel.getId() > 0){
                if (dataModel.getOrderStatus() == 4){
                    sendFailureMessage(response, "订单是成功的,无需进行补单操作!~");
                    return;
                }
            }else {
                sendFailureMessage(response, "没找到对应的订单信息,核实!~");
                log.info("");
                return;
            }



            // check查询这个单子是否已被补单过：怕重复补单
            ReplenishModel replenishModel = new ReplenishModel();
            if (!StringUtils.isBlank(dataModel.getMyTradeNo())){
                replenishModel.setMyTradeNo(dataModel.getMyTradeNo());
            }
            if (!StringUtils.isBlank(dataModel.getOutTradeNo())){
                replenishModel.setOutTradeNo(dataModel.getOutTradeNo());
            }
            replenishModel = replenishService.queryByCondition(replenishModel);
            if (replenishModel != null && replenishModel.getId() > 0){
                sendFailureMessage(response, "此订单之前已补单过,无需重复补单操作!~");
                return;
            }

            // redis锁住此补单的单号：主要不让这条数据短时间内重复点击
            String lockKey = CachedKeyUtils.getDeployCacheKey(DyCacheKey.REPLENISH, dataModel.getMyTradeNo(), dataModel.getOutTradeNo());
            boolean flagLock = redisIdService.lock(lockKey);
            if (!flagLock){
                sendFailureMessage(response, "操作过于频繁,请稍后再试!");
                return;
            }

            ReplenishModel replenishAdd = PublicMethod.assembleReplenishAdd(dataModel, bean.getIsProfit(), bean.getRemark());
            replenishService.add(replenishAdd);

            sendSuccessMessage(response, "保存成功~");
            return;
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }



}
