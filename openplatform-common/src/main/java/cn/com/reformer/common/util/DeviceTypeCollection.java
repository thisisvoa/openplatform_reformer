package cn.com.reformer.common.util;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jiqinwei 新增日期：2016/11/29
 * @author jiqinwei 修改日期：2016/11/29
 * @version 1.0.0
 * @since 1.0.0
 */
public class DeviceTypeCollection {
    String  device_type_AX50 = "1";
    String  device_type_AC70 = "2";
    String  device_type_AC35 = "4";
    public String rxtxDev ;
    public String udpDev;

    /**
     * 串口设备类型
     * @return
     */
    public String getRxtxDevice() {
        rxtxDev +=device_type_AC70;
        return rxtxDev;
    }

    /**
     * udp设备类型
     * @return
     */
    public String getUdpDevice() {
        udpDev += device_type_AX50 + device_type_AC35;
        return udpDev;
    }
}
