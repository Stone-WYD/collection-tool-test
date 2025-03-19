package com.wyd.ibeacon.model.po;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyd
 * @since 2025-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Ibeacon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 所属停车场
     */
    private String parkId;

    /**
     * 信标id，对应具体确定的一个灯
     */
    private String ibeaconId;

    /**
     * 地图上的x坐标
     */
    private String locationX;

    /**
     * 地图上的y坐标
     */
    private String locationY;

    /**
     * 当前信标周围一步可达信标，这里的值是本表id，多个信标请以英文逗号隔开
     */
    private String aroundIbeacon;

    /**
     * 描述信息，如果是出入口点可能会需要使用
     */
    private String detail;

    /**
     * 点类型，暂时没用到（可能是出口、入口、普通点灯）
     */
    private Integer type;

    /**
     * 扩展字段1
     */
    private String extendField1;

    /**
     * 扩展字段2
     */
    private String extendField2;

    /**
     * 扩展字段3
     */
    private String extendField3;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 插入时间
     */
    private LocalDateTime createTime;

    /**
     * 最新一次更新人
     */
    private String updatePerson;


}
