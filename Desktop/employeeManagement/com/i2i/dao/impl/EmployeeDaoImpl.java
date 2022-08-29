package com.i2i.dao.impl;

import com.i2i.dao.IEmployeeDao;
import com.i2i.entity.Trainee;
import com.i2i.entity.Trainer;
import com.i2i.entity.Employee;
import com.i2i.util.Connection;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

public class EmployeeDaoImpl<T extends Employee> implements IEmployeeDao<T> {

    private T employee;

    public EmployeeDaoImpl(T employee) {
        this.employee = employee;
    }

    private static Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    /**
     *used to insert Employee into List
     *@param employee {@link T} the employee object
     *@return {@link void}
     */  
    @Override        
    public  void insertEmployee(T employee) {  
        try(Session session  = Connection.hibernateConnection()) { 
             Transaction transaction =session.beginTransaction(); 
             if (employee instanceof Trainee) {
                 session.persist((Trainee)employee);
             } else {
                 session.persist((Trainer)employee);
             }
             transaction.commit();    
        } catch(Exception e) {
             logger.error("",e);
        }
    }

    /**
     * Retrive list of  All Employees  
     * @return {@link List} of {@link T}
     */   
    @Override
    public List<T> retriveAllEmployees() {
            
        List<Employee> employees = null;
    
        try(Session session  = Connection.hibernateConnection()) {   
    	    Transaction transaction =session.beginTransaction(); 
            if (employee instanceof Trainer) {
                Criteria criteriaTrainer = session.createCriteria(Trainer.class);
                criteriaTrainer.add(Restrictions.ne("deletedEmployee",1));
                employees = criteriaTrainer.list(); 
            } else {
                Criteria criteriaTrainee = session.createCriteria(Trainee.class);
                criteriaTrainee.add(Restrictions.ne("deletedEmployee",1));
                employees = criteriaTrainee.list();
            }
            transaction.commit();    
        } catch(Exception e) {
            logger.error("",e);
        } 
        return (List<T>)employees;         
    }

    /**
     * Retrive  Employee with his id  
     * @param employeeId {@link String} id of employee  
     * @return {@link void} 
     */   
    @Override
    public  T retriveEmployeeById(String employeeId) {

        Employee employee = null;
        try(Session session  = Connection.hibernateConnection()) {  
    	    Transaction transaction =session.beginTransaction();   
            if (employee instanceof Trainer) {
                Criteria criteriaTrainer = session.createCriteria(Trainer.class);
                criteriaTrainer.add(Restrictions.eq("employeeId",employeeId));
                criteriaTrainer.add(Restrictions.ne("deletedEmployee",1));
                List<Trainer> trainers = criteriaTrainer.list();
                for (Trainer trainer : trainers) {
                    employee = trainer;
                    break;      
                }
            } else { 
                Criteria criteriaTrainee = session.createCriteria(Trainee.class);
                criteriaTrainee.add(Restrictions.eq("employeeId",employeeId));
                criteriaTrainee.add(Restrictions.ne("deletedEmployee",1));
                List<Trainee> trainees = criteriaTrainee.list();
                for (Trainee trainee : trainees) {
                    employee = trainee;
                    break;
                 }              
            } 
            transaction.commit();  
        } catch(Exception e) {
                logger.error("",e);
        }                 
        return (T)employee;             
    }

    /**
     * Remove  Employee with his id  
     * @param employeeId {@link String} id of employee  
     * @return {@link void} 
     */   
    @Override
    public void deleteEmployeeById(String employeeId) {
             
        try(Session session  = Connection.hibernateConnection()) {  
    	    Transaction transaction =session.beginTransaction();
            if (employee instanceof Trainer) {
                Criteria criteriaTrainer = session.createCriteria(Trainer.class);
                criteriaTrainer.add(Restrictions.ne("deletedEmployee",1));
                List<Trainer> trainers = criteriaTrainer.list();
                for (Trainer trainer : trainers) {  
                    if (trainer.getEmployeeId().equals(employeeId)) {
                        trainer.setDeletedEmployee(1);
                        break;
                    } 
                } 
            } else {
                Criteria criteriaTrainee = session.createCriteria(Trainee.class);
                criteriaTrainee.add(Restrictions.ne("deletedEmployee",1));
                List<Trainee> trainees = criteriaTrainee.list();
                for (Trainee trainee : trainees) {
                    if (trainee.getEmployeeId().equals(employeeId)) {                 
                        trainee.setDeletedEmployee(1);
                        break;
                    }  
                }
            }
            transaction.commit();
        } catch(Exception e) {
            logger.error("",e);
        }                 
    } 

    /**
     * used to update  Employee  
     * @param updateEmployee {@link Employee} the employee object 
     * @return {@link void} 
     */   
    @Override
    public void updateEmployee(Employee updateEmployee) {
      
        try(Session session  = Connection.hibernateConnection()) { 
             Transaction transaction =session.beginTransaction(); 
             if (employee instanceof Trainee) {
                 session.update((Trainee)updateEmployee);
                 System.out.println("dao trainee");
             } else {
                 session.update((Trainer)updateEmployee);
                 System.out.println("dao trainer");
             }
             transaction.commit();    
        } catch(Exception e) {
             logger.error("",e);
        }
    } 

    public List<Employee> retriveAssociates(String employeeId) {

        if (employee instanceof Trainee) {
            List<Employee> employees = new ArrayList<>();
            try(Session session  = Connection.hibernateConnection()) {  
    	        Transaction transaction =session.beginTransaction();
                Criteria criteriaTrainer = session.createCriteria(Trainer.class);
                criteriaTrainer.add(Restrictions.ne("deletedEmployee",1));
                List<Trainer> trainers = criteriaTrainer.list();
                for (Trainer trainer : trainers) {
		     if (trainer.getEmployeeId().equals(employeeId)) {                       
                         employees.add(trainer);
                         for (Trainee trainee : trainer.getTrainee()) {
                             employees.add(trainee); 
                         }
                     }
                }
                transaction.commit();
            } catch(Exception e) {
                e.printStackTrace();
            }           
            return (List<Employee>)employees;   
        } else {
            List<Employee> employees = new ArrayList<>();
            try(Session session  = Connection.hibernateConnection()) {  
    	        Transaction transaction =session.beginTransaction();
                Criteria criteriaTrainee = session.createCriteria(Trainee.class);
                criteriaTrainee.add(Restrictions.ne("deletedEmployee",1));
                List<Trainee> trainees = criteriaTrainee.list();
                for (Trainee trainee : trainees) {
                    if (trainee.getEmployeeId().equals(employeeId)) {
                        employees.add(trainee);                    
                        for (Trainer trainer : trainee.getTrainers()) {                     
                            employees.add(trainer);
                        }
                    }
                }
                transaction.commit();
            } catch(Exception e) {
                e.printStackTrace();
            }           
            return (List<Employee>)employees;   
        }
    }
}