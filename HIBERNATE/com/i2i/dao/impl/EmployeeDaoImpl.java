package com.i2i.dao.impl;

import com.i2i.dao.IEmployeeDao;
import com.i2i.entity.Trainee;
import com.i2i.entity.Trainer;
import com.i2i.entity.Employee;
import com.i2i.util.Connection;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


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
            if (employee instanceof Trainee) {        
                TypedQuery query = session.getNamedQuery("retriveAllTrainees");    
                query.setParameter("status",0);
                logger.info("gg");
                employees = query.getResultList(); 
            } else {
                TypedQuery query = session.getNamedQuery("retriveAllTrainers");    
                query.setParameter("status",0);
                logger.info("aa");
                employees = query.getResultList(); 
            }
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

        Employee selectedEmployee = null;
        try(Session session  = Connection.hibernateConnection()) {  
            if (employee instanceof Trainer) {
                TypedQuery query = session.getNamedQuery("retriveTrainerById");    
                query.setParameter("employeeId",employeeId);
                query.setParameter("isActiveEmployee",0); 
                List<Trainer> trainers = query.getResultList();
                selectedEmployee = trainers.get(0);
            } else { 
                TypedQuery query = session.getNamedQuery("retriveTraineeById");    
                query.setParameter("employeeId",employeeId);
                query.setParameter("isActiveEmployee",0);
                List<Trainee> trainees = query.getResultList();
                selectedEmployee = trainees.get(0);
            } 
        } catch(Exception e) {
                logger.error("",e);
        }                 
        return (T)selectedEmployee;             
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
}