package com.galaxyeye.websocket.tool.encr;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {
    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";


    public static String encrypt(String str, String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
            UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String res = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return res;
    }


    public static void main(String[] args) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException {
        String pwd = "123456";
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCEDagxsEB5eiEKMEW/sqYyxVjMlYh98tph25fcWVoUqcz1o/cA2axRW6/+XakjApeQXf0yYpjsoD+vJzRFngSmgdSToHmBJ5bqgKpNtDF0BXBor9oyMoCbopsSea9a4eS3PdXbOzVwYMG8DxYsgqqYl/+47kzo4YzbCVxoaLPaOwIDAQAB";
        String encrypt = encrypt(pwd, pubKey);
        System.out.println(encrypt);
    }

}
