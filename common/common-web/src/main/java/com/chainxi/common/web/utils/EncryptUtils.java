package com.chainxi.common.web.utils;


import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class EncryptUtils {
    public static KeyPair generateRsaPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); // 初始化密钥生成器，并指定密钥长度为 2048 位
            return keyGen.generateKeyPair(); // 生成密钥对
        } catch (Exception ignored) {
            return null;
        }
    }

    public static byte[] encrypt(PublicKey publicKey, String original) {
        try {
            Cipher encryptCipher = Cipher.getInstance(publicKey.getAlgorithm());
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return encryptCipher.doFinal(original.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decrypt(PrivateKey privateKey, byte[] encryptedBytes) {
        try {
            Cipher decryptCipher = Cipher.getInstance(privateKey.getAlgorithm());
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            return decryptCipher.doFinal(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
