package com.xn.manager.controller.channel;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.GoogleAuthenticator;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.StringUtil;
import com.xn.common.util.constant.CachedKeyUtils;
import com.xn.common.util.constant.DyCacheKey;
import com.xn.manager.method.PublicMethod;
import com.xn.manager.model.channel.ChannelBalanceDeductModel;
import com.xn.manager.model.channel.ChannelBankModel;
import com.xn.manager.model.channel.ChannelModel;
import com.xn.manager.model.channel.ChannelWithdrawModel;
import com.xn.manager.service.*;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName:
 * @Description: 渠道-提现信息
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/channelwithdraw")
public class ChannelWithdrawController extends BaseController {

    private static Logger log = Logger.getLogger(ChannelWithdrawController.class);

    @Autowired
    private RedisIdService redisIdService;

    @Autowired
    private ChannelWithdrawService<ChannelWithdrawModel> channelWithdrawService;

    @Autowired
    private ChannelService<ChannelModel> channelService;

    @Autowired
    private ChannelBankService<ChannelBankModel> channelBankService;

    @Autowired
    private ChannelBalanceDeductService<ChannelBalanceDeductModel> channelBalanceDeductService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/channelwithdraw/channelwithdrawIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelWithdrawModel model) throws Exception {
        List<ChannelWithdrawModel> dataList = new ArrayList<ChannelWithdrawModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，只能查询自己的数据
                model.setChannelId(account.getId());
            }
            dataList = channelWithdrawService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
//        model.addAttribute("rloeMenu", roleService.queryList());
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                sendFailureMessage(response,"管理员无法提现!");
            }else {
                if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                    ChannelModel channelModel = new ChannelModel();
                    channelModel.setId(account.getId());
                    model.addAttribute("tp", channelService.queryById(channelModel));
                }

                ChannelBankModel channelBankModel = new ChannelBankModel();
                channelBankModel.setChannelId(account.getId());
                channelBankModel.setIsEnable(2);
                model.addAttribute("bank", channelBankService.queryAllList(channelBankModel));
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/channelwithdraw/channelwithdrawAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ChannelWithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                sendFailureMessage(response,"管理员无法提现!");
                return;
            }
            if (StringUtils.isBlank(bean.getMoney()) || StringUtils.isBlank(bean.getServiceCharge())){
                sendFailureMessage(response,"请填写提现金额!");
                return;
            }
            if (bean.getMoney().indexOf("-") > -1 || bean.getServiceCharge().indexOf("-") > -1){
                sendFailureMessage(response,"错误,请重试!");
                return;
            }
            if (StringUtils.isBlank(bean.getServiceCharge())){
                sendFailureMessage(response,"错误,请重试!");
                return;
            }
            boolean flag_serviceCharge = false;
