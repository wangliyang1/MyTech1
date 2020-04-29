package com.wd.tech.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * date:2020/4/6 0006
 */
public class MD5Util {
    private static MD5Util md5Util;
    private MD5Util() {
    }

    public static final MD5Util getInstance() {
        if (md5Util==null){
            synchronized (MD5Util.class){
                if (md5Util==null){
                    md5Util=new MD5Util();
                }
            }
        }
        return md5Util;
    }
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

}
