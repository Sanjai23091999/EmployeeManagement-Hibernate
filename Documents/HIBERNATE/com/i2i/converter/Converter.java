package com.i2i.converter;
import java.util.List;
import com.i2i.dto.TrainerDto;
import com.i2i.dto.TraineeDto;
import com.i2i.dto.EmployeeDto;
import com.i2i.entity.Employee;
import com.i2i.entity.Trainee;
import com.i2i.entity.Trainer;
import java.util.ArrayList;

public class Converter {

    /**
     * used to convert Dto class to entity class of trainee
     * @param traineeDto {@link TraineeDto} the traineeDto object
     * @return trainee {@link Trainee} the trainee object
     */   
    public static Trainee traineeDtoToTrainee(TraineeDto traineeDto) {
    
        Trainee trainee = new Trainee();
        trainee.setEmployeeName(traineeDto.getEmployeeName());
        trainee.setEmployeeDateOfBirth(traineeDto.getEmployeeDateOfBirth());
        trainee.setEmployeeDateOfJoin(traineeDto.getEmployeeDateOfJoin());
        trainee.setEmployeeId(traineeDto.getEmployeeId()); 
        trainee.setEmployeeMobileNumber(traineeDto.getEmployeeMobileNumber());
        return trainee;
    }
    
    /**
     * used to convert entity class to Dto class of trainee
     * @param trainee {@link Trainee} the trainee object
     * @return traineeDto {@link TraineeDto} the traineeDto object
     */   
    public static TraineeDto traineeToTraineeDto(Trainee trainee) {
    
        TraineeDto traineeDto = new TraineeDto();
        traineeDto.setEmployeeName(trainee.getEmployeeName());
        traineeDto.setEmployeeDateOfBirth(trainee.getEmployeeDateOfBirth());
        traineeDto.setEmployeeDateOfJoin(trainee.getEmployeeDateOfJoin());
        traineeDto.setEmployeeId(trainee.getEmployeeId()); 
        traineeDto.setEmployeeMobileNumber(trainee.getEmployeeMobileNumber());
        return traineeDto;
    }
    
    /**
     * used to convert Dto class to entity class of trainee
     * @param trainerDto {@link TrainerDto} the trainerDto object
     * @return trainer {@link Trainer} the trainer object
     */   
    public static Trainer trainerDtoToTrainer(TrainerDto trainerDto) {
    
        Trainer trainer = new Trainer();
        trainer.setEmployeeName(trainerDto.getEmployeeName());
        trainer.setEmployeeDateOfBirth(trainerDto.getEmployeeDateOfBirth());
        trainer.setEmployeeDateOfJoin(trainerDto.getEmployeeDateOfJoin());
        trainer.setEmployeeId(trainerDto.getEmployeeId()); 
        trainer.setEmployeeMobileNumber(trainerDto.getEmployeeMobileNumber());
        return trainer;
    }
    
    /**
     * used to convert entity class to Dto class of trainer
     * @param trainer {@link Trainer} the trainer object
     * @return trainerDto {@link TrainerDto} the trainerDto object
     */   
    public static TrainerDto trainerToTrainerDto(Trainer trainer) {
    
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setEmployeeName(trainer.getEmployeeName());
        trainerDto.setEmployeeDateOfBirth(trainer.getEmployeeDateOfBirth());
        trainerDto.setEmployeeDateOfJoin(trainer.getEmployeeDateOfJoin());
        trainerDto.setEmployeeId(trainer.getEmployeeId()); 
        trainerDto.setEmployeeMobileNumber(trainer.getEmployeeMobileNumber());
        return trainerDto;
    }
   
    /**
     * used to convert entity list to Dto list of trainer
     * @param  trainers {@link List} of {@link Trainer} the trainer object
     * @return trainersDto {@link List} of {@link TrainerDto} the trainerDto object
     */   
    public static List<TrainerDto> trainerListTotrainerDtoList(List<Trainer> trainers) {
        TrainerDto trainerDto = new TrainerDto();
        List<TrainerDto> trainersDto = new ArrayList<>();   
            for (Trainer trainer : trainers) {
                trainerDto = trainerToTrainerDto(trainer);
                trainersDto.add(trainerDto);
            
            }
        return trainersDto;
    }   
    
    /**
     * used to convert entity list to Dto list of trainee
     * @param  trainees {@link List} of {@link Trainee} the trainee object
     * @return traineesDto {@link List} of {@link TraineeDto} the traineeDto object
     */   
    public static List<TraineeDto> traineeListTotraineeDtoList(List<Trainee> trainees) {
         TraineeDto traineeDto = new TraineeDto();
         List<TraineeDto> traineesDto = new ArrayList<>();   
         for (Trainee trainee : trainees) {
             traineeDto = traineeToTraineeDto(trainee);
             traineesDto.add(traineeDto);
             
         }
         return traineesDto;
    }
    
    /**
     * used to convert entity list to Dto list of employee
     * @param  employees {@link List} of {@link Employee} the employee object
     * @return employeesDto {@link List} of {@link EmployeeDto} the employeeDto object
     */   
    public static List<EmployeeDto> employeeListToEmployeeDtoList (List<Employee> employees) {
        List<EmployeeDto> employeesDto = new ArrayList<>(); 
        TrainerDto trainerDto = null;
        TraineeDto traineeDto = null;
       
        for(Employee employee : employees) {
            if (employee  instanceof Trainer) {  
                trainerDto  = trainerToTrainerDto((Trainer)employee);
                employeesDto.add(trainerDto);            
             } else  {
                traineeDto  = traineeToTraineeDto((Trainee)employee);
                employeesDto.add(traineeDto);
             }
         }
         return employeesDto;
     }
    
    /**
     * used to convert entity list to Dto list of trainee
     * @param  traineesDto {@link List} of {@link TraineeDto} the traineeDto object
     * @return trainees {@link List} of {@link Trainee} the trainee object
     */   
    public static List<Trainer> trainerDtoListTotrainerList(List<TrainerDto> trainersDto) {
        Trainer trainer = new Trainer();
        List<Trainer> trainers = new ArrayList<>();   
            for (TrainerDto trainerDto : trainersDto) {
                trainer = trainerDtoToTrainer(trainerDto);
                trainers.add(trainer);
            }
        return trainers;
    }
    
    /**
     * used to convert entity list to Dto list of trainee
     * @param  traineesDto {@link List} of {@link TraineeDto} the traineeDto object
     * @return trainees {@link List} of {@link Trainee} the trainee object
     */   
    public static List<Trainee> traineeDtoListTotraineeList(List<TraineeDto> traineesDto) {
         Trainee trainee = new Trainee();
         List<Trainee> trainees = new ArrayList<>();   
         for (TraineeDto traineeDto : traineesDto) {
             trainee = traineeDtoToTrainee(traineeDto);
             trainees.add(trainee); 
         }
         return trainees;
    }
}