//            if (bean.getServiceCharge().equals("2")){
            if (bean.getServiceCharge().equals("3")){
                flag_serviceCharge = true;
            }
            if (bean.getServiceCharge().equals("5")){
                flag_serviceCharge = true;
            }
            if (!flag_serviceCharge){
                sendFailureMessage(response,"错误,请重试!");
                return;
            }
            int bankType = 0;// 使用银行的地方：1是通过下拉框选择的，2是直接填写的
            if (bean.getChannelBankId() > 0 && !StringUtils.isBlank(bean.getBankName()) && !StringUtils.isBlank(bean.getAccountName())
                    && !StringUtils.isBlank(bean.getBankCard())){
                sendFailureMessage(response,"选择了下拉框的银行则无需填写临时银行信息,两者只需要选择一种!");
                return;
            }
            if (bean.getChannelBankId() <= 0 && StringUtils.isBlank(bean.getBankName())){
                sendFailureMessage(response,"请选择或者填写要提现的银行信息!");
                return;
            }
            if (bean.getChannelBankId() > 0 && StringUtils.isBlank(bean.getBankName()) && StringUtils.isBlank(bean.getAccountName())
                    && StringUtils.isBlank(bean.getBankCard())){
                bankType = 1;
            }
            if (bean.getChannelBankId() <= 0 && !StringUtils.isBlank(bean.getBankName()) && !StringUtils.isBlank(bean.getAccountName())
                    && !StringUtils.isBlank(bean.getBankCard())){
                bankType = 2;
            }



            if (StringUtils.isBlank(bean.getWithdrawPassWd())){
                sendFailureMessage(response,"请输入提现密码!");
                return;
            }


            boolean flag = false;
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                if (bankType == 1){
                    // 查询渠道银行信息
                    ChannelBankModel channelBankModel = new ChannelBankModel();
                    channelBankModel.setId(bean.getChannelBankId());
                    channelBankModel = channelBankService.queryByCondition(channelBankModel);
                    if (channelBankModel == null || channelBankModel.getId() <= 0){
                        sendFailureMessage(response,"没有相关银行信息,请重新选择要提现的银行!");
                        return;
                    }

                    // 银行信息赋值
                    if (channelBankModel != null && channelBankModel.getId() > 0){
                        bean.setBankName(channelBankModel.getBankName());
                        if (!StringUtils.isBlank(channelBankModel.getSubbranchName())){
                            bean.setSubbranchName(channelBankModel.getSubbranchName());
                        }
                        bean.setAccountName(channelBankModel.getAccountName());
                        bean.setBankCard(channelBankModel.getBankCard());
                    }
                }

                ChannelModel channelModel = new ChannelModel();
                channelModel.setId(account.getId());
                channelModel = channelService.queryById(channelModel);
                if (!StringUtils.isBlank(channelModel.getWithdrawPassWd())){
                    if (!channelModel.getWithdrawPassWd().equals(bean.getWithdrawPassWd())){
                        sendFailureMessage(response,"提现密码错误,请您重新输入!");
                        return;
                    }
                }
                // 判断是否需要进行谷歌校验
                if (channelModel.getIsGoogle() == 2){
                    if (StringUtils.isBlank(bean.getGoogleCode())){
                        sendFailureMessage(response,"请您填写谷歌验证码!");
                        return;
                    }else {
                        if (!StringUtils.isBlank(channelModel.getGoogleKey())){
                            boolean check_google = GoogleAuthenticator.authGooleCode(channelModel.getGoogleKey(), bean.getGoogleCode());
                            if (!check_google){
                                sendFailureMessage(response,"请您的谷歌验证码填写有误,请重新填写!");
                                return;
                            }
                        }
                    }
                }
                String totalMoney = StringUtil.getBigDecimalAdd(bean.getMoney(), bean.getServiceCharge());
                flag = StringUtil.getBigDecimalSubtract(channelModel.getBalance(), totalMoney);

                if (flag){
                    // redis锁住此渠道的主键ID：主要不让渠道短时间内重复点击
                    String lockKey = CachedKeyUtils.getDeployCacheKey(DyCacheKey.CHANNEL_WITHDRAW, account.getId());
                    boolean flagLock = redisIdService.lock(lockKey);
                    if (flagLock){
                        bean.setChannelId(account.getId());
                        String orderNo = "PQDTX" + DateUtil.getNowPlusTimeMill();
                        bean.setOrderNo(orderNo);
                        bean.setCurday(DateUtil.getDayNumber(new Date()));
                        // 添加提现纪录
                        channelWithdrawService.add(bean);

                        // 提现金额 + 手续费
                        String money = StringUtil.getBigDecimalAdd(bean.getMoney(), bean.getServiceCharge());
                        ChannelBalanceDeductModel channelBalanceDeductModel = PublicMethod.assembleChannelBalanceDeductAdd(bean.getChannelId(), orderNo,
                                money, 1,1,null,null);
                        // 添加渠道扣减余额流水
                        channelBalanceDeductService.add(channelBalanceDeductModel);
                        sendSuccessMessage(response, "保存成功~");
                        return;
                    }else{
                        sendFailureMessage(response,"频繁点击,请过20秒后在重试!");
                        return;
                    }


                }else {
                    sendFailureMessage(response,"提现金额超出余额!");
                    return;
                }
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
            return;
        }
    }

}
