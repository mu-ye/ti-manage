package com.huan.demo.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 整个文件是借鉴 mybatisPlus中的AES加解密
 * @author mubaisama
 */
@Slf4j
public class AesUtil {
    public static final String AES_KEY = "c6c179101e5d3065";

    private static final String AES = "AES";
    private static final String UTF_8 = "UTF-8";
    private static final String AES_CBC_CIPHER = "AES/CBC/PKCS5Padding";
    /**
     * url 后面添加的 token
     */
    private static final String AUTHENTICATION_TOKEN = "?entranceAuthenticationToken=";

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key  加密密码
     * @return
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, AES);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(key));
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new MybatisPlusException(e);
        }
    }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key  解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, AES);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(key));
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new MybatisPlusException(e);
        }
    }

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key  加密密码
     * @return
     */
    public static String encrypt(String data, String key) {
        try {
            byte[] valueByte = encrypt(data.getBytes(UTF_8), key.getBytes(UTF_8));
            return Base64.getEncoder().encodeToString(valueByte);
        } catch (UnsupportedEncodingException e) {
            throw new MybatisPlusException(e);
        }
    }

    /**
     * 解密
     *
     * @param data 待解密内容 base64 字符串
     * @param key  解密密钥
     * @return
     */
    public static String decrypt(String data, String key) {
        try {
            byte[] originalData = Base64.getDecoder().decode(data.getBytes());
            byte[] valueByte = decrypt(originalData, key.getBytes(UTF_8));
            return new String(valueByte);
        } catch (UnsupportedEncodingException e) {
            throw new MybatisPlusException(e);
        }
    }

    /**
     * 生成一个随机字符串密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateRandomKey() {
        return IdWorker.get32UUID().substring(0, 16);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("123",AES_KEY));

        // 生成key 加密 解密
/*        String key = generateRandomKey();
        log.info("key",key);
        String jobNumber = "123";
        String jobNumberEn = encrypt(jobNumber, key);
        String jobNumberDe = decrypt(jobNumberEn, key);
        log.info("key {}", key);
        log.info("encrypt {}", jobNumberEn);
        log.info("decrypt {}", jobNumberDe);

        // 系统中是否需要集成单点登录  card 中 sign
        Boolean sign = true;
        String url = "http://baidu.com";
        if (sign) {
            // 需要添加 后缀 ?entranceAuthenticationToken
            url += AUTHENTICATION_TOKEN + jobNumberEn;
            log.info("集成单点登录的 url {}", url);
        }

        // 根据用户注册生成的key，来解析获取用户工号
        String token = url.split("entranceAuthenticationToken=")[1];
        String getJobNumber = decrypt(token, key);
        log.info("通过URL获取到的用户工号为 {}", getJobNumber);*/
    }
}