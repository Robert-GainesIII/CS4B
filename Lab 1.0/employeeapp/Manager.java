/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeapp;
import customExceptions.UserNotFound;
import java.util.*;

/**
 *
 * @author bobby
 */
public class Manager{
    
    ArrayList<Person> members;

    public Manager() {
        members = new ArrayList();
    }
    
    public Person getMemberByName(String Name)throws UserNotFound{
        Person Per = null;
        Executive A;
        Employee B;
        
        for(Person p : members){
            if(p.isExcutive){
                A = (Executive)p;
                if(A.getName().equalsIgnoreCase(Name))
                Per = A;
            }
            else if(p.isExcutive != true){
                B = (Employee)p;
                if(B.getName().equalsIgnoreCase(Name))
                Per = B;      
            }
            else{
                throw new UserNotFound("The user was not found!");
            }
        }
        return Per;
    }
    
    
    public void addMember(Person p){
        members.add(p);
    }
    
    public void removeMember(Person p){
        members.remove(p);
    }
}
