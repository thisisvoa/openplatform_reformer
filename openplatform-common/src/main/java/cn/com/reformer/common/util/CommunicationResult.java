package cn.com.reformer.common.util;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2015-12-04
 * @author jhon yang 修改日期：2015-12-04
 * @version 1.0.0
 * @since 1.0.0
 */
public enum CommunicationResult
{
    SUCCESS((byte) 0x00, "成功"),
    ERROR_CHECK_CRC((byte) 0x02, "CRC校验错误"),
    ERROR_COMMAND_INVALID((byte) 0x04, "发送命令错误"),
    ERROR_SEND_MESSAGE_LENGTH_INVALID((byte) 0x05, "发送信息长度校验错误"),
    ERROR_DECRYPTION_FAILED((byte) 0x08, "解密失败"),
    ERROR_CLEARTEXT_COMMAND_INVALID((byte) 0x09, "明文命令错误"),
    ERROR_CHECK_CHAR((byte) 0x64, "字符校验错误"),
    ERROR_CHAR_INVALID((byte) 0x65, "不合理的字符"),
    ERROR_CHECK_TIME_DATA((byte) 0x66, "时间数据校验错误"),
    ERROR_DATA_OUT_RANGE((byte) 0x67, "数据超出范围"),
    ERROR_CHECK_TIME_FORMAT((byte) 0x68, "时间格式校验错误"),
    ERROR_NO_MATCH_DATA_IN_DEVICE((byte) 0x69, "设备不存在相应的数据"),
    ERROR_READ_TIME((byte) 0x6A, "时间读取错误"),
    ERROR_CHECK_DATA_FORMAT((byte) 0x6B, "数据格式校验错误"),
    ERROR_FLASH_MEMORY_FULL_IN_DEVICE((byte) 0x6C, "设备Flash存储空间已满"),
    ERROR_URGENCY_STATUS((byte) 0x6D, "当前为紧急状态，不能执行本操作"),
    ERROR_FLASH_OPT_FAILED((byte) 0x6E, "设备Flash操作失败"),
    ERROR_OPEN_FUNCTION((byte) 0x6F, "功能不可开启"),
    ERROR_REG_CARD_NUM_ZERO((byte) 0x90, "注册卡数为0"),
    ERROR_NO_SESSION((byte) 0xEF, "设备没有连接"),
    ERROR_GET_RESULT_TIMEOUT((byte) 0xEE, "获取结果超时"),
    ERROR_MESSAGE_INVALID((byte) 0xED, "消息无效"),
    ERROR_PARAM_INVALID((byte) 0xEC, "参数无效"),
    ERROR_VERIFICATION_CODE((byte) 0xEB, "验证码错误，解密失败"),
    RECEIVE_FORWARD_DATA_INVALID((byte) 0xEA, "接收调度控制器转发的消息无效"),
    ERROR_EMP_NO_CARD((byte) 0xE8, "用户未发卡"),
    ERROR_EMP_CARD_LOST((byte) 0xE7, "卡片挂失"),
    ERROR_EMP_NOT_EXIST((byte) 0xE6, "用户不存在"),
    ERROR_DEV_NOT_EXIST((byte) 0xE5, "设备不存在"),
    ERROR_DOOR_NOT_EXIST((byte) 0xE4, "房门不存在"),
    FAILED((byte) 0xE9, "失败");

    private final byte result;
    private final String description;

    CommunicationResult(byte result, String description)
    {
        this.result = result;
        this.description = description;
    }

    public byte getResult()
    {
        return result;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString()
    {
        return "CommunicationResult{" +
                "result=" + result +
                ", description='" + description + '\'' +
                '}';
    }
}
