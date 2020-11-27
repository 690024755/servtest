package com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter; /*
 * Description:com.galaxyeye.websocket.test.galaxyeye.MpService.TestCase.Inter
 * @Date Create on 14:40
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/28日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.test.galaxyeye.IResource;
import java.util.HashMap;

public interface IQaExpertResource extends IResource {
    /**
     * 表情转特殊字符形式
     */
    //表情转/uXXXX字符，存入数据库为表情包
    public static String str1 = "\\uD83C\\uDFC0";
    /**
     * 表情：https://apps.timwhitlock.info/emoji/tables/unicode
     */
    public static String str2 = "我的表情(emotion) is 😂😂😂😂✌✌✌✌🚑🚑🚑🚑🉐🉐🉐🉐⌚⌚⌚⌚🌾🌾🌾🌾📆📆📆📆😀😀😀😀🚘🚘🚘🚘🐇🐇🐇🐇";

    //表情转\UXXXXXXXX字符，存入数据库为表情包
    public static String str3 = "\\U0001F3C0";
    //表情转&#DDDD字符，存入数据库为表情包
    public static String str4 = "&#127936;";
    //表情转&#xXXXX;字符，存入数据库为表情包
    public static String str5 = "&#x1F3C0;";
    //表情转Punycode字符，存入数据库为表情包
    public static String str6 = "xl8h";
    //表情转\xXX utf-8字符，存入数据库为表情包
    public static String str7 = "\\xF0\\x9F\\x8F\\x80";
    //表情转Base64字符，存入数据库为表情包
    public static String str8 = "8J+PgA==";
    //表情转Quoted-printable字符，存入数据库为表情包
    public static String str9 = "=F0=9F=8F=80";
    //表情转URL字符，存入数据库为编码后
    public static String str10 = "%F0%9F%8F%80";


}
