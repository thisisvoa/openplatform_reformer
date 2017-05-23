package cn.com.reformer.common.paymentUtil;

import java.util.Map;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jiqinwei 新增日期：2017/3/22
 * @author jiqinwei 修改日期：2017/3/22
 * @version 1.0.0
 * @since 1.0.0
 */
public class EmpRenturnMsg extends BaseReturnMsg {
    private Map<String,Object> MsgMap;


    public EmpRenturnMsg(int return_code ,String return_msg,int result_code,int err_code,String err_code_des ,Map<String,Object> MsgMap){
        super( return_code , return_msg, result_code, err_code, err_code_des);
        this.setMsgMap(MsgMap);
    }

    public Map<String, Object> getMsgMap() {
        return MsgMap;
    }

    public void setMsgMap(Map<String, Object> msgMap) {
        MsgMap = msgMap;
    }
}
