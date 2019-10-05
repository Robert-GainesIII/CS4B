/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeapp;

import customExceptions.EmployeeExists;
import customExceptions.UserAlreadyExists;
import java.util.ArrayList;


public class Person {
    
        public static int IDCount = 0;
        public int ID;
        public String Name;
        public String Birthday;
        public String Title;
        public boolean isExcutive;
        
        ArrayList<Integer> IDList;
        
        
        public Person(String name, String birthday, String title, boolean isExcutive){
            this.Name = name;
            this.Birthday = birthday;
            this.Title = title;
            this.isExcutive = isExcutive;
            IDList = new ArrayList();
            try{
                ID = generateID(++IDCount);
            }catch(UserAlreadyExists e){
                e.printStackTrace();
            }
        }
        
        public int generateID(int a) throws UserAlreadyExists{
            for(int b : IDList){
                if(b == a)throw new UserAlreadyExists("User Already Exists");
                return -1;
            }
            return a;
        }
        
        
}
