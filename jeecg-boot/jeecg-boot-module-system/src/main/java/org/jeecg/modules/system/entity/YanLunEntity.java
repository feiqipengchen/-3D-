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

public class YanLunEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String yifuzhonglei;
    private Integer yifubianhao;
    private  Integer caizhibianhao;
    private String author;
    private Integer pingfen;
    private String content;
    private String datetime;

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

    public Integer getCaizhibianhao() {
        return caizhibianhao;
    }

    public void setCaizhibianhao(Integer caizhibianhao) {
        this.caizhibianhao = caizhibianhao;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPingfen() {
        return pingfen;
    }

    public void setPingfen(Integer pingfen) {
        this.pingfen = pingfen;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "YanLunEntity{" +
                "yifuzhonglei='" + yifuzhonglei + '\'' +
                ", yifubianhao=" + yifubianhao +
                ", caizhibianhao=" + caizhibianhao +
                ", author='" + author + '\'' +
                ", pingfen=" + pingfen +
                ", content='" + content + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
