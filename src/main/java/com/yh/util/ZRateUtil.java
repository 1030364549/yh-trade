package com.yh.util;

import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ZRateUtil {
    /**
     *
     * @param flz 费率%
     * @param fdfl 附加费率%
     * @param fd   封顶金额
     * @param fjp   附加费率%
     * @param fj    附加金额
     * @return  Map<String,Map></>
     */
    public static Map<String,Map> generateRate(String flz,String fdfl,String fd,String fjp,String fj){
        Map<String,Map> reqMap = new HashMap();
        Map maps;
        String index;
        String newLength;
        String flsf;
        BigDecimal bz = new BigDecimal(100);
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
            maps = new HashMap();
            maps.put("arMark","R"+flsf);
            maps.put("arName",flz+"%费率");
            maps.put("arContent",flz+"%费率");
            maps.put("arMarkFl",flz+"%");
            maps.put("pArContent","P*"+fl.divide(bz,20,BigDecimal.ROUND_HALF_UP).doubleValue());
            reqMap.put("fl",maps);
        }
        if(!StringUtils.isEmpty(fdfl) && !StringUtils.isEmpty(fd)){
            //封顶费率
            BigDecimal fdl = new BigDecimal(fdfl);
            BigDecimal fdlx = new BigDecimal(1000);
            index = String.valueOf(fdl.multiply(fdlx).intValue());
            newLength = "0000000" + fdl.multiply(fdlx).intValue();
            String fdlsf;
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
            maps = new HashMap();
            maps.put("arMark","CAP"+flsf+"R"+fdlsf);
            maps.put("arMarkFl",fdfl+"%");
            maps.put("arMarkFd",fd);
            maps.put("arName",fd+"元封顶"+","+fdfl+"%费率");
            BigDecimal bfd = new BigDecimal(fd);
            BigDecimal money = bfd.divide(fdl.divide(bz,20,BigDecimal.ROUND_HALF_UP),20,BigDecimal.ROUND_HALF_UP);
            maps.put("arContent","(0-"+money.intValue()+")"+ fdfl +"%费率,"+fd+"元封顶");
            maps.put("onsetMoney","0");
            maps.put("endMoney",money.intValue());
            maps.put("pArContent","P*"+fdl.divide(bz,20,BigDecimal.ROUND_HALF_UP).doubleValue());
            BigDecimal big = new BigDecimal(0.01);
            maps.put("starMoney",money.add(big).intValue());
            maps.put("fdArContent",fd);
            reqMap.put("flfd",maps);
        }
        if(!StringUtils.isEmpty(fjp)){
            //附加费率
            BigDecimal fjfp = new BigDecimal(fjp);
            BigDecimal fjpx = new BigDecimal(1000);
            index = String.valueOf(fjfp.multiply(fjpx).intValue());
            newLength = "0000000" + fjfp.multiply(fjpx).intValue();
            String fjsf;
            if(index.length() > 2){
                fjsf = newLength.substring(newLength.length() - index.length());
            }else{
                fjsf = newLength.substring(newLength.length() - index.length() - 1);
            }
            maps = new HashMap();
            maps.put("arMark","FJP" + fjsf);
            maps.put("armarkFd",fjp + "%");
            maps.put("arName",fjp + "%附加费率");
            maps.put("arContent",fjp + "%附加费率");
            maps.put("pArContent","P*" + fjfp.divide(bz,4,BigDecimal.ROUND_HALF_UP));
            reqMap.put("fjfl",maps);
        }
        if(!StringUtils.isEmpty(fj)){
            //附加钱
            BigDecimal djfl = new BigDecimal(fj);
            BigDecimal djflx = new BigDecimal("100");
            newLength = "0000000" + djfl.multiply(djflx).intValue();
            index = String.valueOf(djfl.multiply(djflx).intValue());
            String fjfl;
            if(index.length() > 1 && index.length() < 3){
                fjfl = newLength.substring(newLength.length() - index.length() - 1);
            }else if (index.length() >= 3){
                fjfl = newLength.substring(newLength.length() - index.length());
            }else{
                fjfl = newLength.substring(newLength.length() - index.length() - 2);
            }
            maps = new HashMap();
            maps.put("arMark","FJ"+fjfl);
            maps.put("armarkFd",fj);
            maps.put("arName",fj + "元");
            maps.put("arContent",fj + "元");
            maps.put("pArContent",fj);
            reqMap.put("fjflq",maps);
        }
        return reqMap;
    }
}
