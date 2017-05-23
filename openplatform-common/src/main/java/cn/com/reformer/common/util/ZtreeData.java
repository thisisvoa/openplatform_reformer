package cn.com.reformer.common.util;

import java.util.List;

/**
 * 功能描述:
 * <p/>
 * 版权所有：杭州立方控股
 * <p/>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author jhon yang 新增日期：2015-09-08
 * @author jhon yang 修改日期：2015-09-08
 * @version 1.0.0
 * @since 1.0.0
 */
public class ZtreeData
{
    /**
     * 总公司
     */
    public static final String ICON_SKIN_CORPORATION = "iconcorp";
    /**
     * 部门
     */
    public static final String ICON_SKIN_DEPART = "icondept";
    /**
     * 部门
     */
    public static final String ICON_SKIN_DEPART_DISBALE = "icondept_disable";
    /**
     * 人员
     */
    public static final String ICON_SKIN_EMPLOYEE = "iconemp";
    /**
     * 门区
     */
    public static final String ICON_SKIN_DOOR_AREA = "iconarea";
    /**
     * 门
     */
    public static final String ICON_SKIN_DOOR = "icondoor";
    /**
     * 控制器
     */
    public static final String ICON_SKIN_STATION = "iconstn";
    /**
     * 控制器
     */
    public static final String ICON_SKIN_STATION_DISBALE = "iconstn_disable";
    /**
     * 控制器组
     */
    public static final String ICON_SKIN_STATION_GROUP = "iconstngrp";
    /**
     * 权限
     */
    public static final String ICON_SKIN_AUTHORITY = "iconauth";
    /**
     * 选择全部
     */
    public static final String ICON_SKIN_ALL = "iconall";
    /**
     * 调度器
     */
    public static final String ICON_SKIN_DISPATCH = "icondispatch";
    /**
     * 周计划
     */
    public static final String ICON_SKIN_WEEK_PLAN = "iconweekplan";
    /**
     * 权限组
     */
    public static final String ICON_SKIN_AUTHORITY_GROUP = "iconauthgrp";

    /**
     * 梯控设备组
     */
    public static final String ICON_SKIN_TK_GROUP = "icontkgroup";
    /**
     * 梯控设备
     */
    public static final String ICON_SKIN_TK_STATION = "icontkdevice";
    /**
     * 楼层
     */
    public static final String ICON_SKIN_FLOOR = "iconfloor";


    private Integer id;
    private Integer pId;
    private String name;
    private Boolean open = Boolean.FALSE;
    private Boolean checked = Boolean.FALSE;
    private Boolean isParent = Boolean.FALSE;
    private Boolean chkDisabled = Boolean.FALSE;
    private List<ZtreeData> children;
    private String iconSkin;
    private Boolean nocheck = Boolean.FALSE;

    private Double doorX;

    private Double doorY;

    private byte status;

    private String mapUrl;
    //设备类型
    private String devType;

    private String stationNo;

    private Integer deptGrpId;

    private Integer areaType;// 门区 类型： 1 ：普通门区，2：紧急门区


    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public Integer getDeptGrpId()
    {
        return deptGrpId;
    }

    public void setDeptGrpId(Integer deptGrpId)
    {
        this.deptGrpId = deptGrpId;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getpId()
    {
        return pId;
    }

    public void setpId(Integer pId)
    {
        this.pId = pId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getOpen()
    {
        return open;
    }

    public void setOpen(Boolean open)
    {
        this.open = open;
    }

    public Boolean getChecked()
    {
        return checked;
    }

    public void setChecked(Boolean checked)
    {
        this.checked = checked;
    }

    public Boolean getIsParent()
    {
        return isParent;
    }

    public void setIsParent(Boolean isParent)
    {
        this.isParent = isParent;
    }

    public Boolean getChkDisabled()
    {
        return chkDisabled;
    }

    public void setChkDisabled(Boolean chkDisabled)
    {
        this.chkDisabled = chkDisabled;
    }

    public List<ZtreeData> getChildren()
    {
        return children;
    }

    public void setChildren(List<ZtreeData> children)
    {
        this.children = children;
    }

    public Double getDoorX()
    {
        return doorX;
    }

    public void setDoorX(Double doorX)
    {
        this.doorX = doorX;
    }

    public Double getDoorY()
    {
        return doorY;
    }

    public void setDoorY(Double doorY)
    {
        this.doorY = doorY;
    }

    public String getIconSkin()
    {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin)
    {
        this.iconSkin = iconSkin;
    }

    public Boolean isNocheck()
    {
        return nocheck;
    }

    public void setNocheck(Boolean nocheck)
    {
        this.nocheck = nocheck;
    }

    public String getMapUrl()
    {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl)
    {
        this.mapUrl = mapUrl;
    }

    public byte getStatus()
    {
        return status;
    }

    public void setStatus(byte status)
    {
        this.status = status;
    }

    public String getDevType()
    {
        return devType;
    }

    public void setDevType(String devType)
    {
        this.devType = devType;
    }

    public String getStationNo()
    {
        return stationNo;
    }

    public void setStationNo(String stationNo)
    {
        this.stationNo = stationNo;
    }
}
