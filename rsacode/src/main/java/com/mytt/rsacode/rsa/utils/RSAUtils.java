package com.mytt.rsacode.rsa.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils {
    //非对称密钥算法
    public static final String KEY_ALGORITHM="RSA";
    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     * */
    private static final int KEY_SIZE=2048;
    //公钥
    public static final String PUBLIC_KEY="public_key";

    //私钥
    public static final String PRIVATE_KEY="private_key";

    /**
     * 初始化密钥对
     * @return Map 甲方密钥的Map
     * */
    public static Map<String,String> initKey() throws Exception{
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey publicKey=(RSAPublicKey) keyPair.getPublic();
        System.out.println("系数："+publicKey.getModulus()+"  加密指数："+publicKey.getPublicExponent());
        //甲方私钥
        RSAPrivateKey privateKey=(RSAPrivateKey) keyPair.getPrivate();
        System.out.println("系数："+privateKey.getModulus()+"解密指数："+privateKey.getPrivateExponent());
        //将密钥存储在map中
        Map<String,String> keyMap=new HashMap<String,String>();
        keyMap.put(PUBLIC_KEY, Base64.encodeBase64String(publicKey.getEncoded()));
        keyMap.put(PRIVATE_KEY, Base64.encodeBase64String(privateKey.getEncoded()));
        return keyMap;

    }
    /**
     * 私钥加密
     * @param data 待加密数据
     * @param privateKey 密钥
     * @return byte[] 加密数据
     * */
    public static String encryptByPrivateKey(String data,String privateKey) throws Exception{
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec=new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey priKey=keyFactory.generatePrivate(pkcs8KeySpec);
        //数据加密
        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
    }
    /**
     * 公钥加密
     * @param data 待加密数据
     * @param publicKey 密钥
     * @return byte[] 加密数据
     * */
    public static String encryptByPublicKey(String data,String publicKey) throws Exception{
        //实例化密钥工厂
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        //产生公钥
        PublicKey pubKey=keyFactory.generatePublic(x509KeySpec);
        //数据加密
        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
    }
    /**
     * 私钥解密
     * @param data 待解密数据
     * @param privateKey 密钥
     * @return byte[] 解密数据
     * */
    public static String decryptByPrivateKey(String data,String privateKey) throws Exception{
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec=new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey priKey=keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(Base64.decodeBase64(data)));
    }

    /**
     * 公钥解密
     * @param data 待解密数据
     * @param publicKey 密钥
     * @return byte[] 解密数据
     * */
    public static String decryptByPublicKey(String data,String publicKey) throws Exception{
        //实例化密钥工厂
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        //产生公钥
        PublicKey pubKey=keyFactory.generatePublic(x509KeySpec);
        //数据解密
        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return new String(cipher.doFinal(Base64.decodeBase64(data)));
    }


    /**
     * 校验数字签名
     *
     * @param data  加密数据
     * @param publicKey 公钥
     * @param sign 数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(String data, String publicKey, String sign) throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(pubKey);
        signature.update(data.getBytes());
        // 验证签名是否正常
        return signature.verify(Base64.decodeBase64(sign));
    }

    /**
     * 用私钥对指定字符串进行签名
     * @param str 需要签名的字符串
     * @param privateKey 私钥
     */
    public static String sign(String str, String privateKey) throws Exception {
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(toPrivateKey(privateKey));
        signature.update(str.getBytes());
        return Base64.encodeBase64String(signature.sign());
    }
    /**
     * 根据私钥字符串生成私钥对象
     * @param privateKey 私钥字符串
     */
    private static PrivateKey toPrivateKey(String privateKey) throws Exception {
        // 对密钥解密
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(pkcs8KeySpec);
    }

}
