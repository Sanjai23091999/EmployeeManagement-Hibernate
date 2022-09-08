package com.i2i.dto;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class TrainerDto extends EmployeeDto{

    private List<TraineeDto> traineesDto;

    public void setTrainee(List<TraineeDto> traineesDto ) {
	this.traineesDto = traineesDto;
    }

    public List<TraineeDto> getTrainee() {
	return traineesDto;
    }

    public String toString() {
        return super.toString();
    }  
}