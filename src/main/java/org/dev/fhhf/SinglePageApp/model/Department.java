package org.dev.fhhf.SinglePageApp.model;

public class Department {

    private Integer dpIp;
    private String dpName;

    public Department() {
    }

    public Department(Integer dpIp, String dpName) {
        this.dpIp = dpIp;
        this.dpName = dpName;
    }

    public Integer getDpIp() {
        return dpIp;
    }

    public void setDpIp(Integer dpIp) {
        this.dpIp = dpIp;
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
                "dpIp=" + dpIp +
                ", dpName='" + dpName + '\'' +
                '}';
    }
}
