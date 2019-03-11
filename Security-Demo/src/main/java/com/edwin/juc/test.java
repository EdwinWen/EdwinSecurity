package com.edwin.juc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wenpuzhao on 2019/3/8.
 */
public class test {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher("13012345678");
        // 判断不是手机号，则进行数据库保存
        if( !isNum.matches() ){
            System.out.println("我不是手机号");
        }else{
            System.out.println("手机号");
        }
    }
}
