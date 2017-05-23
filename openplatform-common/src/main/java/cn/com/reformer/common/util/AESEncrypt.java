package cn.com.reformer.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESEncrypt {
	
    public static String encrypt(String bef_aes, String password) {
		byte[] byteContent = null;
		try {
			byteContent = bef_aes.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encrypt(byteContent, password);
	}

	public static String encrypt(byte[] content, String password) {
		try {
			SecretKey secretKey = getKey(password);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			String aft_aes = parseByte2HexStr(result);
			return aft_aes; // 加密
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

	public static String decrypt(String aft_aes, String password) {
		try {
			byte[] content = parseHexStr2Byte(aft_aes);
			SecretKey secretKey = getKey(password);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			String bef_aes = new String(result);
			return bef_aes; // 加密
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

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int value = Integer
					.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16);
			result[i] = (byte) value;
		}
		return result;
	}

	public static SecretKey getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes());
			_generator.init(128, secureRandom);
			return _generator.generateKey();
		} catch (Exception e) {
			throw new RuntimeException("初始化密钥出现异常");
		}
	}
	
//	public static String generateSequenceID() throws Exception {
//		String dateTime = DateOper.date2Str(new Date(), "yyyyMMddhhmmss");
//		String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
//		String ranEight = String.format("%08d", new Random().nextInt(99999999));
//		return dateTime + uuid + ranEight;
//	}

    /**
     * 
     * 对字符串根据摘要名进行摘要。SHA-256
     * 
     * @param encName
     *            摘要名
     * @param strSrc
     *            摘要目标字符串
     * @return 摘要结果字符串
     */
    public static String encrypt256(final String strSrc) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            // to HexString
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * 将byte数组转为16进制表示的字符串
     * 
     * @param bts
     *            源数组
     * @return 16进制表示的字符串
     */
    public static String bytes2Hex(byte[] bts)
    {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++)
        {
            tmp = Integer.toHexString(bts[i] & 0xFF);
            if (tmp.length() == 1)
            {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public static void main(String[] args) {
        String content = "718,840|20151123170410|13738071130|12D2DA551F45FAC8593054914DC1F76C";
        String password = "jQ1RDExNDkxNUU1QUZEMzRBRjUxNjg1QzI1MzMxQjQwQT";

        // String aa = String.valueOf(Base64.encodeBase64(content.getBytes()));
        // 加密
        System.out.println("加密前：" + content);
        String s = encrypt(content, password);
        System.out.println("加密前：" + s);
        String h = new String(Base64.encodeBase64(s.getBytes()));

        System.out.println("加密后：" + h);
        // 解密
        String h1 = new String(Base64.decodeBase64(h.getBytes()));
        String s1 = decrypt(h1, password);
        System.out.println("解密后：" + s1);
    }

}