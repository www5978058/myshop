package com.wzh.myshop.commons.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 加解密工具类
 * @author wzh
 * @date 2019/9/22 - 14:55
 */
public class MyEAESUtil {
    private static final String keyWord = "myshopEAES";

    /**
     *
     * @param content 需要加密的内容
     * @return byte[] 加密后的字节数组
     */
    public static byte[] encrypt(String content) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keyWord.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param content 加密后的内容
     * @return String 加密后的字符串
     */
    public static String encrypttoStr(String content) {
        return parseByte2HexStr(encrypt(content));
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @return byte[]
     */
    public static byte[] decrypt(byte[] content) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keyWord.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param content 待解密内容(字符串)
     * @return byte[]
     */
    public static byte[] decrypt(String content) {
        return decrypt(parseHexStr2Byte(content));
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return String
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return byte[]
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1){
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static int randomnums(String encryptResult) {
        Random randomX = new Random();
        int randomnum = 2 + randomX.nextInt(encryptResult.length() - 1);

        return randomnum;
    }

    public static String jiami(String content) {
        // 原始密文
        String randomnum = encrypttoStr(content);
        // 随机数
        int randomX = randomnums(randomnum);
        // 调换转换后的密文
        String hexrando = randomnum.substring(0, randomX);
        String hexranum = randomnum.substring(randomX);
        String hexrandonum = hexranum + hexrando;
        // 16进制随机数
        String num = Integer.toHexString(randomX);
        // 调换位子
        if (num.length() == 1) {
            num = 0 + num;
        }
        // 最终加密的密文
        String hexrandomX = num + hexrandonum;
        return hexrandomX;
    }

    public static String jiemi(String content) {
        try {
            // 随机数
            String num = content.substring(0, 2);
            int randomX = Integer.parseInt(num, 16);
            // 调换转换后的密文
            String hexrandonum = (content.substring(2));
            String hexrando = hexrandonum.substring(0, hexrandonum.length() - randomX);
            String hexranum = hexrandonum.substring(hexrandonum.length() - randomX);
            String randomnum = hexranum + hexrando;
            return new String(decrypt(randomnum));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {

        String mima = "123456";
        System.out.println("加密后：" + new String(MyEAESUtil.jiami(mima)));
        String jiemi = "10A2A8848E59E9E94F736D1947CA30B407";
        System.out.println(jiemi.length());
        System.out.println("解密后: "+ MyEAESUtil.jiemi(jiemi));
    }
}
