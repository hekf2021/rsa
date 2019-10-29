package com.mytt.rsacode.rsa;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.Security;
import java.util.UUID;

/**
 * AES加密工具
 */
public class AESUtils {

    private static final BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();

    /**
     * 生成随机密钥
     */
    public static String randomKey() {
        SecureRandom random = new SecureRandom();
        long randomKey = random.nextLong();
        return String.valueOf(randomKey);
        //return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }

    /**
     * 加密
     * @param randomKey 随机密钥
     * @param data 报文
     * @return 报文密文
     */
    public static String encrypt(String data, String randomKey) throws Exception {
        byte[] aesData = doCipher(randomKey, data.getBytes("UTF-8"), Cipher.ENCRYPT_MODE);
        return Base64.encodeBase64String(aesData);
    }

    /**
     * 解密
     * @param data 加密后的报文
     * @param randomKey aes秘钥
     */
    public static String decrypt(String data, String randomKey) throws Exception {
        byte[] plain = doCipher(randomKey, Base64.decodeBase64(data), Cipher.DECRYPT_MODE);
        return new String(plain, Charset.forName("utf-8"));
    }

    /**
     * 公共方法
     * @param randomKey aes key
     */
    private static byte[] doCipher(String randomKey, byte[] bytes, int mode) throws Exception {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(randomKey.getBytes());
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        Security.addProvider(bouncyCastleProvider);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(mode, keySpec);
        return cipher.doFinal(bytes);
    }

}
