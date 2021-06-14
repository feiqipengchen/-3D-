package org.jeecg.modules.demo.customrecord.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 定制衣服记录
 * @Author: jeecg-boot
 * @Date:   2021-04-19
 * @Version: V1.0
 */
@Data
@TableName("customrecord")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="customrecord对象", description="定制衣服记录")
public class Customrecord implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**订单人*/
	@Excel(name = "订单人", width = 15)
    @ApiModelProperty(value = "订单人")
    private String dingdanren;
	/**衣服种类*/
	@Excel(name = "衣服种类", width = 15)
    @ApiModelProperty(value = "衣服种类")
    private String yifuzhonglei;
	/**衣服编号*/
	@Excel(name = "衣服编号", width = 15)
    @ApiModelProperty(value = "衣服编号")
    private Integer yifubianhao;
	/**材质编号*/
	@Excel(name = "材质编号", width = 15)
    @ApiModelProperty(value = "材质编号")
    private Integer caizhibainhao;
	/**衣服颜色r*/
	@Excel(name = "衣服颜色r", width = 15)
    @ApiModelProperty(value = "衣服颜色r")
    private Integer yifuyanser;
	/**衣服颜色g*/
	@Excel(name = "衣服颜色g", width = 15)
    @ApiModelProperty(value = "衣服颜色g")
    private Integer yifuyanseg;
	/**衣服颜色b*/
	@Excel(name = "衣服颜色b", width = 15)
    @ApiModelProperty(value = "衣服颜色b")
    private Integer yifuyanseb;
	/**衣服型号*/
	@Excel(name = "衣服型号", width = 15)
    @ApiModelProperty(value = "衣服型号")
    private String yifuxinghao;
	/**件数*/
	@Excel(name = "件数", width = 15)
    @ApiModelProperty(value = "件数")
    private Integer jianshu;
	/**完成*/
	@Excel(name = "完成", width = 15, dicCode = "state")
	@Dict(dicCode = "state")
    @ApiModelProperty(value = "完成")
    private String wancheng;
	/**订单日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "订单日期")
    private Date createTime;
}
