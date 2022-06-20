package com.xn.manager.method;

import com.alibaba.fastjson.JSON;
import com.xn.common.util.DateUtil;
import com.xn.common.util.MD5;
import com.xn.manager.model.*;
import com.xn.manager.model.agent.AgentBalanceDeductModel;
import com.xn.manager.model.channel.ChannelBalanceDeductModel;
import com.xn.manager.model.inorder.InOrderModel;
import com.xn.manager.model.replenish.ReplenishModel;
import com.xn.manager.model.strategy.StrategyData;
import com.xn.manager.model.strategy.StrategyModel;
import com.xn.manager.model.template.*;
import com.xn.manager.service.StrategyService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author df
 * @Description: TODO(公共方法类)
 * @create 10:12 2018/9/12
 **/
public class PublicMethod {
    private static Logger log = Logger.getLogger(PublicMethod.class);


    /**
     * @Description: TODO(生成AppKey的值)
     * @author df
     * @param dpId-开发者ID
     * @create 10:30 2018/9/12
     **/
    public static String assembleAppKey(long dpId) throws Exception {
        String str = "";
        String dp = dpId+"";
        String timeMill = DateUtil.getNowPlusTimeMill();
        str = MD5.parseMD5(dp+timeMill);
        return str;
    }


    /**
     * @Description: TODO(生成UUID)
     * @author df
     * @create 16:56 2018/9/18
     **/
    public static String assembleUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }


    /**
     * @Description: 组装渠道扣减余额流水的方法
     * @param channelId - 渠道ID
     * @param orderNo - 订单号
     * @param money - 金额
     * @param orderType - 订单类型：1提现订单，2代付订单，3提现驳回订单
     * @param changeType - 变更金额类型：0初始化，1核减金额，2加金额
     * @param delayTime - 延迟运行时间：当订单属于超时状态：则系统时间需要大于此时间才能进行逻辑操作
     * @param lockTime - 锁定时间
     * @return com.xn.manager.model.channel.ChannelBalanceDeductModel
     * @Author: yoko
     * @Date 2021/9/7 10:54
     */
    public static ChannelBalanceDeductModel assembleChannelBalanceDeductAdd(long channelId, String orderNo, String money,
                                                                            int orderType, int changeType, String delayTime,
                                                                            String lockTime){
        ChannelBalanceDeductModel resBean = new ChannelBalanceDeductModel();
        resBean.setChannelId(channelId);
        resBean.setOrderNo(orderNo);
        resBean.setMoney(money);
        resBean.setOrderType(orderType);
        resBean.setChangeType(changeType);
        if (!StringUtils.isBlank(delayTime)){
            resBean.setDelayTime(delayTime);
        }
        if (!StringUtils.isBlank(lockTime)){
            resBean.setLockTime(lockTime);
        }

        resBean.setCurday(DateUtil.getDayNumber(new Date()));
        resBean.setCurhour(DateUtil.getHour(new Date()));
        resBean.setCurminute(DateUtil.getCurminute(new Date()));
        return resBean;
    }


    /**
     * @Description: 组装代理扣减余额流水的方法
     * @param agentId - 代理ID
     * @param orderNo - 订单号
     * @param money - 金额
     * @param orderType - 订单类型：1提现订单，2代付订单，3提现驳回订单
     * @param changeType - 变更金额类型：0初始化，1核减金额，2加金额
     * @param delayTime - 延迟运行时间：当订单属于超时状态：则系统时间需要大于此时间才能进行逻辑操作
     * @param lockTime - 锁定时间
     * @return com.xn.manager.model.agent.AgentBalanceDeductModel
     * @Author: yoko
     * @Date 2021/9/7 10:54
     */
    public static AgentBalanceDeductModel assembleAgentBalanceDeductAdd(long agentId, String orderNo, String money,
                                                                          int orderType, int changeType, String delayTime,
                                                                          String lockTime){
        AgentBalanceDeductModel resBean = new AgentBalanceDeductModel();
        resBean.setAgentId(agentId);
        resBean.setOrderNo(orderNo);
        resBean.setMoney(money);
        resBean.setOrderType(orderType);
        resBean.setChangeType(changeType);
        if (!StringUtils.isBlank(delayTime)){
            resBean.setDelayTime(delayTime);
        }
        if (!StringUtils.isBlank(lockTime)){
            resBean.setLockTime(lockTime);
        }

        resBean.setCurday(DateUtil.getDayNumber(new Date()));
        resBean.setCurhour(DateUtil.getHour(new Date()));
        resBean.setCurminute(DateUtil.getCurminute(new Date()));
        return resBean;
    }


    /**
     * @Description: 组装补单数据添加的方法
     * @param inOrderModel - 代收订单信息
     * @param isProfit - 是否用于计算收益：1不计算收益，2计算收益
     * @param remark - 备注
     * @return com.xn.manager.model.replenish.ReplenishModel
     * @Author: yoko
     * @Date 2021/9/15 19:48
     */
    public static ReplenishModel assembleReplenishAdd(InOrderModel inOrderModel, int isProfit, String remark){
        ReplenishModel resBean = new ReplenishModel();
        resBean.setChannelId(inOrderModel.getChannelId());
        resBean.setMyTradeNo(inOrderModel.getMyTradeNo());
        resBean.setOutTradeNo(inOrderModel.getOutTradeNo());
        resBean.setIsProfit(isProfit);
        if (!StringUtils.isBlank(remark)){
            resBean.setRemark(remark);
        }
        resBean.setCurday(DateUtil.getDayNumber(new Date()));
        resBean.setCurhour(DateUtil.getHour(new Date()));
        resBean.setCurminute(DateUtil.getCurminute(new Date()));
        return resBean;

    }


    /**
     * @Description: 组装前端传过来的form表单数据
     * @param strArr
     * @return
     * @Author: yoko
     * @Date 2021/9/23 17:06
    */
    public static List<SendFieldModel> assembleSendFieldListByJson(String [] strArr) throws Exception{
        List<SendFieldModel> resList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        for (String str : strArr){
            str = URLDecoder.decode(str, "UTF-8");
            if (str.subSequence(0,1).equals("&")){
                str = str.substring(1, str.length());
            }
            str = str.replaceAll("=", "\":\"");
            str = str.replaceAll("&", "\",\"");
            str = "{\"" + str + "\"}";

            strList.add(str);
        }

        for (String str : strList){
            SendFieldModel sendFieldModel = JSON.parseObject(str, SendFieldModel.class);
            if (sendFieldModel != null){
                resList.add(sendFieldModel);
            }
        }

        for (SendFieldModel sb : resList){
            log.info("id:" + sb.getId() + ", send_template_id:" + sb.getSendTemplateId() + ", field_name:" + sb.getFieldName()
            + ", parameter_name:" + sb.getParameterName() + ", parameter_value:" + sb.getParameterValue() + ", parameter_value_type:" + sb.getParameterValueType()
            + ", is_encryption:" + sb.getIsEncryption() + ", is_vacant:" + sb.getIsVacant() + ", seat:" + sb.getSeat() + ", field_type:" + sb.getFieldType());
        }

        return resList;

    }


    /**
     * @Description: 解析请求模板数据
     * <p>
     *     前端传的是key1=value1&key2=value2.....数据格式
     *     这里需要把数据转换成json然后在通过json解析出实体Bean
     * </p>
     * @param str - 请求模板数据
     * @return
     * @Author: yoko
     * @Date 2021/9/28 11:35
    */
    public static SendTemplateModel assembleSendTemplateByJson(String str) throws Exception{
        str = str.replace("\"", "");
        str = URLDecoder.decode(str, "UTF-8");
        str = str.replaceAll("=", "\":\"");
        str = str.replaceAll("&", "\",\"");
        str = "{\"" + str + "\"}";
        SendTemplateModel resBean = JSON.parseObject(str, SendTemplateModel.class);
        return resBean;
    }

    /**
     * @Description: 解析请求字段数据
     * <p>
     *     前端传的是key1=value1&key2=value2.....数据格式
     *     这里需要把数据转换成json然后在通过json解析出实体Bean
     * </p>
     * @param str - 请求字段
     * @return
     * @Author: yoko
     * @Date 2021/9/28 11:40
    */
    public static List<SendFieldModel> assembleSendFieldByJson(String str) throws Exception{
        str = str.replace("\"", "");
        str = URLDecoder.decode(str, "UTF-8");
        String [] strArr = str.split("&yc=`");

        List<SendFieldModel> resList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        for (String data : strArr){
            if (data.subSequence(0,1).equals("&")){
                data = data.substring(1, data.length());
            }
            data = data.replaceAll("=", "\":\"");
            data = data.replaceAll("&", "\",\"");
            data = "{\"" + data + "\"}";

            strList.add(data);
        }

        for (String data : strList){
            SendFieldModel sendFieldModel = JSON.parseObject(data, SendFieldModel.class);
            if (sendFieldModel != null){
                resList.add(sendFieldModel);
            }
        }

//        for (SendFieldModel sb : resList){
//            log.info("id:" + sb.getId() + ", send_template_id:" + sb.getSendTemplateId() + ", field_name:" + sb.getFieldName()
//                    + ", parameter_name:" + sb.getParameterName() + ", parameter_value:" + sb.getParameterValue() + ", parameter_value_type:" + sb.getParameterValueType()
//                    + ", is_encryption:" + sb.getIsEncryption() + ", is_vacant:" + sb.getIsVacant() + ", seat:" + sb.getSeat() + ", field_type:" + sb.getFieldType());
//        }

        return resList;
    }


    /**
     * @Description: check请求模板的基本数据
     * @param sendTemplateModel - 请求模板的数据
     * @return
     * @Author: yoko
     * @Date 2021/10/8 14:46
    */
    public static Map<String, Object> checkSendTemplate(SendTemplateModel sendTemplateModel){
        Map<String, Object> resMap = new HashMap<>();
        boolean flag_check = true;
        String msg = "";
        if (sendTemplateModel.getGewayCodeId() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "通道码不能为空!");
            return resMap;
        }
        if (StringUtils.isBlank(sendTemplateModel.getTemplate())){
            resMap.put("flag_check", false);
            resMap.put("msg", "模板名称不能为空!");
            return resMap;
        }
        if (sendTemplateModel.getIsEncryption() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "是否要加密不能为空!");
            return resMap;
        }
        if (sendTemplateModel.getEncryptionWay() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "大小写加密方式不能为空!");
            return resMap;
        }
        if (sendTemplateModel.getEncryptionType() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "加密类型不能为空!");
            return resMap;
        }
        if (sendTemplateModel.getSecretKeySeat() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "秘钥放置位置不能为空!");
            return resMap;
        }
        if (sendTemplateModel.getSecretKeyType() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "秘钥key类型不能为空!");
            return resMap;
        }
        if (sendTemplateModel.getEncryptionSort() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "加密排序不能为空!");
            return resMap;
        }
        if (sendTemplateModel.getSendType() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "请求提交数据方式不能为空!");
            return resMap;
        }


        resMap.put("flag_check", flag_check);
        resMap.put("msg", msg);
        return resMap;
    }


    /**
     * @Description: check请求字段是否填写正确
     * @param sendFieldList - 请求字段的集合数据
     * @return
     * @Author: yoko
     * @Date 2021/10/9 10:21
    */
    public static Map<String, Object> checkSendField(List<SendFieldModel> sendFieldList) {
        Map<String, Object> resMap = new HashMap<>();
        boolean flag_check = true;
        String msg = "";
        for (SendFieldModel sendFieldModel : sendFieldList){
            if (StringUtils.isBlank(sendFieldModel.getFieldName())){
                resMap.put("flag_check", false);
                resMap.put("msg", "字段名称不能为空!");
                return resMap;
            }
            if (StringUtils.isBlank(sendFieldModel.getParameterName())){
                resMap.put("flag_check", false);
                resMap.put("msg", "参数名称不能为空!");
                return resMap;
            }
            if (sendFieldModel.getParameterValueType() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "参数值类型不能为空!");
                return resMap;
            }
            if (sendFieldModel.getIsEncryption() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "是否要加密不能为空!");
                return resMap;
            }
            if (sendFieldModel.getIsVacant() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "是否允许为空不能为空!");
                return resMap;
            }
            if (sendFieldModel.getSeat() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "顺序/位置不能为空!");
                return resMap;
            }
            if (sendFieldModel.getFieldType() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "字段类型不能为空!");
                return resMap;
            }
        }
        resMap.put("flag_check", flag_check);
        resMap.put("msg", msg);
        return resMap;
    }



    /**
     * @Description: 解析返回模板数据
     * <p>
     *     前端传的是key1=value1&key2=value2.....数据格式
     *     这里需要把数据转换成json然后在通过json解析出实体Bean
     * </p>
     * @param str - 返回模板数据
     * @return
     * @Author: yoko
     * @Date 2021/9/28 11:35
     */
    public static ReturnTemplateModel assembleReturnTemplateByJson(String str) throws Exception{
        str = str.replace("\"", "");
        str = URLDecoder.decode(str, "UTF-8");
        str = str.replaceAll("=", "\":\"");
        str = str.replaceAll("&", "\",\"");
        str = "{\"" + str + "\"}";
        ReturnTemplateModel resBean = JSON.parseObject(str, ReturnTemplateModel.class);
        return resBean;
    }


    /**
     * @Description: check返回模板的基本数据
     * @param returnTemplateModel - 返回模板的数据
     * @return
     * @Author: yoko
     * @Date 2021/10/8 14:46
     */
    public static Map<String, Object> checkReturnTemplate(ReturnTemplateModel returnTemplateModel){
        Map<String, Object> resMap = new HashMap<>();
        boolean flag_check = true;
        String msg = "";
        if (returnTemplateModel.getGewayCodeId() == 0){
            resMap.put("msg", "通道码不能为空!");
            resMap.put("flag_check", false);
            return resMap;
        }
        if (StringUtils.isBlank(returnTemplateModel.getTemplate())){
            resMap.put("flag_check", false);
            resMap.put("msg", "模板名称不能为空!");
            return resMap;
        }
        if (returnTemplateModel.getDataType() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "返回数据类型不能为空!");
            return resMap;
        }
        if (StringUtils.isBlank(returnTemplateModel.getParameterName())){
            resMap.put("flag_check", false);
            resMap.put("msg", "拉单状态的参数名不能为空!");
            return resMap;
        }
        if (StringUtils.isBlank(returnTemplateModel.getParameterValue())){
            resMap.put("flag_check", false);
            resMap.put("msg", "拉单成功的参数值不能为空!");
            return resMap;
        }
        if (returnTemplateModel.getParameterValueType() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "拉单成功参数值的数据类型不能为空!");
            return resMap;
        }

        resMap.put("flag_check", flag_check);
        resMap.put("msg", msg);
        return resMap;
    }

    /**
     * @Description: 解析返回字段数据
     * <p>
     *     前端传的是key1=value1&key2=value2.....数据格式
     *     这里需要把数据转换成json然后在通过json解析出实体Bean
     * </p>
     * @param str - 返回字段
     * @return
     * @Author: yoko
     * @Date 2021/9/28 11:40
     */
    public static List<ReturnFieldModel> assembleReturnFieldByJson(String str) throws Exception{
        str = str.replace("\"", "");
        str = URLDecoder.decode(str, "UTF-8");
        String [] strArr = str.split("&yc=`");

        List<ReturnFieldModel> resList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        for (String data : strArr){
            log.info("");
            if (data.subSequence(0,1).equals("&")){
                data = data.substring(1, data.length());
            }
            data = data.replaceAll("=", "\":\"");
            data = data.replaceAll("&", "\",\"");
            data = "{\"" + data + "\"}";

            strList.add(data);
        }

        for (String data : strList){
            ReturnFieldModel returnFieldModel = JSON.parseObject(data, ReturnFieldModel.class);
            if (returnFieldModel != null){
                resList.add(returnFieldModel);
            }
        }

        for (ReturnFieldModel sb : resList){
            log.info("id:" + sb.getId() + ", return_template_id:" + sb.getReturnTemplateId() + ", field_name:" + sb.getFieldName()
                    + ", parameter_name:" + sb.getParameterName() + ", parameter_value:" + sb.getParameterValue() + ", parameter_value_type:" + sb.getParameterValueType()
                    + ", seat:" + sb.getSeat() + ", field_type:" + sb.getFieldType());
        }

        return resList;
    }


    /**
     * @Description: check返回字段是否填写正确
     * @param returnFieldList - 返回字段的集合数据
     * @return
     * @Author: yoko
     * @Date 2021/10/9 10:21
     */
    public static Map<String, Object> checkReturnField(List<ReturnFieldModel> returnFieldList) {
        Map<String, Object> resMap = new HashMap<>();
        boolean flag_check = true;
        String msg = "";
        for (ReturnFieldModel returnFieldModel : returnFieldList){
            if (StringUtils.isBlank(returnFieldModel.getFieldName())){
                resMap.put("flag_check", false);
                resMap.put("msg", "字段名称不能为空!");
                return resMap;
            }
            if (StringUtils.isBlank(returnFieldModel.getParameterName())){
                resMap.put("flag_check", false);
                resMap.put("msg", "参数名称不能为空!");
                return resMap;
            }
            if (returnFieldModel.getParameterValueType() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "参数值类型不能为空!");
                return resMap;
            }
            if (returnFieldModel.getFieldType() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "字段类型不能为空!");
                return resMap;
            }
            if (returnFieldModel.getSeat() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "存放位置不能为空!");
                return resMap;
            }
        }
        resMap.put("flag_check", flag_check);
        resMap.put("msg", msg);
        return resMap;
    }



    /**
     * @Description: 组装前端传过来的form表单数据-返回模板的返回字段
     * @param strArr
     * @return
     * @Author: yoko
     * @Date 2021/9/23 17:06
     */
    public static List<ReturnFieldModel> assembleReturnFieldListByJson(String [] strArr) throws Exception{
        List<ReturnFieldModel> resList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        for (String str : strArr){
            log.info("");
            str = URLDecoder.decode(str, "UTF-8");
            if (str.subSequence(0,1).equals("&")){
                str = str.substring(1, str.length());
            }
            str = str.replaceAll("=", "\":\"");
            str = str.replaceAll("&", "\",\"");
            str = "{\"" + str + "\"}";

            strList.add(str);
        }

        for (String str : strList){
            ReturnFieldModel returnFieldModel = JSON.parseObject(str, ReturnFieldModel.class);
            if (returnFieldModel != null){
                resList.add(returnFieldModel);
            }
        }

        for (ReturnFieldModel sb : resList){
            log.info("id:" + sb.getId() + ", return_template_id:" + sb.getReturnTemplateId() + ", field_name:" + sb.getFieldName()
                    + ", parameter_name:" + sb.getParameterName() + ", parameter_value:" + sb.getParameterValue() + ", parameter_value_type:" + sb.getParameterValueType()
                    + ", seat:" + sb.getSeat() + ", field_type:" + sb.getFieldType());
        }

        return resList;

    }



    /**
     * @Description: 解析接收模板数据
     * <p>
     *     前端传的是key1=value1&key2=value2.....数据格式
     *     这里需要把数据转换成json然后在通过json解析出实体Bean
     * </p>
     * @param str - 接收模板数据
     * @return
     * @Author: yoko
     * @Date 2021/9/28 11:35
     */
    public static NotifyTemplateModel assembleNotifyTemplateByJson(String str) throws Exception{
        str = str.replace("\"", "");
        str = URLDecoder.decode(str, "UTF-8");
        str = str.replaceAll("=", "\":\"");
        str = str.replaceAll("&", "\",\"");
        str = "{\"" + str + "\"}";
        NotifyTemplateModel resBean = JSON.parseObject(str, NotifyTemplateModel.class);
        return resBean;
    }



    /**
     * @Description: check接收模板的基本数据
     * @param notifyTemplateModel - 接收模板的数据
     * @return
     * @Author: yoko
     * @Date 2021/10/8 14:46
     */
    public static Map<String, Object> checkNotifyTemplate(NotifyTemplateModel notifyTemplateModel){
        Map<String, Object> resMap = new HashMap<>();
        boolean flag_check = true;
        String msg = "";
        if (notifyTemplateModel.getGewayCodeId() == 0){
            resMap.put("msg", "通道码不能为空!");
            resMap.put("flag_check", false);
            return resMap;
        }
        if (StringUtils.isBlank(notifyTemplateModel.getTemplate())){
            resMap.put("flag_check", false);
            resMap.put("msg", "模板名称不能为空!");
            return resMap;
        }
        if (notifyTemplateModel.getNotifyType() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "接收数据方式不能为空!");
            return resMap;
        }

        if (notifyTemplateModel.getDataType() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "返回数据类型不能为空!");
            return resMap;
        }
        if (StringUtils.isBlank(notifyTemplateModel.getParameterName())){
            resMap.put("flag_check", false);
            resMap.put("msg", "订单状态的参数名不能为空!");
            return resMap;
        }
        if (StringUtils.isBlank(notifyTemplateModel.getParameterValue())){
            resMap.put("flag_check", false);
            resMap.put("msg", "订单成功的参数值不能为空!");
            return resMap;
        }
        if (notifyTemplateModel.getParameterValueType() == 0){
            resMap.put("flag_check", false);
            resMap.put("msg", "订单成功参数值的数据类型不能为空!");
            return resMap;
        }
        if (StringUtils.isBlank(notifyTemplateModel.getSucTag())){
            resMap.put("flag_check", false);
            resMap.put("msg", "接收成功数据返回的tag不能为空!");
            return resMap;
        }
        if (StringUtils.isBlank(notifyTemplateModel.getFailTag())){
            resMap.put("flag_check", false);
            resMap.put("msg", "接收数据失败返回的tag不能为空!");
            return resMap;
        }

        resMap.put("flag_check", flag_check);
        resMap.put("msg", msg);
        return resMap;
    }



    /**
     * @Description: 解析接收字段数据
     * <p>
     *     前端传的是key1=value1&key2=value2.....数据格式
     *     这里需要把数据转换成json然后在通过json解析出实体Bean
     * </p>
     * @param str - 接收字段
     * @return
     * @Author: yoko
     * @Date 2021/9/28 11:40
     */
    public static List<NotifyFieldModel> assembleNotifyFieldByJson(String str) throws Exception{
        str = str.replace("\"", "");
        str = URLDecoder.decode(str, "UTF-8");
        String [] strArr = str.split("&yc=`");

        List<NotifyFieldModel> resList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        for (String data : strArr){
            log.info("1");
            if (data.subSequence(0,1).equals("&")){
                data = data.substring(1, data.length());
            }
            data = data.replaceAll("=", "\":\"");
            data = data.replaceAll("&", "\",\"");
            data = "{\"" + data + "\"}";

            strList.add(data);
        }

        for (String data : strList){
            NotifyFieldModel notifyFieldModel = JSON.parseObject(data, NotifyFieldModel.class);
            if (notifyFieldModel != null){
                resList.add(notifyFieldModel);
            }
        }

        for (NotifyFieldModel sb : resList){
            log.info("id:" + sb.getId() + ", notify_template_id:" + sb.getNotifyTemplateId() + ", field_name:" + sb.getFieldName()
                    + ", parameter_name:" + sb.getParameterName() + ", parameter_value:" + sb.getParameterValue() + ", parameter_value_type:" + sb.getParameterValueType()
                    + ", seat:" + sb.getSeat() + ", field_type:" + sb.getFieldType());
        }

        return resList;
    }




    /**
     * @Description: check接收字段是否填写正确
     * @param notifyFieldList - 接收字段的集合数据
     * @return
     * @Author: yoko
     * @Date 2021/10/9 10:21
     */
    public static Map<String, Object> checkNotifyField(List<NotifyFieldModel> notifyFieldList) {
        Map<String, Object> resMap = new HashMap<>();
        boolean flag_check = true;
        String msg = "";
        for (NotifyFieldModel notifyFieldModel : notifyFieldList){
            if (StringUtils.isBlank(notifyFieldModel.getFieldName())){
                resMap.put("flag_check", false);
                resMap.put("msg", "字段名称不能为空!");
                return resMap;
            }
            if (StringUtils.isBlank(notifyFieldModel.getParameterName())){
                resMap.put("flag_check", false);
                resMap.put("msg", "参数名称不能为空!");
                return resMap;
            }
            if (notifyFieldModel.getParameterValueType() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "参数值类型不能为空!");
                return resMap;
            }
            if (notifyFieldModel.getSeat() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "存放位置不能为空!");
                return resMap;
            }
            if (notifyFieldModel.getFieldType() == 0){
                resMap.put("flag_check", false);
                resMap.put("msg", "字段类型不能为空!");
                return resMap;
            }

        }
        resMap.put("flag_check", flag_check);
        resMap.put("msg", msg);
        return resMap;
    }



    /**
     * @Description: 组装前端传过来的form表单数据-接收模板的接收字段
     * @param strArr
     * @return
     * @Author: yoko
     * @Date 2021/9/23 17:06
     */
    public static List<NotifyFieldModel> assembleNotifyFieldListByJson(String [] strArr) throws Exception{
        List<NotifyFieldModel> resList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        for (String str : strArr){
            log.info("1");
            str = URLDecoder.decode(str, "UTF-8");
            if (str.subSequence(0,1).equals("&")){
                str = str.substring(1, str.length());
            }
            str = str.replaceAll("=", "\":\"");
            str = str.replaceAll("&", "\",\"");
            str = "{\"" + str + "\"}";

            strList.add(str);
        }

        for (String str : strList){
            NotifyFieldModel notifyFieldModel = JSON.parseObject(str, NotifyFieldModel.class);
            if (notifyFieldModel != null){
                resList.add(notifyFieldModel);
            }
        }

        for (NotifyFieldModel sb : resList){
            log.info("id:" + sb.getId() + ", notify_template_id:" + sb.getNotifyTemplateId() + ", field_name:" + sb.getFieldName()
                    + ", parameter_name:" + sb.getParameterName() + ", parameter_value:" + sb.getParameterValue() + ", parameter_value_type:" + sb.getParameterValueType()
                    + ", seat:" + sb.getSeat() + ", field_type:" + sb.getFieldType());
        }

        return resList;

    }


//    /**
//    * @Description: 根据条件查询策略数据，把json策略返回集合数据
//    * @param strategyModel - 查询策略的条件
//    * @return: List<StrategyData>
//    * @author: yoko
//    * @date: 2022/6/20 10:32
//    * @version 1.0.0
//    */
//    public List<StrategyData> getStrategyJsonList(StrategyModel strategyModel){
//        StrategyModel strategyData = strategyService.queryByCondition(strategyModel);
//        List<StrategyData> resList = new ArrayList<>();
//        if (strategyData != null ){
//            if (!StringUtils.isBlank(strategyData.getStgBigValue())){
//                resList = JSON.parseArray(strategyModel.getStgBigValue(), StrategyData.class);
//            }
//        }
//        return resList;
//    }














    public static void main(String[] args) throws Exception {
        String str = assembleAppKey(3);
        log.info("str:"+str);
    }
}
