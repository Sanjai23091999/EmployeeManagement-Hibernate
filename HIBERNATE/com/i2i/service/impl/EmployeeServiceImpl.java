package com.i2i.service.impl;

import java.util.List;
import java.util.Set;

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
            Trainer trainer = trainerDao.retriveEmployeeById(employeeId);
            TrainerDto trainerDto = Converter.trainerToTrainerDto(trainer);
            trainerDto.setTrainee(Converter.traineeListTotraineeDtoList(trainer.getTrainee()));
            return (T)trainerDto;
        } else {
            Trainee trainee = traineeDao.retriveEmployeeById(employeeId);
            TraineeDto traineeDto = Converter.traineeToTraineeDto(trainee);
            traineeDto.setTrainers(Converter.trainerListTotrainerDtoList(trainee.getTrainers()));
            return (T)traineeDto;
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
            Trainer trainer = trainerDao.retriveEmployeeById(employeeId);
            trainer.setActiveEmployee(1);
            trainer.getTrainee().removeAll(trainer.getTrainee());
            trainerDao.updateEmployee(trainer);
        } else {
            Trainee trainee = traineeDao.retriveEmployeeById(employeeId);
            trainee.setActiveEmployee(1);
            trainee.getTrainers().removeAll(trainee.getTrainers());
            traineeDao.updateEmployee(trainee);
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
            Trainer trainer = trainerDao.retriveEmployeeById(employeeId);
            trainer.setEmployeeMobileNumber(mobileNumber);
            trainerDao.updateEmployee(trainer);
        } else {
            Trainee trainee = traineeDao.retriveEmployeeById(employeeId);
            trainee.setEmployeeMobileNumber(mobileNumber);
            traineeDao.updateEmployee(trainee);
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
        if (employee instanceof TraineeDto) {
            Trainer trainer = trainerDao.retriveEmployeeById(employeeId); 
            trainer.setTrainee(Converter.traineeDtoListTotraineeList ((List<TraineeDto>)employeesDto));
            trainerDao.updateEmployee(trainer);            
        } else {
            Trainee trainee = traineeDao.retriveEmployeeById(employeeId);
            trainee.setTrainers(Converter.trainerDtoListTotrainerList((List<TrainerDto>)employeesDto));
            traineeDao.updateEmployee(trainee);
        }
    } 
}


    