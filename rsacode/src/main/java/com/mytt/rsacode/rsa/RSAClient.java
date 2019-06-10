package com.mytt.rsacode.rsa;

public class RSAClient {
    private String serverUrl;
    private String appId;
    private String privateKey;
    private String publicKey;
    private String charset;
    private RSARequest rsaRequest;

    public RSAClient(String serverUrl, String appId, String privateKey, String publicKey, String charset) {
        this.serverUrl = serverUrl;
        this.appId = appId;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.charset = charset;
    }


    public void execute(RSARequest rsaRequest){
        this.rsaRequest =rsaRequest;
    }


    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
