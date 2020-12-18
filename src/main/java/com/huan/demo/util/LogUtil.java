package com.huan.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 牟欢
 * @Classname LogUtil
 * @Description TODO
 * @Date 2020-12-17 14:25
 */
public class LogUtil {
    private static Logger customerLogger = LoggerFactory.getLogger("customerLogger");

    public static void customInfo(String msg){
        customerLogger.info(msg);
    }

    public static void customerError(String msg,Throwable t){
        customerLogger.error(msg,t);
    }

}
