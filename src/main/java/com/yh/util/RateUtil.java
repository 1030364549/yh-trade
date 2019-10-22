package com.yh.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RateUtil {


    /**
     * @Author 野猪佩奇
     * @Description //TODO
     * @Date 18:00 2019/6/23
     * @Param [flz, fdfl, fd, fj, fjp]
     * @Param [费率值,封顶费率,封顶金额,附加金额,附加金额按费率]
     * @return java.util.Map
     **/
    public static Map generateRate(String flz,String fdfl,String fd,String fj,String fjp){
        Map reqMap = new HashMap();
        String index = "";
        String newLength = "";
        String flsf = "";
        if(!StringUtils.isEmpty(flz)){
            //费率
            BigDecimal fl = new BigDecimal(flz);
            BigDecimal flx = new BigDecimal(1000);
            index = String.valueOf(fl.multiply(flx).intValue());
            newLength = "0000000" + fl.multiply(flx).intValue();
            if(index.length() > 2){
                flsf = newLength.substring(newLength.length() - index.length());
            }else{
                flsf = newLength.substring(newLength.length() - index.length()-1);
            }
            reqMap.put("fl","R"+flsf);
        }

        if(!StringUtils.isEmpty(fdfl) && !StringUtils.isEmpty(fd)){
            //封顶费率
            BigDecimal fdl = new BigDecimal(fdfl);
            BigDecimal fdlx = new BigDecimal(1000);
            index = String.valueOf(fdl.multiply(fdlx).intValue());
            newLength = "0000000" + fdl.multiply(fdlx).intValue();
            String fdlsf = "";
            if(index.length() > 2){
                fdlsf = newLength.substring(newLength.length() - index.length());
            }else{
                fdlsf = newLength.substring(newLength.length() - index.length()-1);
            }
            //封顶值
            BigDecimal fengding = new BigDecimal(fd);//
            BigDecimal fdz = new BigDecimal("100");
            newLength = "0000000" + fengding.multiply(fdz).intValue();
            index = String.valueOf(fengding.multiply(fdz).intValue());
            if(index.length() > 1 && index.length() < 3){
                flsf = newLength.substring(newLength.length() - index.length() - 1);
            }else if (index.length() >= 3){
                flsf = newLength.substring(newLength.length() - index.length());
            }else{
                flsf = newLength.substring(newLength.length() - index.length() - 2);
            }
            reqMap.put("flfd","CAP"+flsf+"R"+fdlsf);
        }

        if(!StringUtils.isEmpty(fjp)){
            //附加费率
            BigDecimal fjfp = new BigDecimal(fjp);
            BigDecimal fjpx = new BigDecimal(1000);
            index = String.valueOf(fjfp.multiply(fjpx).intValue());
            newLength = "0000000" + fjfp.multiply(fjpx).intValue();
            String fjsf = "";
            if(index.length() > 2){
                fjsf = newLength.substring(newLength.length() - index.length());
            }else{
                fjsf = newLength.substring(newLength.length() - index.length()-1);
            }
            reqMap.put("fjfl","FJP"+fjsf);
        }


        if(!StringUtils.isEmpty(fj)){
            //附加钱
            BigDecimal djfl = new BigDecimal(fj);
            BigDecimal djflx = new BigDecimal("100");
            newLength = "0000000" + djfl.multiply(djflx).intValue();
            index = String.valueOf(djfl.multiply(djflx).intValue());
            String fjfl = "";
            if(index.length() > 1 && index.length() < 3){
                fjfl = newLength.substring(newLength.length() - index.length() - 1);
            }else if (index.length() >= 3){
                fjfl = newLength.substring(newLength.length() - index.length());
            }else{
                fjfl = newLength.substring(newLength.length() - index.length() - 2);
            }
            reqMap.put("fjflq","FJ"+fjfl);
        }
        return reqMap;
    }
}
