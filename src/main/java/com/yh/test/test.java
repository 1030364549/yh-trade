package com.yh.test;

import com.yh.controller.BaseController;
import com.yh.entity.Page;
import com.yh.service.IncrementAccountsService;
import com.yh.service.impl.IncrementAccountsServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * 类名:test
 * 描述：todo
 * 作者：luran
 * 时间：2019/5/27 15:26
 * 版本：1.0
 **/
public class test extends BaseController {
    public static void main(String[] args){
//        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//        Pattern regex = Pattern.compile(check);
//        Matcher matcher = regex.matcher("1101125210@qq.com");
//        boolean isMatched = matcher.matches();
//        System.out.println(isMatched);


        String str = "6212250200006122806";
        System.out.println(str.substring(0,10)+"****"+str.substring(14));


        System.out.println(str.substring(0,6)+"****"+str.substring(str.length()-4));


    }
}
