package org.dev.fhhf.SinglePageApp.model;

public class Employee {

    private Integer empId;
    private String empName;
    private Boolean empActive;
    private Integer emp_dpId;

    public Employee() {
    }

    public Employee(Integer empId, String empName, Boolean empActive, Integer emp_dpId) {
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

    public Integer getEmp_dpId() {
        return emp_dpId;
    }

    public void setEmp_dpId(Integer emp_dpId) {
        this.emp_dpId = emp_dpId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empActive=" + empActive +
                ", emp_dpId=" + emp_dpId +
                '}';
    }
}
