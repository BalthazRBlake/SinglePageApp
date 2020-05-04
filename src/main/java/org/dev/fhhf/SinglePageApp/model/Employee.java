package org.dev.fhhf.SinglePageApp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "Employee details")
public class Employee implements Serializable {

    private static final long serialVersionUD = 1L;

    @ApiModelProperty(notes = "Primary Key Autogenerated")
    private Integer empId;
    @ApiModelProperty(example = "Frank")
    private String empName;
    @ApiModelProperty(example = "true")
    private Boolean empActive;
    private Department emp_dpId;

    public Employee() {
    }

    public Employee(Integer empId) {
        this.empId = empId;
    }

    public Employee(Integer empId, String empName, Boolean empActive, Department emp_dpId) {
        this.empId = empId;
        this.empName = empName;
        this.empActive = empActive;
        this.emp_dpId = emp_dpId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Boolean getEmpActive() {
        return empActive;
    }

    public void setEmpActive(Boolean empActive) {
        this.empActive = empActive;
    }

    public Department getEmp_dpId() {
        return emp_dpId;
    }

    public void setEmp_dpId(Department emp_dpId) {
        this.emp_dpId = emp_dpId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName +
                ", empActive=" + empActive +
                ", emp_dpId=" + emp_dpId +
                '}';
    }
}
