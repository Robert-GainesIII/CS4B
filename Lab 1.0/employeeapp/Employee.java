/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeapp;

/**
 *
 * @author bobby
 */
public class Employee extends Person implements IGetDetails{
    
        private double hourlyWage = 10.0;
        private Executive Manager;
        
        public Employee(String name, String birthday, String title, boolean isExcutive){
            super(name, birthday, title, isExcutive);
        }
        
        public Executive getManager(){ return Manager; }
        public void setManager(Executive Manager){
            this.Manager = Manager;
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
