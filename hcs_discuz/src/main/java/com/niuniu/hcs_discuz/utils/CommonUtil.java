package com.niuniu.hcs_discuz.utils;

/**
 * @author niuniu
 * @date 2022/3/16
 */
public class CommonUtil {

    public static boolean strIsNull(String str) {
        if (null == str || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

}
