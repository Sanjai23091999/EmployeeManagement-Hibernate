package com.i2i.entity;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Employee {

    @Column(name = "Employee_Id", columnDefinition="VARCHAR(80)")  
    private String employeeId;
    private String employeeName;
    private LocalDate employeeDateOfBirth;
    private LocalDate employeeDateOfJoin;
    private long mobileNumber;
    private int activeEmployee;
        
    public String getEmployeeName() {
        return employeeName;
    }
   
    public String getEmployeeId() {
        return employeeId;
    }

    public long getEmployeeMobileNumber() {
        return mobileNumber;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
   
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public void setEmployeeDateOfBirth(LocalDate employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfJoin() {
        return employeeDateOfJoin;
    }

     public void setEmployeeDateOfJoin(LocalDate employeeDateOfJoin) {
        this.employeeDateOfJoin = employeeDateOfJoin;
    }
 
     public void setEmployeeMobileNumber(long mobileNumber ) {
        this.mobileNumber = mobileNumber;
    }

    public void setDeletedEmployee(int activeEmployee) {
        this.activeEmployee = activeEmployee;
    }
  
    public int getDeletedEmployee() {
        return activeEmployee;
    }

}
    
        
        