package com.qfedu.examsys.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author Lei
 * @Date 2019-8-30 15:04
 */

@Component
public class  TestMapperUtils{

    /**
     * @param tList 结果集合题库
     * @param size 需要随机个数
     * @return  返回  随机集合题库List
     */
    public  List  getRandomList(List tList, Integer size){

        List ts = new ArrayList<>();

        StringBuffer sb = new StringBuffer();

        if(tList.size()>size) {
            sb.delete(0,sb.length());
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                int i1 = random.nextInt(tList.size()-1);
                if(!sb.toString().contains(String.valueOf(i1))){
                    sb.append(i1);
                    ts.add(tList.get(i1));
                    continue;
                }
                i--;
            }
        }else {
            ts=tList;
        }
        return  ts;

    }

}
