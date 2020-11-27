package com.galaxyeye.websocket.tool.netty.tcp; /*
 * Description:com.galaxyeye.websocket.tool.netty.tcp
 * @Date Create on 14:46
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/9/25日galaxyeye All Rights Reserved.
 */

public class TcpMessageUtil {

    private static final int TOTAL_SIZE = 14;


    private static byte[] requestPack(byte[] content,int cmd) {
        int total_size = content.length + TOTAL_SIZE;
        int pkgLen = total_size - 4;
        //System.out.println("包大小: " + pkgLen);
        int offset = 0;//偏移量
        byte[] msg = new byte[total_size];
        write_int_le(msg, offset, pkgLen);
        offset += 4;//pkgLen
        write_int_le(msg, offset, 0);
        offset += 4;//checkSum
        write_short_le(msg, offset, (short)cmd);
        offset += 2;//cmd
        write_short_le(msg, offset, (short) 0);
        offset += 2;//target
        write_short_le(msg, offset, (short) 0);
        offset += 2;//retCode
        write_bytes(content, 0, msg, offset);
        return msg;
    }

    private static void write_int_le(byte[] buf, int offset, int value) {
        //32位数，低8位值为value值，索引大于等于8位以上全部清空为0
        //https://blog.csdn.net/m0_37479246/article/details/79492828
        buf[offset + 3] = (byte) ((value >> 24) & 0xff);//说明把value值右移24位，高位的空位补符号位，即正数补零，负数补1。符号位不变，https://www.cnblogs.com/mithrandirw/p/8670859.html
        buf[offset + 2] = (byte) ((value >> 16) & 0xff);
        buf[offset + 1] = (byte) ((value >> 8) & 0xff);//value >> 8表示把value向右移动8位，高位的空位补符号位，即正数补零，负数补1
        buf[offset + 0] = (byte) (value & 0xff);//&为按位与操作，其中0x为16进制表示，那么0xff表示32位，https://cloud.tencent.com/developer/ask/65975
    }

    private static void write_short_le(byte[] buf, int offset, short value) {
        buf[offset + 1] = (byte) ((value >> 8) & 0xff);//说明一
        buf[offset + 0] = (byte) ((value) & 0xff);
    }

    private static void write_bytes(byte[] src, int src_offset, byte[] dst, int dst_offset) {
        for(int i = 0 ; i < src.length - src_offset; ++i) {
            dst[dst_offset +i] = src[src_offset + i];
        }
        //System.out.println("bytes.length: " + (src.length - src_offset));
        //System.arraycopy(src, src_offset, dst, dst_offset, src.length - src_offset);
    }


    /**
     *
     * @param content 发送请求的内容
     * @param cmd 发送的命令号
     * @return tcp请求返回的消息
     * @throws Exception
     */
    public static byte[] getMessage(byte[] content,int cmd) throws Exception {
        return requestPack(content,cmd);
    }

    /**
     *
     * @param content 发送请求的内容
     * @param cmd 发送的命令号
     * @return tcp请求返回的消息
     * @throws Exception
     */
    public static byte[] getMessage(String content,int cmd) throws Exception {
        return getMessage(content.getBytes("UTF-8"),cmd);
    }

}
