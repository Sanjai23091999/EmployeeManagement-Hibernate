import com.i2i.dto.EmployeeDto;
import com.i2i.dto.TraineeDto;
import com.i2i.dto.TrainerDto;

import com.i2i.exception.IdDoesNotExistException;
import com.i2i.service.impl.EmployeeServiceImpl;
import com.i2i.service.IEmployeeService;
import com.i2i.util.EmployeeUtil;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeController {

    private  Scanner scanner = new Scanner(System.in);
    
    private IEmployeeService<TraineeDto> traineeService = new EmployeeServiceImpl(new TraineeDto());
    private IEmployeeService<TrainerDto> trainerService = new EmployeeServiceImpl(new TrainerDto());

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public static void main(String[] args) {

        EmployeeController employeeController = new EmployeeController();
        
        System.out.println("Welcome to Ideas2IT Employee management portal!!");    
        employeeController.init();     
    }

    public void init() {
        boolean isContinue = true;
        while(isContinue) {
            logger.info("Please enter one of the below options to proceed further");
            logger.info(" Enter 1 for add details \n Enter 2 for display Details \n Enter 3 for Delete details"
                               +" \n Enter 4 for update \n Enter 5 for Associate Trainer or Trainee \n Enter Any other for Exit");                               
            int userDecision = scanner.nextInt();
            switch (userDecision) {
            case 1:
                logger.info(" Enter 1 for add Trainer \n Enter 2 add Trainee");
                int userChoice = scanner.nextInt();
                if (userChoice == 1) {
                    createEmployee(userChoice);
                } else if (userChoice == 2) {
                    createEmployee(userChoice);
                }
                break;

            case 2: 
                logger.info(" Enter 1 for Display All Employee \n Enter 2 for Display specific Employee" );
                int userInput = scanner.nextInt();
                switch (userInput) {
                case 1:
                    scanner.nextLine();
                    logger.info("Enter 1 for Display  All Trainers \n Enter 2 for Display All Trainees ");
                    int userSelect = scanner.nextInt();
                    switch (userSelect) {
                    case 1:
                        displayAllTrainers();
                        break;

                    case 2: 
                        displayAllTrainees();
                        break;
                    }
                    break;
                 case 2:
                     logger.info(" Enter 1 for  Trainer \n Enter 2  Trainee");
                     int userPreference = scanner.nextInt();
                     scanner.nextLine();
                     if (userPreference == 1) {
                         logger.info("Please Enter your Employee Id");
                         String id = scanner.nextLine();
                         try {
                             displayTrainerById(id);
                         } catch (IdDoesNotExistException e) {
                             System.out.println(e);
                         }
                     } else {
                         logger.info("Please Enter your Employee Id");
                         String id = scanner.nextLine();
                         try {
                             displayTraineeById(id); 
                         } catch (IdDoesNotExistException e) {
                             System.out.println(e);
                         }
                     }                      
                    break;
                 }
                 break;

            case 3:
                logger.info(" Enter 1 for  Trainer \n Enter 2  Trainee");
                int userOption = scanner.nextInt();
                     if (userOption == 1) {
                         scanner.nextLine();
                         logger.info("Please Enter your Employee Id");
                         String id = scanner.nextLine();
                         trainerService.removeEmployeeById(id);
                     } else {
                         scanner.nextLine();
                         logger.info("Please Enter your Employee Id");
                         String id = scanner.nextLine();
                         traineeService.removeEmployeeById(id); 
                     }
                 break;
            
            case 4:
                logger.info(" Enter 1 for  Trainer \n Enter 2  Trainee");
                int userWish = scanner.nextInt();
                     if (userWish == 1) {
                         updateEmployee(userWish);
                     } else if (userWish == 2){
                         updateEmployee(userWish);
                     }
                 break;

             case 5:
                 logger.info(" Enter 1 for Associate  Trainee for Trainer \nEnter 2 for Associate Trainer for Trainee");                  
                 int userAssociate = scanner.nextInt();
                 if (userAssociate == 1) {
                     associateEmployee(userAssociate);
                 } else if (userAssociate == 2) {
                     associateEmployee(userAssociate);
                 }
                 break;

            default:
                isContinue = false;
            }
        }
    }
             
    public void createEmployee(int userInput) {
          
        scanner.nextLine();
        logger.info("Enter Employee Name");
        String employeeName = scanner.nextLine();      
        logger.info("Enter date of birth in YYYY-MM-DD format: ");
        String dateOfBirth = scanner.nextLine();
        LocalDate employeeDateOfBirth = LocalDate.parse(dateOfBirth);
        logger.info("Enter date of Join in YYYY-MM-DD format: ");
        String dateOfJoin = scanner.nextLine();
        LocalDate employeeDateOfJoin = LocalDate.parse(dateOfJoin);
        logger.info("Enter your mobile Number");
        long mobileNumber = scanner.nextLong();
        String employeeId = EmployeeUtil.getId();

        if (userInput == 2) {

            TraineeDto traineeDto = new TraineeDto();

            traineeDto.setEmployeeName(employeeName);
            traineeDto.setEmployeeDateOfBirth(employeeDateOfBirth);
            traineeDto.setEmployeeDateOfJoin(employeeDateOfJoin);
            traineeDto.setEmployeeId(employeeId); 
            traineeDto.setEmployeeMobileNumber(mobileNumber);
            traineeService.addEmployee(traineeDto);

        } else if (userInput == 1) {

            TrainerDto trainerDto = new TrainerDto();

            trainerDto.setEmployeeName(employeeName);
            trainerDto.setEmployeeDateOfBirth(employeeDateOfBirth);
            trainerDto.setEmployeeDateOfJoin(employeeDateOfJoin);
            trainerDto.setEmployeeId(employeeId); 
            trainerDto.setEmployeeMobileNumber(mobileNumber);
            trainerService.addEmployee(trainerDto);
        }           
    }

    public void displayAllTrainers() {
        for (TrainerDto trainerDto : (List<TrainerDto>)trainerService.getAllEmployees()) {
            logger.info(trainerDto.toString());     
        }
    }

    public void displayAllTrainees() {
        for (TraineeDto traineeDto : (List<TraineeDto>)traineeService.getAllEmployees()) {
            logger.info(traineeDto.toString());
        } 
    } 
  
    public void displayTrainerById(String employeeId) throws IdDoesNotExistException {
        TrainerDto trainerDto = trainerService.getEmployeeById(employeeId);
        if (trainerService.getEmployeeById(employeeId) == null) {
            throw new IdDoesNotExistException("ID does not exists");
        } else { 
            logger.info(trainerDto.toString()+"Trainees List"+trainerDto.getTrainee());
        }
    }

    public void displayTraineeById(String employeeId) throws IdDoesNotExistException {
        TraineeDto traineeDto = traineeService.getEmployeeById(employeeId);
        if (traineeService.getEmployeeById(employeeId) == null) {
            throw new IdDoesNotExistException("ID does not exists");
        } else {
            logger.info(traineeDto.toString()+"Trainers List"+traineeDto.getTrainers());
        }
    }

    public void updateEmployee(int userUpdate) {
        scanner.nextLine();
        logger.info("Please Enter your EmployeeId");
        String employeeId = scanner.nextLine();
        logger.info("Please Enter your Mobile Number");
        long mobileNumber = scanner.nextInt();

        if (userUpdate == 1) {
           trainerService.updateEmployeeById(employeeId, mobileNumber);
        } else {
           traineeService.updateEmployeeById(employeeId, mobileNumber);
        }
    }

    public void associateEmployee(int userChoice) {
        if (userChoice == 2) {
            List<TrainerDto> trainersDto = new ArrayList<>();
            scanner.nextLine();
            logger.info("Enter the Trainee employeeId ");
            displayAllTrainees();
            String traineeId = scanner.nextLine();
            logger.info("Enter the Trainers Id you want to add");
            displayAllTrainers(); 
            String[] trainerIds = scanner.nextLine().split(",");
            for (int i = 0; i<trainerIds.length; i++) {
                trainersDto.add(trainerService.getEmployeeById(trainerIds[i]));
            }
            trainerService.associateEmployee(trainersDto, traineeId);
        } else if (userChoice == 1) {
            List<TraineeDto> traineesDto = new ArrayList<>();
            scanner.nextLine();
            logger.info("Enter the Trainer employeeId ");
            displayAllTrainers();
            String trainerId = scanner.nextLine();
            logger.info("Enter the Trainees Id you want to add");
            displayAllTrainees();
            String[] traineeIds = scanner.nextLine().split(",");
            for (int i = 0; i<traineeIds.length; i++) {
                traineesDto.add(traineeService.getEmployeeById(traineeIds[i]));
            }
            traineeService.associateEmployee(traineesDto, trainerId);
        } 
    } 
}


        
    
