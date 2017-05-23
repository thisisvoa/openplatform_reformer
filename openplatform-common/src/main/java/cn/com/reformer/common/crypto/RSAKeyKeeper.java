package cn.com.reformer.common.crypto;

import org.apache.commons.lang.StringUtils;

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
public class RSAKeyKeeper
{
    private static final String QR_CODE_DEVICE_PUBLIC_KEY_BASE64_ENCODE = "";
    private static final String QR_CODE_DEVICE_PRIVATE_KEY_BASE64_ENCODE = "";
    private byte[] publicKeyByte;
    private byte[] privateKeyByte;

    public void init(KeeperType type) throws Exception
    {
        if (type == null)
            throw new Exception();

        if (KeeperType.VISITOR_QR_CODE.equals(type))
        {
            if (StringUtils.isNotEmpty(QR_CODE_DEVICE_PUBLIC_KEY_BASE64_ENCODE))
                publicKeyByte = Base64Util.decryptBASE64(QR_CODE_DEVICE_PUBLIC_KEY_BASE64_ENCODE.trim());
            if (StringUtils.isNotEmpty(QR_CODE_DEVICE_PRIVATE_KEY_BASE64_ENCODE))
                privateKeyByte = Base64Util.decryptBASE64(QR_CODE_DEVICE_PRIVATE_KEY_BASE64_ENCODE.trim());
        } else
            throw new Exception();
    }

    public byte[] getPublicKeyByte()
    {
        return publicKeyByte;
    }

    public byte[] getPrivateKeyByte()
    {
        return privateKeyByte;
    }

    public enum KeeperType
    {
        VISITOR_QR_CODE;
    }
}
