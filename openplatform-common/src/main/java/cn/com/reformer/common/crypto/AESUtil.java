package cn.com.reformer.common.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * <p/>
 * 算法/模式/填充 16字节加密后数据长度 不满16字节加密后长度
 * AES/CBC/NoPadding 16 不支持
 * AES/CBC/PKCS5Padding 32 16
 * AES/CBC/ISO10126Padding 32 16
 * AES/CFB/NoPadding 16 原始数据长度
 * AES/CFB/PKCS5Padding 32 16
 * AES/CFB/ISO10126Padding 32 16
 * AES/ECB/NoPadding 16 不支持
 * AES/ECB/PKCS5Padding 32 16
 * AES/ECB/ISO10126Padding 32 16
 * AES/OFB/NoPadding 16 原始数据长度
 * AES/OFB/PKCS5Padding 32 16
 * AES/OFB/ISO10126Padding 32 16
 * AES/PCBC/NoPadding 16 不支持
 * AES/PCBC/PKCS5Padding 32 16
 * AES/PCBC/ISO10126Padding 32 16
 * <p/>
 * 1、JCE中AES支持五中模式：CBC，CFB，ECB，OFB，PCBC；支持三种填充：NoPadding，PKCS5Padding，ISO10126Padding。 不带模式和填充来获取AES算法的时候，其默认使用ECB/PKCS5Padding。
 * 2、Java支持的密钥长度：keysize must be equal to 128, 192 or 256
 * 3、Java默认限制使用大于128的密钥加密（解密不受限制），报错信息：java.security.InvalidKeyException: Illegal key size or default parameters
 * 4、下载并安装JCE Policy文件即可突破128密钥长度的限制：覆盖jre\lib\security目录下local_policy.jar、US_export_policy.jar文件即可
 * 5、除ECB外，需提供初始向量（IV），如：Cipher.init(opmode, key, new IvParameterSpec(iv)), 且IV length: must be 16 bytes long
 *
 * @author jhon yang 新增日期：2016-10-20
 * @author jhon yang 修改日期：2016-10-20
 * @version 1.0.0
 * @since 1.0.0
 */
public class AESUtil
{
    public static final String ALGORITHM = "AES";
    public static final String TRANSFORMATION_AES_ECB_PKCS5PADDING = "AES/ECB/PKCS5Padding";
    public static final String TRANSFORMATION_AES_ECB_NOPADDING = "AES/ECB/NoPadding";

    /**
     * 生成随机密钥
     *
     * @return
     * @throws Exception
     */
    public static SecretKey generateKey(int keySize) throws NoSuchAlgorithmException
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(keySize, new SecureRandom());
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    /**
     * 生成随机密钥
     *
     * @return
     * @throws Exception
     */
    public static SecretKey generateKey() throws NoSuchAlgorithmException
    {
        return generateKey(128);
    }

    /**
     * 生成固定密钥
     *
     * @param seed 作为种子，生成对应的密钥
     * @return
     * @throws Exception
     */
    public static SecretKey generateKey(int keySize, byte[] seed) throws NoSuchAlgorithmException
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(keySize, new SecureRandom(seed));
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    /**
     * 生成固定密钥
     *
     * @param password 作为种子，生成对应的密钥
     * @return
     * @throws Exception
     */
    public static SecretKey generateKey(int keySize, String password) throws NoSuchAlgorithmException
    {
        return generateKey(keySize, password.getBytes());
    }

    /**
     * 生成固定密钥
     *
     * @param password 作为种子，生成对应的密钥
     * @return
     * @throws Exception
     */
    public static SecretKey generateKey(String password) throws NoSuchAlgorithmException
    {
        return generateKey(128, password);
    }

    public static byte[] encrypt(byte[] content, byte[] key) throws Exception
    {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_ECB_PKCS5PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, ALGORITHM));
        byte[] output = cipher.doFinal(content);
        return output;
    }

    /**
     * 执行加密
     *
     * @param content
     * @param password 作为种子，生成对应的密钥
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] content, String password) throws Exception
    {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_ECB_PKCS5PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(password));
        byte[] output = cipher.doFinal(content);
        return output;
    }

    public static byte[] encryptForNoPadding(byte[] content, byte[] secretKey) throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(secretKey, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_ECB_NOPADDING);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        int length = content.length;
        int div = length / 16;
        int mod = length % 16;
        //每16字节做一段加密
        int i = 0;
        byte[] encryptedBytes = null;
        if (mod == 0)
            encryptedBytes = new byte[length];
        else
            encryptedBytes = new byte[(div + 1) * 16];
        for (i = 0; i < div; i++)
        {
            byte[] i_bytes = new byte[16];
            System.arraycopy(content, i * 16, i_bytes, 0, 16);
            byte[] o_bytes = cipher.doFinal(i_bytes);
            System.arraycopy(o_bytes, 0, encryptedBytes, i * 16, 16);
        }

        if (mod != 0) //剩余数据再处理
        {
            byte[] i_bytes = new byte[16];
            for (int j = 0; j < 16; j++)
            {
                if (j < mod)
                {
                    i_bytes[j] = content[i * 16 + j];
                } else
                {
                    i_bytes[j] = 0;
                }
            }
            byte[] o_bytes = cipher.doFinal(i_bytes);
            System.arraycopy(o_bytes, 0, encryptedBytes, i * 16, 16);
        }
        return encryptedBytes;
    }

    /**
     * 执行解密
     *
     * @param content
     * @param key     长度必须为16、24、32位，即128bit、192bit、256bit
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] content, byte[] key) throws Exception
    {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_ECB_PKCS5PADDING);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, ALGORITHM));
        byte[] output = cipher.doFinal(content);
        return output;
    }

    /**
     * 执行解密
     *
     * @param content
     * @param password
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] content, String password) throws Exception
    {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_ECB_PKCS5PADDING);
        cipher.init(Cipher.DECRYPT_MODE, generateKey(password));
        byte[] output = cipher.doFinal(content);
        return output;
    }

    public static byte[] decryptForNoPadding(byte[] content, byte[] secretKey) throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(secretKey, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_ECB_NOPADDING);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(content);
    }

    public static byte[] randomGenerator() throws Exception
    {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[4];
        random.nextBytes(bytes);
        return bytes;
    }

    public static void main(String[] args)
    {
        try
        {
            String content = "1234567891234567";
            byte[] c_1 = content.getBytes();
            SecretKey secretKey = AESUtil.generateKey(128);
            System.out.println(Arrays.toString(secretKey.getEncoded()));
            System.out.println(Base64Util.encryptBASE64(secretKey.getEncoded()));
            System.out.println(Arrays.equals(Base64Util.decryptBASE64(Base64Util.encryptBASE64(secretKey.getEncoded())), secretKey.getEncoded()));
            System.out.println("------------------------");
            byte[] c_2 = encryptForNoPadding(c_1, secretKey.getEncoded());
            System.out.println(Base64Util.encryptBASE64(c_2));
            System.out.println(Base64Util.encryptBASE64(encryptForNoPadding(c_1, secretKey.getEncoded())));
            randomGenerator();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
