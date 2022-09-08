package com.i2i.dto;
import java.time.LocalDate;
import java.time.Period;

public class  EmployeeDto{

    private String EmployeeName;
    private String EmployeeId;
    private LocalDate EmployeeDateOfBirth;
    private LocalDate EmployeeDateOfJoin;
    private long mobileNumber;
        
    public String getEmployeeName() {
        return EmployeeName;
    }
   
    public String getEmployeeId() {
        return EmployeeId;
    }

    public long getEmployeeMobileNumber() {
        return mobileNumber;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }
   
    public void setEmployeeId(String EmployeeId) {
        this.EmployeeId = EmployeeId;
    }
    
    public void setEmployeeDateOfBirth(LocalDate EmployeeDateOfBirth) {
        this.EmployeeDateOfBirth = EmployeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfBirth() {
        return EmployeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfJoin() {
        return EmployeeDateOfJoin;
    }

    public void setEmployeeDateOfJoin(LocalDate EmployeeDateOfJoin) {
        this.EmployeeDateOfJoin = EmployeeDateOfJoin;
    }


    public void setEmployeeMobileNumber(long mobileNumber ) {
        this.mobileNumber = mobileNumber;
    }

    public int getAgeFromDateOfBirth() {
        LocalDate currentDate = LocalDate.now();
        return (Period.between(EmployeeDateOfBirth, currentDate).getYears());
    }

    public int getExperienceFromDateOfJoin() {
        LocalDate currentDate = LocalDate.now();
        return (Period.between(EmployeeDateOfJoin, currentDate).getYears());
    }

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
       
        stringBuilder.append("Name :").append(this.getEmployeeName()).append("\nID :")
                     .append(this.getEmployeeId()).append("\nMobile No :")
                     .append(this.getEmployeeMobileNumber()).append("\nAge :")
                     .append(this.getAgeFromDateOfBirth()).append("\nExperience :")
                     .append(this.getExperienceFromDateOfJoin());
        
        return stringBuilder.toString(); 
    }  
}
