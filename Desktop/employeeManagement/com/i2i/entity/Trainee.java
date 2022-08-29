package com.i2i.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;



@Entity
public class Trainee extends Employee{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int TraineeId;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy="trainees") 
    private List<Trainer> trainers;
        
    public List<Trainer> getTrainers(){
	return trainers;
    }
                                        
    public void setTrainers(List<Trainer> trainers) {
	this.trainers = trainers;
    }

}
       




















