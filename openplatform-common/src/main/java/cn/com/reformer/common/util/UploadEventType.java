package cn.com.reformer.common.util;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2015-11-26
 * @author jhon yang 修改日期：2015-11-26
 * @version 1.0.0
 * @since 1.0.0
 */
public enum UploadEventType
{
    IN_OUT_EVENT((byte) 1, "人员进出"),
    ALARM_EVENT((byte) 2, "警报"),
    CLOSE_CHANNEL_EVENT((byte) 3, "关闭通道"),
    CHANNEL_OTHER_EVENT((byte) 4, "通道其他操作"),
    CUSTOM_EVENT((byte) 5, "自定义输入");

    private final byte type;
    private final String name;

    UploadEventType(byte type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public byte getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }
}
