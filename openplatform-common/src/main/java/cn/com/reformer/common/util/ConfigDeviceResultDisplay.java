package cn.com.reformer.common.util;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2015-12-11
 * @author jhon yang 修改日期：2015-12-11
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigDeviceResultDisplay
{
    public static final int OPT_FORMAT_GROUP = 1;
    public static final int OPT_FORMAT_HOLIDAY_PLAN = 2;
    public static final int OPT_FORMAT_HOLIDAY = 3;
    public static final int OPT_FORMAT_WEEK_PLAN = 4;
    public static final int OPT_FORMAT_TIMEZONE = 5;
    public static final int OPT_DOWNLOAD_TIMEZONE = 6;
    public static final int OPT_DOWNLOAD_WEEK_PLAN = 7;
    public static final int OPT_DOWNLOAD_HOLIDAY = 8;
    public static final int OPT_DOWNLOAD_HOLIDAY_PLAN = 9;
    public static final int OPT_DOWNLOAD_GROUP = 10;
    public static final int OPT_DELETE_GROUP = 11;
    public static final int OPT_DELETE_HOLIDAY_PLAN = 12;
    public static final int OPT_DELETE_HOLIDAY = 13;
    public static final int OPT_DELETE_WEEK_PLAN = 14;
    public static final int OPT_DELETE_TIMEZONE = 15;
    private Integer percent;
    private Integer totalCount;
    private Integer hasDoneCount;
    private Integer successCount;
    private Integer failedCount;

    private String stationName;
    private Integer optType;
    private String optObject;
    private byte result;

    public Integer getPercent()
    {
        return percent;
    }

    public void setPercent(Integer percent)
    {
        this.percent = percent;
    }

    public Integer getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount)
    {
        this.totalCount = totalCount;
    }

    public Integer getHasDoneCount()
    {
        return hasDoneCount;
    }

    public void setHasDoneCount(Integer hasDoneCount)
    {
        this.hasDoneCount = hasDoneCount;
    }

    public Integer getSuccessCount()
    {
        return successCount;
    }

    public void setSuccessCount(Integer successCount)
    {
        this.successCount = successCount;
    }

    public Integer getFailedCount()
    {
        return failedCount;
    }

    public void setFailedCount(Integer failedCount)
    {
        this.failedCount = failedCount;
    }

    public String getStationName()
    {
        return stationName;
    }

    public void setStationName(String stationName)
    {
        this.stationName = stationName;
    }

    public Integer getOptType()
    {
        return optType;
    }

    public void setOptType(Integer optType)
    {
        this.optType = optType;
    }

    public String getOptObject()
    {
        return optObject;
    }

    public void setOptObject(String optObject)
    {
        this.optObject = optObject;
    }

    public byte getResult()
    {
        return result;
    }

    public void setResult(byte result)
    {
        this.result = result;
    }

    public void calculatePercent()
    {
        percent = (hasDoneCount * 100) / totalCount;
    }
}
