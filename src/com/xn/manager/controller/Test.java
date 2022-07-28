package com.xn.manager.controller;

import com.xn.manager.model.agent.AgentProfitDistributionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description: TODO
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class Test {
    //aaa
    public static void main(String [] args){
//        List<Long> cdList = new ArrayList<>();
//        cdList.add(11L);
//        cdList.add(22L);
//        cdList.add(3L);
//        cdList.add(44L);
//        cdList.add(5L);
//        cdList.add(66L);
//        List<Long> dataList = new ArrayList<>();
//        dataList.add(3L);
//        dataList.add(5L);
//
//        for (int i=0;i<dataList.size();i++) {
//            for (int j=0;j<cdList.size();j++) {
//                if (cdList.get(j) == dataList.get(i)){
//                    System.out.println(cdList.get(j));
//                }else {
//                    System.out.println(cdList.get(j));
//                }
//            }
//        }
//
//        String a = "0.9999";
//        if (a.matches("[+-]?[0-9]+(\\.[0-9]+)?")){
//            System.out.println("是");
//        }else {
//            System.out.println("不是");
//        }
//
//        double fr = Double.parseDouble(a);
//        if (fr >= 1){
//            System.out.println("错误");
//        }

        List<Long> agentIdList = new ArrayList<>();// 代理数据集合
        agentIdList.add(3L);
        agentIdList.add(6L);
//        agentIdList.add(12L);

        List<AgentProfitDistributionModel> pfGwewayCodeServiceChargeList = new ArrayList<>();// 平台通道添加分润的集合
        AgentProfitDistributionModel bean1 = new AgentProfitDistributionModel();
        bean1.setPfGewayCodeId(1L);
        bean1.setServiceCharge("0.011");
        pfGwewayCodeServiceChargeList.add(bean1);

        AgentProfitDistributionModel bean2 = new AgentProfitDistributionModel();
        bean2.setPfGewayCodeId(2L);
        bean2.setServiceCharge("0.012");
        pfGwewayCodeServiceChargeList.add(bean2);

        List<AgentProfitDistributionModel> sunPfGewayServiceChargeList = new ArrayList<>();


//        for (long agentId : agentIdList){
//            for (AgentProfitDistributionModel pfGwewayCodeServiceCharge : pfGwewayCodeServiceChargeList){
//                if (pfGwewayMap != null && !pfGwewayMap.isEmpty()){
//                    for (String mapKey : pfGwewayMap.keySet()) {
//                        System.out.print(key + "-" + testMap.get(key) + "	");
//                    }
//                }else{
//                    pfGwewayMap.put("pfGewayCodeId", pfGwewayCodeServiceCharge.getPfGewayCodeId() + "");
//                    pfGwewayMap.put("serviceCharge", pfGwewayCodeServiceCharge.getServiceCharge());
//                }
//
//            }
//        }

    }
}
