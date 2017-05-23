package cn.com.reformer.common.util;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2016-07-20
 * @author jhon yang 修改日期：2016-07-20
 * @version 1.0.0
 * @since 1.0.0
 */
public class TransactionNumberGenerate
{
    private static final TransactionNumberGenerate instance = new TransactionNumberGenerate();

    private TransactionNumberGenerate()
    {
    }

    public static final TransactionNumberGenerate getInstance()
    {
        return instance;
    }

    public String generate()
    {
        String date_str = DateUtils.formatDate(new Date(), DateUtils.FMT_YYMMDDHHMMSS_SSS);
        String date = date_str.substring(0, 6);
        String time = date_str.substring(6);
        return date + getTwoRandom() + time + getThreeRandom();
    }

    public String generateWithModelType(ModelType modelType)
    {
        int model_type;
        if (modelType == null)
            model_type = ModelType.MODEL_SYSTEM.getType();
        else
            model_type = modelType.getType();

        String date_str = DateUtils.formatDate(new Date(), DateUtils.FMT_YYMMDDHHMMSS_SSS);
        String date = date_str.substring(0, 6);
        String time = date_str.substring(6);
        return model_type + date + getTwoRandom() + time + getThreeRandom();
    }

    public String generateQRCodeSerial()
    {
        String date_str = DateUtils.formatDate(new Date(), DateUtils.FMT_YYMMDDHHMMSS_SSS);
//        String date = date_str.substring(0, 6);
//        String time = date_str.substring(6);
//        return date + time + getOneRandom();
        return date_str + getOneRandom();
    }

    public String generateQRCodeSerial(Date dateTime)
    {
        String date_str = DateUtils.formatDate(dateTime, DateUtils.FMT_YYMMDDHHMMSS_SSS);
        return date_str + getOneRandom();
    }

    private String getOneRandom()
    {
        SecureRandom random = new SecureRandom();
        int rdm = random.nextInt(100);
        String result = String.valueOf(rdm % 9);
        return result;
    }

    private String getTwoRandom()
    {
        Random random = new Random();
        String result = String.valueOf(random.nextInt(100));
        if (result.length() == 1)
            result = "0" + result;
        return result;
    }

    private String getThreeRandom()
    {
        Random random = new Random();
        String result = String.valueOf(random.nextInt(1000));
        if (result.length() == 1)
            result = "00" + result;
        else if (result.length() == 2)
            result = "0" + result;
        return result;
    }

    public static void main(String args[])
    {
        System.out.println(TransactionNumberGenerate.getInstance().generate());
        System.out.println(TransactionNumberGenerate.getInstance().generateQRCodeSerial());
    }
}
