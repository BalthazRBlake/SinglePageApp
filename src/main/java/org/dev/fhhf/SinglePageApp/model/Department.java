package org.dev.fhhf.SinglePageApp.model;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUD = 1L;

    private Integer dpId;
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
