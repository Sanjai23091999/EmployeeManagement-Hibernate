package com.i2i.dao;

import com.i2i.entity.Trainer;
import com.i2i.entity.Trainee;
import com.i2i.entity.Employee;
import com.i2i.dao.IEmployeeDao;
import java.util.List;
import java.util.LinkedList;

public interface IEmployeeDao<T extends Employee> {

    /**
     *used to insert Employee into List
     *@param employee {@link T} the employee object
     *@return {@link void}
     */
    public  void insertEmployee(T employee);

    /**
     * Retrive list of  All Employees  
     * @return {@link List} of {@link T}
     */   
    public List<T>  retriveAllEmployees();

    /**
     * Retrive  Employee with his id  
     * @param employeeId {@link String} id of employee  
     * @return {@link void} 
     */   
    public T retriveEmployeeById(String employeeId);

    /**
     * Remove  Employee with his id  
     * @param employeeId {@link String} id of employee  
     * @return {@link void} 
      
    public void deleteEmployeeById(String employeeId);*/  

    /**
     * used to update  Employee  
     * @param updateEmployee {@link Employee} the employee object 
     * @return {@link void} 
     */   
    public void updateEmployee(Employee updateEmployee);
  
}
 
