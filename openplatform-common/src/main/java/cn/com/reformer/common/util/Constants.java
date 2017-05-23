package cn.com.reformer.common.util;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2015-08-25
 * @author jhon yang 修改日期：2015-08-25
 * @version 1.0.0
 * @since 1.0.0
 */
public class Constants
{
    public static final String DEFAULT_ENCRYPT_SALT = "HZRF";
    public static final String CURRENT_ADMIN = "currentAdmin";
    public static final String CURRENT_EMPLOYEE = "currentEmployee";
    public static final String CURRENT_EMPLOYEE_SYSNO = "currentEmployeeSysNo";
    public static final String CURRENT_ADMIN_PERMISSION = "currentAdminPermission";

    public static final String MAIN_MENU_ID = "main_menu_id";
    public static final String SUB_MENU_ID = "sub_menu_id";
    public static final String WORK_MEAL_MENU_ID = "work_meal_menu_id";
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int DEFAULT_BATCH_OPT_NUM = 200;
    public static final int REPEAT_SEND_TIME = 3;
    public static final boolean ONLY_CARD_SUPPORT = false;

    public static final int MAIN_MENU = 1;
    public static final int SUB_MENU = 2;
    public static final int ADMINISTRATOR_ROLE_ID = 1;
    public static final int ADMINISTRATOR_ACCOUNT_ID = 1;
    public static final int TOP_PARENT_DEPART = 1;
    public static final int NO_DATA_QUERY_ID = -9999;

    public static final byte TABLE_RECORD_STATUS_NORMAL = 0;
    public static final byte TABLE_RECORD_STATUS_STOP = 1;
    public static final byte TABLE_RECORD_STATUS_DELETE = 2;
    public static final byte TABLE_RECORD_STATUS_LOCK = 3;

    public static final byte DEVICE_CONFIG_RELA_TYPE_DEV_GROUP = 1;
    public static final byte DEVICE_CONFIG_RELA_TYPE_PERMISSION_GROUP = 2;

    public static final int DOWNTASK_TYPE_REGISTER_PERSON = 1;//任务类型-注册权限
    public static final int DOWNTASK_TYPE_UNREGISTER_PERSON = 2;//任务类型-取消权限

    public static final int REGUSER_ISLOSE = 1;//权限挂失
    public static final int REGUSER_ISNORMAL = 0;//权限正常

    public static final String TEMP_FILE_PATH = "/file/temp";
    public static final String TEMPLATE_FILE_PATH = "/file/template";
    public static final String SUBSIDY_FILE_PATH = "/file/subsidy";
    public static final String SUBSIDY_TEMP_FILE_PATH = "/file/subsidy_temp";
    public static final String PRINTER_FILE_PATH = "/file/PrinterTemplate1";
    public static final String QRDroid_FILE_PATH = "/file/VisitorPrintTemplate";
    public static final String INSTALL_LODOP32 = "/file/PrinterTemplate1";
    public static final String FACE_PIC = "/file/facepic";

    public static final int REAL_TIME_DOWN_JOB = 1;    //实时下载任务
    public static final int REFRESH_CLOCK_JOB = 2;   //刷新时钟 任务
    public static final int DOWN_USER_QUALIFICATION_JOB = 3; //下载用户资格 任务
    public static final int SUMMARY_DATA_JOB = 4;//汇总数据任务
    public static final int COLLECTION_RECORD_JOB = 5;//自动采集记录任务
    public static final int RESTART_DEVICE_JOB = 6; //重启设备任务
    public static final int DATABASE_BACKUP_JOB = 7; //数据库备份任务

    public static final int MULTI_STOREY_TIME = 1; //多层权限继电器
    public static final int MONOLAYER_TIME = 2; //单层权限继电器
    public static final int BLUETOOTH_TIME = 3; //蓝牙继电器
    public static final int ORDINARY_TIME = 4; //普通继电器
    public static final int CALL_DELAY_TIME = 5; //召唤控制器延时

    public static final int CONSUMPTION_UNIFY_QUOTA = 1;  //统一定额
    public static final int CONSUMPTION_ByMeal_QUOTA = 2;  //餐时定额
    public static final int CONSUMPTION_MENU_QUOTA = 3;  //菜单定额

    public static final int DO_INSERT_SUCCESS = 1;  //插入执行成功

    public static final byte MORNING_MEAL_QUOTA = 1;  //早餐
    public static final byte NOON_MEAL_QUOTA = 2;  //午餐
    public static final byte DINNER_MEAL_QUOTA = 3;  //晚餐
    public static final byte SUPPER_MEAL_QUOTA = 4;  //夜餐
    public static final byte OTHER_MEAL_QUOTA = 5;  //其他时段

    public static final byte ELEVATOR_SWAPING_CARD_RECORD = 1;  //梯控刷卡记录
    public static final byte ELEVATOR_CALL_RECORD = 2;  //梯控召唤记录
    public static final byte ELEVATOR_ALARM_TYPR_RECORD = 3;  //梯控警报记录
    public static final byte ELEVATOR_OFF_NETWORK_RECORD = 4;  //梯控脱网记录

    public static final int ELEVATOR_ALL_TYPE = 1;  //所有警报类型
    public static final int ELEVATOR_ALARM_TYPE = 2;  //警报类型
    public static final int ELEVATOR_OFF_NETWORK_TYPE = 3;  //脱网类型

    public static final byte SERIAL_CALL_TYPE = 1;  //卡片类型
    public static final byte PHONE_CALL_TYPE = 9;  //手机号类型

    public static final String DEFAULT_PASSWORD = "123456";  //默认访客要求登录密码


    public static final int LIMITATION_VISITOR = 0;    //受限访客
    public static final int VIP_VISITOR = 1;          //vip访客

    public static final int USER_TYPE_MEMBER = 2;//考勤


    //payment
    public final static int CLOSED_ORDER = 2;//2已关单
    public final static int PLACING_ORDERS = 3;//3已下单
    public final static int PAYMENT_SUCCESS = 4;//4已支付
    public final static int PAYMENT_FAIL = 5;//5支付失败
    public final static int RECHARGE_SUCCESS = 6;//6充值成功
    public final static int RECHARGE_FAIL = 7;//7充值失败
    public final static int REFUND_SUCCESS = 8;//8退款成功
    public final static int REFUND_FAIL = 9;//9退款失败
    public final static int REVERSE = 10;//10撤销
    public static final int JSAPI = 1;//公众号充值
    public static final int SCANPAY = 2;//扫码支付

    public final static int CONSUMPTION_RECHARGE = 1;//消费充值
    public final static int WATER_RECHARGE = 2;//水控充值

    public final static int RECHARGE_WECHAT = 1;//微信充值
    public final static int RECHARGE_ALIPAY = 2;//支付宝充值
    public final static int REHCARGE_QUICK_PASS = 3;//银联闪付
    public final static int RECHARGE_TRANSFER = 4;//消费钱包转账
}
