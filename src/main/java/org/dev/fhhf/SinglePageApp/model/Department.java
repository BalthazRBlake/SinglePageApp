package org.dev.fhhf.SinglePageApp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "Department details")
public class Department implements Serializable {

    private static final long serialVersionUD = 1L;

    @ApiModelProperty(notes = "Primary Key Autogenerated", example = "0")
    private Integer dpId;
    @ApiModelProperty(example = "Tech")
    private String dpName;

    public Department() {
    }

    public Department(Integer dpId, String dpName) {
        this.dpId = dpId;
        this.dpName = dpName;
    }

    public Integer getDpId() {
        return dpId;
    }

    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }

    public String getDpName() {
        return dpName;
    }

    public void setDpName(String dpName) {
        this.dpName = dpName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "dpIp=" + dpId +
                ", dpName='" + dpName + '\'' +
                '}';
    }
}
