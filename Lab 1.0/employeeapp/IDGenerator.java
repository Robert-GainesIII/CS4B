package employeeapp;
import customExceptions.EmployeeExists;
import java.util.*;


public class IDGenerator {
    
        private int baseID = 1;
        static int newID; 
         
        
        ArrayList<Integer> IDList;
        
        public IDGenerator(){
        this.IDList = new ArrayList();
                
        }
        
        public int generateID() throws EmployeeExists{
            newID = baseID;
            baseID+=2;
            for(int i: IDList){
                if(i == newID)throw new EmployeeExists("EMPLOYEE ALREADY EXISTS!");
            }
            IDList.add(newID);
            return newID;
        }
}
