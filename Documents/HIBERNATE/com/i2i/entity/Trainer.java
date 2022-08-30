package com.i2i.entity;

import java.util.List;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@NamedQueries(
    {
    @NamedQuery(  
        name = "retriveTrainerById",  
        query = "from Trainer trainer  where trainer.employeeId = :employeeId AND trainer.isActiveEmployee = :isActiveEmployee" 
    ), 
    @NamedQuery(  
        name = "retriveAllTrainers",  
        query = "from Trainer trainer where trainer.isActiveEmployee = :status"  
    )
    }
)

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
       



    

