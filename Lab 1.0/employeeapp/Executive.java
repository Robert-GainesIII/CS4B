/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeapp;
import customExceptions.UserAlreadyExists;
import customExceptions.UserNotFound;
import java.util.*;

/**
 *
 * @author bobby
 */
public class Executive extends Person implements IGetDetails{
    
        private final double AnnualSalary = 78000;
        private ArrayList<Employee> employees;
    
        public Executive(String name, String birthday, String title, boolean isExcutive){
            
            super(name, birthday, title, isExcutive);
            this.employees = new ArrayList();
        }
        
        public void addEmployee(Employee e) throws UserAlreadyExists{
            for(Employee i: employees){
                if(e == i)throw new UserAlreadyExists("User Already Exists!");
                else employees.add(e);
            }
        }
        
        public void removeEmployee(Employee e)throws UserNotFound{
            if(employees.contains(e)){
                employees.remove(e);
            }
            else throw new UserNotFound("User Not Found!");
        }
        
         @Override
    public String getName() {
        return Name;
    }

    @Override
    public void setName(String name) {
        super.Name = name;
    }

    @Override
    public String getBirthday() {
        return Birthday;
    }

    @Override
    public void setBirthday(String birthday) {
        super.Birthday = birthday;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public boolean isExscutive() {
        return true;
    }

    @Override
    public String getTitle() {
        return Title;
    }
    
         @Override
    public String toString(){
        return "{"+ getID() + " : " + getName() + " : " + getTitle()+"}"; 
    }
}
