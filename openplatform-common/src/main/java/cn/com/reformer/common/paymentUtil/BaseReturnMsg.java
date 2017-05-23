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
public class BaseReturnMsg {
    //协议层
    private int return_code;
    private String return_msg = "";
    //协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
    private int result_code ;
    private int err_code ;
    private String err_code_des = "";
    //业务返回的具体数据（以下字段在return_code 和result_code 都为SUCCESS 的时候有返回）


    public BaseReturnMsg(int return_code ,String return_msg,int result_code,int err_code,String err_code_des){
        setReturn_code(return_code);
        setReturn_msg(return_msg);
        setResult_code(result_code);
        setErr_code(err_code);
        setErr_code_des(err_code_des);
    }
    public int getReturn_code() {
        return return_code;
    }

    public void setReturn_code(int return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }
}
