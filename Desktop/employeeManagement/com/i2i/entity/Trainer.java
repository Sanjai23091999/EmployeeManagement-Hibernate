package com.i2i.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Trainer extends Employee{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int TrainerId;

    @ManyToMany(cascade = CascadeType.ALL)  
    @JoinTable(name = "join_employee_table",   
    joinColumns = @JoinColumn(name = "Trainer_id"),   
    inverseJoinColumns = @JoinColumn(name = "Trainee_id")) 
    private List<Trainee> trainees;

    public void setTrainee(List<Trainee> trainees ) {
	this.trainees= trainees;
    }

    public List<Trainee> getTrainee() {
	return trainees;
    }
        


}
       



    

