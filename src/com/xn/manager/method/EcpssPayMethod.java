package com.xn.manager.method;

import com.xn.common.util.MD5Util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author long
 * @Date 2021/10/30 13:03
 * @Version 1.0
 */
public class EcpssPayMethod {
    public static  String    toSignInfo(Map<String,String> map){
        String  ecpssPayMd5 ="";
        ecpssPayMd5="MerNo="+map.get("MerNo").toString()+"&BillNo="+map.get("BillNo").toString()+"&Amount="+map.get("Amount").toString()
                +"&OrderTime="+map.get("OrderTime").toString()+"&ReturnURL="+map.get("ReturnURL").toString()+
                "&AdviceURL="+map.get("AdviceURL").toString()+"&"+map.get("MD5key").toString();

        String   toecpssPayMd5=MD5Util.getMD5String(ecpssPayMd5);
        return toecpssPayMd5.toUpperCase();
    }



    public static  String    gatewayPayment(Map<String,String> map){
        String  ecpssPayMd5 ="";
        ecpssPayMd5="MerNo="+map.get("MerNo").toString()+"&BillNo="+map.get("BillNo").toString()+"&Amount="+map.get("Amount").toString()
                +"&OrderTime="+map.get("OrderTime").toString()+"&ReturnURL="+map.get("ReturnURL").toString()+
                "&AdviceURL="+map.get("AdviceURL").toString()+"&"+map.get("MD5key").toString();

        String   toecpssPayMd5=MD5Util.getMD5String(ecpssPayMd5);
        return toecpssPayMd5.toUpperCase();
    }

    public  static void main(String [] args){
        Map<String,String>  map  =new HashMap<>();
        map.put("MerNo","51655");
        map.put("BillNo","100130");
        map.put("Amount","0.60");
        map.put("OrderTime","20160602170707");
        map.put("ReturnURL","http://www.baidu.com");
        map.put("AdviceURL","http://www.baidu.com");
        map.put("MD5key","123123");
        System.out.println(EcpssPayMethod.toSignInfo(map));
        String  code ="bwi8qcCAisOXrLg76hlRcDnLUJYtYbTRLtqRE1jvnrdhW%2FZUxbbaMmIEEmuyeXSPReGCEkn3n3FlIJmGmU3yngnmG%2B2Eho51rkv1lkUqX17QrSoJJZT2pDu5dqadFhEDbpIldUogtJqFfmL1SnyjX0AZb1d8JVbL9c4yXkHaUIE%3D";
//        Url
    }
}
