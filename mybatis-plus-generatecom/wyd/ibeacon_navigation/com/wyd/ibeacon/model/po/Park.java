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
public class Park implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    private String id;

    /**
     * 停车场名称
     */
    private String parkName;

    /**
     * 停车场描述信息
     */
    private String detail;

    /**
     * 灯的数量
     */
    private Integer lampCount;

    /**
     * 拓展字段
     */
    private String extendField;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
