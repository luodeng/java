package com.roden.java.algorithm.encrypt;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class Rsa {
    public static final String src = "罗登";

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey rsaprivateKey = (RSAPrivateKey) keyPair.getPrivate();// 私有密钥
        RSAPublicKey rsapublicKey = (RSAPublicKey) keyPair.getPublic();// 公有密钥
        System.out.println("privateKey : " + Base64.encodeBase64String(rsaprivateKey.getEncoded()));
        System.out.println("publicKey : " + Base64.encodeBase64String(rsapublicKey.getEncoded()));

        // 私钥加密，公钥解密--加密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaprivateKey.getEncoded());
        KeyFactory privateKeyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = privateKeyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher privateCipher = Cipher.getInstance("RSA");
        privateCipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = privateCipher.doFinal(src.getBytes());
        System.out.println("私钥加密，公钥解密--加密:" + Base64.encodeBase64String(result));

        // 私钥加密，公钥解密--解密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsapublicKey.getEncoded());
        KeyFactory publicKeyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = publicKeyFactory.generatePublic(x509EncodedKeySpec);
        Cipher publicCipher = Cipher.getInstance("RSA");
        publicCipher.init(Cipher.DECRYPT_MODE, publicKey);
        result = publicCipher.doFinal(result);
        System.out.println("私钥加密，公钥解密--解密:" + new String(result));

        // 公钥加密，私钥解密---加密
        x509EncodedKeySpec = new X509EncodedKeySpec(rsapublicKey.getEncoded());
        publicKeyFactory = KeyFactory.getInstance("RSA");
        publicKey = publicKeyFactory.generatePublic(x509EncodedKeySpec);
        publicCipher = Cipher.getInstance("RSA");
        publicCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        result = publicCipher.doFinal(src.getBytes());
        System.out.println("公钥加密，私钥解密---加密:" + Base64.encodeBase64String(result));

        // 公钥加密，私钥解密---解密
        pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaprivateKey.getEncoded());
        privateKeyFactory = KeyFactory.getInstance("RSA");
        privateKey = privateKeyFactory.generatePrivate(pkcs8EncodedKeySpec);
        privateCipher = Cipher.getInstance("RSA");
        privateCipher.init(Cipher.DECRYPT_MODE, privateKey);
        result = privateCipher.doFinal(result);
        System.out.println("公钥加密，私钥解密---解密:" + new String(result));
    }

}
