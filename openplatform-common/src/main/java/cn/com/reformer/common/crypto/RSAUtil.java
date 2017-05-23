package cn.com.reformer.common.crypto;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2016-10-20
 * @author jhon yang 修改日期：2016-10-20
 * @version 1.0.0
 * @since 1.0.0
 */
public class RSAUtil
{
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    public static final int KEY_SIZE = 1024;

    private String[] generateKeyPair() throws Exception
    {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        String[] keys = new String[2];
        keys[0] = Base64Util.encryptBASE64(publicKey.getEncoded());
        keys[1] = Base64Util.encryptBASE64(privateKey.getEncoded());
        System.out.println("RSAPublicKey：" + keys[0]);
        System.out.println("RSAPrivateKey：" + keys[1]);

        System.out.println("RSAPublicKey m：" + publicKey.getModulus().toString());
        System.out.println("RSAPublicKey e：" + publicKey.getPublicExponent().toString());
        System.out.println("RSAPrivateKey m：" + privateKey.getModulus().toString());
        System.out.println("RSAPrivateKey e：" + privateKey.getPrivateExponent().toString());
        return keys;
    }

    public static PublicKey getPublicKey(byte[] key_bytes) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key_bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static PrivateKey getPrivateKey(byte[] key_bytes) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key_bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] sign(byte[] content, PrivateKey privateKey) throws Exception
    {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(content);
        return signature.sign();
    }

    /**
     * 校验数字签名
     *
     * @param content
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] content, PublicKey publicKey, byte[] sign) throws Exception
    {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(content);
        // 验证签名是否正常
        return signature.verify(sign);
    }

    /**
     * 用私钥解密
     *
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] content, PrivateKey privateKey)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**
     * 用公钥加密
     *
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] content, PublicKey publicKey)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    public static void main(String[] args)
    {
        //B给A发送的信息
        byte[] plain = "tH97psirCH3zLEdES071Fg==".getBytes();
        System.out.println("原文：" + Arrays.toString(plain));
        RSAUtil rsaUtil = new RSAUtil();
        try
        {
            String keys[] = rsaUtil.generateKeyPair();
            PublicKey publicKey = RSAUtil.getPublicKey(Base64Util.decryptBASE64(keys[0]));
            PrivateKey privateKey = RSAUtil.getPrivateKey(Base64Util.decryptBASE64(keys[1]));

            Cipher cipher = Cipher.getInstance("RSA");
            //B用A的公钥把信息加密后发给A
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            cipher.update(plain);
            byte[] result1 = cipher.doFinal();
            System.out.println("加密结果：" + Base64Util.encryptBASE64(result1));

            //A得到B发过来的信息后用自己的私钥进行解密
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            cipher.update(result1);
            byte[] result2 = cipher.doFinal();
            System.out.println("解密结果：" + Base64Util.encryptBASE64(result2));
            System.out.println("摘要：" + Base64Util.encryptBASE64(RSAUtil.sign(plain,privateKey)));

            System.out.println("原文比较：" + Arrays.equals(result2, plain));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
