package cn.com.reformer.common.util;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2016-04-21
 * @author jhon yang 修改日期：2016-04-21
 * @version 1.0.0
 * @since 1.0.0
 */
public class DownloadResultDisplay
{
    private Integer percent;
    private Integer totalCount;
    private Integer hasDoneCount;
    private Integer successCount;
    private Integer failedCount;

    private String empName;
    private String serialPhoneName;
    private String deviceName;
    private String weekPlanName;
    private String authGroupName;
    private byte result;
    private String resultDisplay;

    private Boolean complete = Boolean.FALSE;

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

    public String getDeviceName()
    {
        return deviceName;
    }

    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getWeekPlanName()
    {
        return weekPlanName;
    }

    public void setWeekPlanName(String weekPlanName)
    {
        this.weekPlanName = weekPlanName;
    }

    public String getAuthGroupName()
    {
        return authGroupName;
    }

    public void setAuthGroupName(String authGroupName)
    {
        this.authGroupName = authGroupName;
    }

    public byte getResult()
    {
        return result;
    }

    public void setResult(byte result)
    {
        this.result = result;
    }

    public String getResultDisplay()
    {
        return resultDisplay;
    }

    public void setResultDisplay(String resultDisplay)
    {
        this.resultDisplay = resultDisplay;
    }

    public Boolean isComplete()
    {
        return complete;
    }

    public void setComplete(Boolean complete)
    {
        this.complete = complete;
    }

    public void calculatePercent()
    {
        percent = (hasDoneCount * 100) / totalCount;
    }

    public String getEmpName()
    {
        return empName;
    }

    public void setEmpName(String empName)
    {
        this.empName = empName;
    }

    public String getSerialPhoneName()
    {
        return serialPhoneName;
    }

    public void setSerialPhoneName(String serialPhoneName)
    {
        this.serialPhoneName = serialPhoneName;
    }
}
