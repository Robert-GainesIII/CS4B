package customExceptions;

public class EmployeeExists extends Exception{
    
        public EmployeeExists(String s){
            super(s);
        }
}
