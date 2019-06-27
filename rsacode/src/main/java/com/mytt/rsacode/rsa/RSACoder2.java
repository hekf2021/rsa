package com.mytt.rsacode.rsa;

import com.mytt.rsacode.rsa.utils.RSAUtils;

import java.util.Map;

public class RSACoder2 {

      

    /**  
     * @param args  
     * @throws Exception   
     */    
    public static void main(String[] args) throws Exception {    
        //初始化密钥    
        //生成密钥对    
        Map<String,String> keyMap= RSAUtils.initKey();
        //公钥    
        String publicKey= keyMap.get(RSAUtils.PUBLIC_KEY);
        //byte[] publicKey = b;  
        //私钥    
        String privateKey= keyMap.get(RSAUtils.PRIVATE_KEY);
        System.out.println("公钥："+ publicKey);
        System.out.println("私钥："+privateKey);


        System.out.println("================密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输=============");    
        String str="aattaggcctegthththfef/aat.mp4";    
        System.out.println("===========甲方向乙方发送加密数据==============");    
        System.out.println("原文:"+str);    
        //甲方进行数据的加密    
        String code1= RSAUtils.encryptByPublicKey(str, publicKey);
        //String code1= RSAUtils.encryptByPrivateKey(str, privateKey);
        System.out.println("甲方 使用乙方公钥加密后的数据："+code1);
        System.out.println("===========乙方使用甲方提供的公钥对数据进行解密==============");    
        //乙方进行数据的解密    
        String decode1= RSAUtils.decryptByPrivateKey(code1, privateKey);
        //String decode1= RSAUtils.decryptByPublicKey(code1, publicKey);
        System.out.println("乙方解密后的数据："+new String(decode1)+"");    
            
        System.out.println("===========反向进行操作，乙方向甲方发送数据==============");
        str="乙方向甲方发送数据RSA算法";
        System.out.println("原文:"+str);    
            

          
          
    }    
} 