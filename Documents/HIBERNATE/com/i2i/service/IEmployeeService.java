package com.i2i.service;

import com.i2i.entity.Employee;
import com.i2i.entity.Trainee;
import com.i2i.entity.Trainer;
import com.i2i.dao.IEmployeeDao;
import com.i2i.dao.impl.EmployeeDaoImpl;
import java.util.List;
import com.i2i.dto.EmployeeDto;

public interface IEmployeeService<T> {

    /**
     *used to add Employee into List
     *@param employee {@link T} the employee object
     *@return {@link void}
     */
    public void addEmployee(T emp);

    /**
     * Returns list of  All Employees    
     * @return {@link List} of {@link T}
     */
    public List<T> getAllEmployees();

    /**
     * used to get employee by his employeeId
     * @param employeeId {@link String} id of employee
     * @return {@void} 
     */
    public T  getEmployeeById(String employeeId);

    /**
     * used to remove the Employee by his employeeId
     * @param employeeId {@link String} id of employee
     * @return {@void}  
     */   
    public void removeEmployeeById(String employeeId);

    /**
     * used to update the Employee by his employeeId
     * @param employeeId {@link String} id of employee
     * @param mobileNumber{@link long} mobile number of employee
     * @return {@void}  
     */   
    public void  updateEmployeeById(String employeeId, long mobileNumber);

    /**
     * used to associate the Employee by his employeeId
     * @param employees {@link List} of {@link T} list of employees
     * @param employeeId {@link String} id of employee
     * @return {@void}  
     */   
    public void associateEmployee(List<T> employeesDto, String employeeId);

    /**
     * used to get associated employees with id
     * @param employeeId {@link String} id of employee
     * @return {@link List} of {@link EmployeeDto}
    
    public List<EmployeeDto> getAssociates(String employeeId);
      */  

}