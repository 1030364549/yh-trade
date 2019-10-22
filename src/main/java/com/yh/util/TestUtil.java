package com.yh.util;

import com.yh.cache.DataCache;
import redis.clients.jedis.Jedis;

public class TestUtil {
    //测试
    public static void main(String args[]) throws Exception {
        String ind_mcc="1000";
        int coode=Integer.valueOf(ind_mcc);
        String [] areacodes= new String[]{"1000","1100","1200","1600","1900","2200","2400","2600","2900","3000","3300",
                "3600","3900","4200","4500","4900","5200","5500","5800","6100","6400","6500","6900","7000",
                "7300","7700","7900","8200","8500","8700","8800"};
        for (int i=0;i<areacodes.length;i++){
            if (ind_mcc.equals(areacodes[i])){
                coode=Integer.valueOf(ind_mcc)+1;
            }
        }
        System.out.println(String.valueOf(coode));
        int num=(int)((Math.random()*9+1)*1000);
        System.out.println(num);
    }
}
