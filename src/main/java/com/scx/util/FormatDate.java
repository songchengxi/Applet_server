package com.scx.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期工具类
 */
public class FormatDate {

    /**
     * 将微信消息中的CreateTime转换成标准格式时间（yyyy-MM-dd HH:mm:ss）
     *
     * @param createTime
     * @return
     */
    public static String formatTime(String createTime) {
        // 将微信传入的CreateTime转换成long类型，在乘1000成毫秒
        Long msgCreateTime = Long.parseLong(createTime) * 1000L;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(msgCreateTime));
    }
}
