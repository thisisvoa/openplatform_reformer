package cn.com.reformer.common.paymentUtil;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jiqinwei 新增日期：2017/3/21
 * @author jiqinwei 修改日期：2017/3/21
 * @version 1.0.0
 * @since 1.0.0
 */
public enum PayErrorCode {
    /*协议层*/
    SUCCESS(200, "请求成功"),
    ERROR(500,"服务器内部错误"),
    ERROR_502(502,"参数错误，请检查参数是否正确或完整"),

    ERROR_410(410,"没有对应人员"),
    ERROR_430(430,"密码错误"),
    ERROR_431(431,"挂失失败"),
    ERROR_432(432,"该人员没有卡号"),
    ERROR_433(433,"人员已经重新发过卡了"),
    ERROR_434(434,"该人员没有挂失卡"),
    ERROR_435(435,"解挂失败"),
    ERROR_436(436,"2次密码输入不同"),
    ERROR_437(437,"密码修改失败"),
    ERROR_438(438,"密码多次错误，账号已锁定，请在2小时候后重新操作"),

    /*业务层*/
    ERROR_501(501,"业务错误"),

    ERROR_503(503,"数据库操作错误"),
    ERROR_505(505,"授权码无效，提示用户刷新一维码/二维码，之后重新扫码支付"),
    ERROR_506(506,"需要用户输入密码、在一定时间内没有查询到支付成功、订单已撤销"),
    ERROR_507(507,"用户余额不足，换其他卡支付或是用现金支付"),
    ERROR_508(508,"表示用户用来支付的二维码已经过期，提示收银员重新扫一下用户微信“刷卡”里面的二维码"),
    ERROR_509(509,"支付请求API返回的数据签名验证失败，有可能数据被篡改了"),
    ERROR_510(510,"支付API系统返回失败，请检测Post给API的数据是否规范合法"),
    ERROR_511(511,"支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问"),
    ERROR_512(512,"充值失败，已退款"),
    ERROR_513(513,"退款失败，请到ocs管理员处手动处理"),
    ERROR_514(514,"转账失败"),
    ERROR_515(515,"没有余额账户，转账失败"),
    ERROR_516(516,"没有余额账户，可能是未发卡或者发卡时发生异常"),
    ERROR_517(517,"HttpClient初始化失败，请检查微信配置参数是否正确"),
    ;

    private final Integer code;
    private final String info;

    PayErrorCode(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
