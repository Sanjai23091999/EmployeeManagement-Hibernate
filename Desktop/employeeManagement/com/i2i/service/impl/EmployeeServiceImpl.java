package com.i2i.service.impl;

import java.util.List;

import com.i2i.converter.Converter;
import com.i2i.dao.impl.EmployeeDaoImpl;
import com.i2i.dao.IEmployeeDao;
import com.i2i.dto.EmployeeDto;
import com.i2i.dto.TraineeDto;
import com.i2i.dto.TrainerDto;
import com.i2i.entity.Employee;
import com.i2i.entity.Trainee;
import com.i2i.entity.Trainer;
import com.i2i.service.IEmployeeService;


public class EmployeeServiceImpl<T extends EmployeeDto> implements IEmployeeService<T> {

    private  static IEmployeeDao<Trainer> trainerDao = new EmployeeDaoImpl(new Trainer());
    private  static IEmployeeDao<Trainee> traineeDao = new EmployeeDaoImpl(new Trainee()); 

    private T employee;


    public EmployeeServiceImpl(T employee) {
        this.employee = employee;
    }

    /**
     *used to add Employee into List
     *@param employee {@link T} the employee object
     *@return {@link void}
     */    
    @Override   
    public void addEmployee(T employeeDto) {
        if (employeeDto instanceof TrainerDto) {
            trainerDao.insertEmployee(Converter.trainerDtoToTrainer((TrainerDto)employeeDto)); 
        } else {  
            traineeDao.insertEmployee(Converter.traineeDtoToTrainee((TraineeDto)employeeDto));
        }
    }

    /**
     * Returns list of  All Employees    
     * @return {@link List} of {@link T}
     */
    @Override
    public List<T> getAllEmployees() {
        if (employee instanceof TrainerDto) {
            return (List<T>)Converter.trainerListTotrainerDtoList(trainerDao.retriveAllEmployees());            
        } else {
            return (List<T>)Converter.traineeListTotraineeDtoList(traineeDao.retriveAllEmployees());
        }
    }

    /**
     * used to get employee by his employeeId
     * @param employeeId {@link String} id of employee
     * @return {@void} 
     */
    @Override
    public T  getEmployeeById(String employeeId) {
        if (employee instanceof TrainerDto) {
            return (T)Converter.trainerToTrainerDto(trainerDao.retriveEmployeeById(employeeId));
        } else {
            return (T)Converter.traineeToTraineeDto(traineeDao.retriveEmployeeById(employeeId));
        }
    }

    /**
     * used to remove the Employee by his employeeId
     * @param employeeId {@link String} id of employee
     * @return {@void}  
     */   
    @Override
    public void  removeEmployeeById(String employeeId) {
        if (employee instanceof TrainerDto) {
            trainerDao.deleteEmployeeById(employeeId);
        } else {
            traineeDao.deleteEmployeeById(employeeId);
        }
    }

    /**
     * used to update the Employee by his employeeId
     * @param employeeId {@link String} id of employee
     * @param mobileNumber{@link long} mobile number of employee
     * @return {@void}  
     */   
    @Override
    public void  updateEmployeeById(String employeeId, long mobileNumber) {   
        if (employee instanceof TrainerDto) {
            for(Trainer trainer : trainerDao.retriveAllEmployees()) { 
                if (trainer.getEmployeeId().equals(employeeId)) {
                    trainer.setEmployeeMobileNumber(mobileNumber);
                    trainerDao.updateEmployee(trainer);
                    break;
                }
            }
        } else {
            for(Trainee trainee : traineeDao.retriveAllEmployees()) {
                if (trainee.getEmployeeId().equals(employeeId)) {                 
                    trainee.setEmployeeMobileNumber(mobileNumber);
                    traineeDao.updateEmployee(trainee);
                    break;
                 } 
            } 
        }         
    } 

    /**
     * used to associate the Employee by his employeeId
     * @param employees {@link List} of {@link T} list of employees
     * @param employeeId {@link String} id of employee
     * @return {@void}  
     */       
    @Override
    public void associateEmployee(List<T> employeesDto, String employeeId) {
        System.out.println("service 3");   
        if (employee instanceof TraineeDto) {
             System.out.println("service 5");
            for(Trainer trainer : trainerDao.retriveAllEmployees()) { 
             System.out.println("service 1");
                if (trainer.getEmployeeId().equals(employeeId)) {
                    trainer.setTrainee(Converter.traineeDtoListTotraineeList ((List<TraineeDto>)employeesDto));
                    trainerDao.updateEmployee(trainer);
                    break;
                }
            }
        } else {
             for(Trainee trainee : traineeDao.retriveAllEmployees()) { 
              if (trainee.getEmployeeId().equals(employeeId)) {
                    trainee.setTrainers(Converter.trainerDtoListTotrainerList((List<TrainerDto>)employeesDto));
                    traineeDao.updateEmployee(trainee);
                    break;
                }
            }
        }
    } 

    /**
     * used to get associated employees with id
     * @param employeeId {@link String} id of employee
     * @return {@link List} of {@link EmployeeDto}
     */   
    public List<EmployeeDto> getAssociates(String employeeId) {
       if (employee instanceof TraineeDto) { 
           return Converter.employeeListToEmployeeDtoList(traineeDao.retriveAssociates(employeeId));
       } else {
           return Converter.employeeListToEmployeeDtoList(trainerDao.retriveAssociates(employeeId)); 
       }
    }    
}


    