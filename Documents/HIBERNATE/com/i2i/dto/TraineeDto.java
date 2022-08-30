package com.i2i.dto;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class TraineeDto extends EmployeeDto{

    private List<TrainerDto> trainersDto;
        
    public List<TrainerDto> getTrainers(){
	return trainersDto;
    }
                                        
    public void setTrainers(List<TrainerDto> trainersDto) {
	this.trainersDto = trainersDto;
    }

    public String toString() {
        return super.toString();
    }  
}