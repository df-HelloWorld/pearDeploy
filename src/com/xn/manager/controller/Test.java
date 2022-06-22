package com.xn.manager.controller;

import java.util.ArrayList;
import java.util.List;

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
        List<Long> cdList = new ArrayList<>();
        cdList.add(11L);
        cdList.add(22L);
        cdList.add(3L);
        cdList.add(44L);
        cdList.add(5L);
        cdList.add(66L);
        List<Long> dataList = new ArrayList<>();
        dataList.add(3L);
        dataList.add(5L);

        for (int i=0;i<dataList.size();i++) {
            for (int j=0;j<cdList.size();j++) {
                if (cdList.get(j) == dataList.get(i)){
                    System.out.println(cdList.get(j));
                }else {
                    System.out.println(cdList.get(j));
                }
            }
        }

    }
}
