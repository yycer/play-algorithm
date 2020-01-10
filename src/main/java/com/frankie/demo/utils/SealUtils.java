package com.frankie.demo.utils;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.ArrayList;

/**
 * @author: Yao Frankie
 * @date: 2020/1/10 10:37
 */
public class SealUtils {

//    public static String showSafeCertNum(String certNum){
//        return showHideField(certNum, 0, 4);
//    }

    public static String showHideField(String certNum, int start, int end) {
        String trimmedCertNum = certNum.trim();
        if (trimmedCertNum.length() < (start + end + 1)) {
            return certNum;
        } else {
            StringBuffer buffer = new StringBuffer(trimmedCertNum);
            for(int i = start; i < trimmedCertNum.length() - end; i++){
                buffer.setCharAt(i, '*');
            }
            buffer.insert(start, ' ');
            buffer.insert(buffer.length() - end, ' ');
            return buffer.toString();
        }
    }

    public void testReference(){
        ArrayList<String> names = new ArrayList<>();
        names.add("yyc");
        names.add("frankie");
        processNames(names);
        System.out.println("The size of names is " + names.size());
    }

    private void processNames(ArrayList<String> names) {
        names.add("asan");
    }
}
