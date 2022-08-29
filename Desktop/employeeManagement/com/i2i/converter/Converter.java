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

    public static Trainee traineeDtoToTrainee(TraineeDto traineeDto) {
    
        Trainee trainee = new Trainee();
        trainee.setEmployeeName(traineeDto.getEmployeeName());
        trainee.setEmployeeDateOfBirth(traineeDto.getEmployeeDateOfBirth());
        trainee.setEmployeeDateOfJoin(traineeDto.getEmployeeDateOfJoin());
        trainee.setEmployeeId(traineeDto.getEmployeeId()); 
        trainee.setEmployeeMobileNumber(traineeDto.getEmployeeMobileNumber());
        return trainee;
    }

    public static TraineeDto traineeToTraineeDto(Trainee trainee) {
    
        TraineeDto traineeDto = new TraineeDto();
        traineeDto.setEmployeeName(trainee.getEmployeeName());
        traineeDto.setEmployeeDateOfBirth(trainee.getEmployeeDateOfBirth());
        traineeDto.setEmployeeDateOfJoin(trainee.getEmployeeDateOfJoin());
        traineeDto.setEmployeeId(trainee.getEmployeeId()); 
        traineeDto.setEmployeeMobileNumber(trainee.getEmployeeMobileNumber());
        return traineeDto;
    }

    public static Trainer trainerDtoToTrainer(TrainerDto trainerDto) {
    
        Trainer trainer = new Trainer();
        trainer.setEmployeeName(trainerDto.getEmployeeName());
        trainer.setEmployeeDateOfBirth(trainerDto.getEmployeeDateOfBirth());
        trainer.setEmployeeDateOfJoin(trainerDto.getEmployeeDateOfJoin());
        trainer.setEmployeeId(trainerDto.getEmployeeId()); 
        trainer.setEmployeeMobileNumber(trainerDto.getEmployeeMobileNumber());
        return trainer;
    }

    public static TrainerDto trainerToTrainerDto(Trainer trainer) {
    
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setEmployeeName(trainer.getEmployeeName());
        trainerDto.setEmployeeDateOfBirth(trainer.getEmployeeDateOfBirth());
        trainerDto.setEmployeeDateOfJoin(trainer.getEmployeeDateOfJoin());
        trainerDto.setEmployeeId(trainer.getEmployeeId()); 
        trainerDto.setEmployeeMobileNumber(trainer.getEmployeeMobileNumber());
        return trainerDto;
    }

    public static List<TrainerDto> trainerListTotrainerDtoList(List<Trainer> trainers) {
        TrainerDto trainerDto = new TrainerDto();
        List<TrainerDto> trainersDto = new ArrayList<>();   
            for (Trainer trainer : trainers) {
                trainerDto = trainerToTrainerDto(trainer);
                trainersDto.add(trainerDto);
            
            }
        return trainersDto;
    }   

    public static List<TraineeDto> traineeListTotraineeDtoList(List<Trainee> trainees) {
         TraineeDto traineeDto = new TraineeDto();
         List<TraineeDto> traineesDto = new ArrayList<>();   
         for (Trainee trainee : trainees) {
             traineeDto = traineeToTraineeDto(trainee);
             traineesDto.add(traineeDto);
             
         }
         return traineesDto;
    }

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

    public static List<Trainer> trainerDtoListTotrainerList(List<TrainerDto> trainersDto) {
        Trainer trainer = new Trainer();
        List<Trainer> trainers = new ArrayList<>();   
            for (TrainerDto trainerDto : trainersDto) {
                trainer = trainerDtoToTrainer(trainerDto);
                trainers.add(trainer);
            }
        return trainers;
    }

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