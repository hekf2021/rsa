package com.mytt.rsacode.rsa;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class GeneratorKey {
    //非对称密钥算法
    public static final String KEY_ALGORITHM="RSA";
    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     * */
    private static final int KEY_SIZE=2048;


    /**
     * 生成公钥，私钥；说明，公钥、私钥都是成对的。
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws Base64DecodingException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, Base64DecodingException {
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey publicKey=(RSAPublicKey) keyPair.getPublic();
        //甲方私钥
        RSAPrivateKey privateKey=(RSAPrivateKey) keyPair.getPrivate();
        System.out.println("公钥："+Base64.encode(publicKey.getEncoded()).replaceAll("\\n",""));
        System.out.println("私钥："+Base64.encode(privateKey.getEncoded()).replaceAll("\\n",""));
    }
}
