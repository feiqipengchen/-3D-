package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Description: 定制衣服记录
 * @Author: jeecg-boot
 * @Date:   2021-04-14
 * @Version: V1.0
 */
@Data
@TableName("customrecord")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="customrecord对象", description="定制衣服记录")
public class UserCustomRecordEntity implements Serializable {
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
	@Excel(name = "完成", width = 15)
    @ApiModelProperty(value = "完成")
    private String wancheng;
    @ApiModelProperty(value = "订单日期")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDingdanren() {
        return dingdanren;
    }

    public void setDingdanren(String dingdanren) {
        this.dingdanren = dingdanren;
    }

    public String getYifuzhonglei() {
        return yifuzhonglei;
    }

    public void setYifuzhonglei(String yifuzhonglei) {
        this.yifuzhonglei = yifuzhonglei;
    }

    public Integer getYifubianhao() {
        return yifubianhao;
    }

    public void setYifubianhao(Integer yifubianhao) {
        this.yifubianhao = yifubianhao;
    }

    public Integer getCaizhibainhao() {
        return caizhibainhao;
    }

    public void setCaizhibainhao(Integer caizhibainhao) {
        this.caizhibainhao = caizhibainhao;
    }

    public Integer getYifuyanser() {
        return yifuyanser;
    }

    public void setYifuyanser(Integer yifuyanser) {
        this.yifuyanser = yifuyanser;
    }

    public Integer getYifuyanseg() {
        return yifuyanseg;
    }

    public void setYifuyanseg(Integer yifuyanseg) {
        this.yifuyanseg = yifuyanseg;
    }

    public Integer getYifuyanseb() {
        return yifuyanseb;
    }

    public void setYifuyanseb(Integer yifuyanseb) {
        this.yifuyanseb = yifuyanseb;
    }

    public String getYifuxinghao() {
        return yifuxinghao;
    }

    public void setYifuxinghao(String yifuxinghao) {
        this.yifuxinghao = yifuxinghao;
    }

    public Integer getJianshu() {
        return jianshu;
    }

    public void setJianshu(Integer jianshu) {
        this.jianshu = jianshu;
    }

    public String getWancheng() {
        return wancheng;
    }

    public void setWancheng(String wancheng) {
        this.wancheng = wancheng;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserCustomRecordEntity{" +
                "id='" + id + '\'' +
                ", dingdanren='" + dingdanren + '\'' +
                ", yifuzhonglei='" + yifuzhonglei + '\'' +
                ", yifubianhao=" + yifubianhao +
                ", caizhibainhao=" + caizhibainhao +
                ", yifuyanser=" + yifuyanser +
                ", yifuyanseg=" + yifuyanseg +
                ", yifuyanseb=" + yifuyanseb +
                ", yifuxinghao='" + yifuxinghao + '\'' +
                ", jianshu=" + jianshu +
                ", wancheng='" + wancheng + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
