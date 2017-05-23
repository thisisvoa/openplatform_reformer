package cn.com.reformer.common.util;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2015-10-15
 * @author jhon yang 修改日期：2015-10-15
 * @version 1.0.0
 * @since 1.0.0
 */
public enum DeviceType   //appType 应用类型
{
    DEVICE_MJ("A", "device.type.mj"),
    DEVICE_KQ("K", "device.type.kq"),
    DEVICE_XF_IC("C", "device.type.xf.ic"),
    DEVICE_XF_ID("D", "device.type.xf.id"),
    DEVICE_TK("G", "device.type.tk"),//梯控 G 表示 ID 电梯控制器
    DEVICE_TK_DD("O", "device.type.tk.dd"),//梯控 调度控制器
    DEVICE_USB("U", "device.type.usb"),
    DEVICE_TK_DT_IC("H", "device.type.tk.dt"),//搜索  H 表示IC 电梯控制器
    DEVICE_TC("T", "device.type.tc"),
    DEVICE_SK("W", "device.type.sk"),//水控
    DEVICE_FK("V","device.type.fk"),//访客控制器
    DEVICE_QY("X","device.type.qy");//区域控制器

    private final String type;
    private final String name;

    DeviceType(String type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "DeviceType{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
