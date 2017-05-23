package cn.com.reformer.common.util;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2016-09-23
 * @author jhon yang 修改日期：2016-09-23
 * @version 1.0.0
 * @since 1.0.0
 */
public enum ModelType
{
    MODEL_HUMAN_RESOURCES(11, "permission.hr.system"),
    MODEL_ENTRANCE_GUARD(12, "permission.mj.system"),
    MODEL_ATTENDANCE(13, "permission.kq.system"),
    MODEL_CONSUME(14, "permission.xf.system"),
    MODEL_ELEVATOR(15, "permission.tk.system"),
    MODEL_WATER(16, "permission.sk.system"),
    MODEL_VISITOR(20, "permission.fk.system"),
    MODEL_SECURITY(95, "permission.security.system"),
    MODEL_SYSTEM(99, "permission.sys.system");

    private final int type;
    private final String name;

    ModelType(int type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public int getType()
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
        return "ModelType{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
