package com.xn.manager.controller.inorder;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HttpSendUtils;
import com.xn.common.util.MD5Util;
import com.xn.common.util.StringUtil;
import com.xn.manager.model.PrGewayCodeModel;
import com.xn.manager.model.PrPlatformGewayCodeModel;
import com.xn.manager.model.channel.ChannelModel;
import com.xn.manager.model.inorder.InOrderPullModel;
import com.xn.manager.service.ChannelService;
import com.xn.manager.service.PrGewayCodeService;
import com.xn.manager.service.PrPlatformGewayCodeService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:
 * @Description: 代收拉单的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/inorderpull")
public class InOrderPullController extends BaseController {
    private static Logger log = Logger.getLogger(InOrderPullController.class);


    @Autowired
    private ChannelService<ChannelModel> channelService;

    @Autowired
    private PrPlatformGewayCodeService<PrPlatformGewayCodeModel> prPlatformGewayCodeService;

    @Autowired
    private PrGewayCodeService<PrGewayCodeModel> prGewayCodeService;

    public static String interfaceAds_POST = "http://localhost:8096/pear/inOrder/init";

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/inorderpull/inorderpullIndex";
    }




    /**
     * 添加数据
     */
    @RequestMapping("/pull")
    public void add(HttpServletRequest request, HttpServletResponse response, InOrderPullModel bean) throws Exception {
//        InOrderPullModel bean = new InOrderPullModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getAcType() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else {
                sendFailureMessage(response, "您无权进行拉单操作!");
                return;
            }

            String channel = "";
            String trade_type = "";
            String total_amount = "";
            String out_trade_no = "SDDS" + DateUtil.getNowPlusTimeMill();
            String sign = "";
            String notify_url = "http://www.baidu.com/temp";
            String interface_ver = "V5.0";
            String return_url = "http://www.qidian.com";
            String noredirect = "2";// 1返回json数据，2返回base64加密的支付地址，3走302跳转
            String is_profit = "";

            if (bean == null){
                sendFailureMessage(response, "错误,请重试!");
                return;
            }
            if (bean.getChannelId() == 0){
                sendFailureMessage(response, "请选择渠道!");
                return;
            }
            if (bean.getPfGewayCodeId() == 0 && bean.getGewayCodeId() == 0){
                sendFailureMessage(response, "平台通道,通道必须二选一!");
                return;
            }
            if (bean.getPfGewayCodeId() > 0 && bean.getGewayCodeId() > 0){
                sendFailureMessage(response, "平台通道,通道只能选择其中一个选择!");
                return;
            }
            if (StringUtils.isBlank(bean.getIsProfit())){
                sendFailureMessage(response, "请选择是否计算收益!");
                return;
            }

            // 判断订单金额是否为空
            if (StringUtils.isBlank(bean.getTotalAmount())){
                sendFailureMessage(response,"请填写拉单金额!");
                return;
            }else {
                // 判断订单金额是否包含减号
                if (bean.getTotalAmount().indexOf("-") > -1){
                    sendFailureMessage(response,"请填写正确的拉单金额!");
                    return;
                }

                // 金额是否有效
                if (bean.getTotalAmount().indexOf(".") > -1){
                    boolean flag = StringUtil.isNumberByMoney(bean.getTotalAmount());
                    if (!flag){
                        sendFailureMessage(response,"金额小数点后只能有2位!");
                        return;
                    }
                }else {
                    boolean flag = StringUtil.isNumer(bean.getTotalAmount());
                    if (!flag){
                        sendFailureMessage(response,"请填写正常的数字的拉单金额!");
                        return;
                    }
                    // 添加小数点后2位
                    bean.setTotalAmount(bean.getTotalAmount() + ".00");
                }
            }

            // 拉单金额
            total_amount = bean.getTotalAmount();

            // 是否计算收益
            is_profit = bean.getIsProfit();

            // 获取渠道的信息
            ChannelModel channelModel = new ChannelModel();
            channelModel.setId(bean.getChannelId());
            channelModel = channelService.queryByCondition(channelModel);
            if (channelModel == null || channelModel.getId() <= 0){
                sendFailureMessage(response,"根据渠道ID查询渠道数据为空,错误,请重试!");
                return;
            }
            // 商铺号
            channel = channelModel.getChannel();
            if (bean.getPfGewayCodeId() > 0){
                PrPlatformGewayCodeModel prPlatformGewayCodeModel = new PrPlatformGewayCodeModel();
                prPlatformGewayCodeModel.setId(bean.getPfGewayCodeId());
                prPlatformGewayCodeModel = prPlatformGewayCodeService.queryByCondition(prPlatformGewayCodeModel);
                if (prPlatformGewayCodeModel == null || prPlatformGewayCodeModel.getId() <= 0){
                    sendFailureMessage(response,"根据平台ID查询平台数据为空,错误,请重试!");
                    return;
                }else {
                    trade_type = prPlatformGewayCodeModel.getPfGewayCode();
                }
            }

            if (bean.getGewayCodeId() > 0){
                PrGewayCodeModel prGewayCodeModel = new PrGewayCodeModel();
                prGewayCodeModel.setId(bean.getGewayCodeId());
                prGewayCodeModel = prGewayCodeService.queryByCondition(prGewayCodeModel);
                if (prGewayCodeModel == null || prGewayCodeModel.getId() <= 0){
                    sendFailureMessage(response,"根据通道ID查询通道数据为空,错误,请重试!");
                    return;
                }else {
                    trade_type = prGewayCodeModel.getMyGewayCode();
                }
            }



            sign = getQrCodeSign(channelModel.getChannel(), trade_type, total_amount, out_trade_no, noredirect, notify_url, channelModel.getSecretKey());

            Map<String ,Object> sendDataMap = new HashMap<>();
            sendDataMap.put("channel", channel);
            sendDataMap.put("trade_type", trade_type);
            sendDataMap.put("total_amount", total_amount);
            sendDataMap.put("sign", sign);
            sendDataMap.put("out_trade_no", out_trade_no);
            sendDataMap.put("notify_url", notify_url);
            sendDataMap.put("interface_ver", interface_ver);
            sendDataMap.put("return_url", return_url);
            sendDataMap.put("noredirect", noredirect);
            sendDataMap.put("is_profit", is_profit);
            String parameter = JSON.toJSONString(sendDataMap);
            String resData = HttpSendUtils.sendPostAppJson(interfaceAds_POST, parameter);
            if (StringUtils.isBlank(resData)){
                sendFailureMessage(response,"拉单失败,请您重试!");
//                String qrUrl = "http://www.baidu.com";
//                sendSuccessMessage(response, "保存成功~", qrUrl);
                return;
            }
            if (!StringUtils.isBlank(resData)){
                if (resData.indexOf("resultCode") > -1){
                    sendFailureMessage(response,"拉单失败,请您重试!");
//                    String qrUrl = "http://www.baidu.com";
//                    sendSuccessMessage(response, "保存成功~", qrUrl);
                    return;
                }
            }
            String qrUrl = StringUtil.decoderBase64(resData);
            sendSuccessMessage(response, "保存成功~", qrUrl);
            return;
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
            return;
        }
    }


    public static String getQrCodeSign(String channel, String trade_type, String total_amount, String out_trade_no,
                                       String noredirect, String notify_url, String secretKey){
        String checkSign = "";
        checkSign = "channel=" + channel + "&" + "trade_type=" + trade_type + "&" + "total_amount=" + total_amount
                + "&" + "out_trade_no=" + out_trade_no + "&" + "noredirect=" + noredirect
                + "&" + "notify_url=" + notify_url + "&" + "key=" + secretKey;
        String str = MD5Util.encryption(checkSign);
        return str;
    }

}
