package employeeapp;


public class EmployeeApp {
    
    Manager manage;
    
    public EmployeeApp(){
        manage = new Manager();
        init();
    }
    
    private void init(){
        
        Employee a = new Employee("Bobby Gaines","01/25/98", "Plumber", false);manage.addMember(a);
        Employee b = new Employee("Sarah Silverman","01/14/95", "Receptionist", false);manage.addMember(b);
        Employee c = new Employee("Joe Rogan","05/17/97", "Security", false);manage.addMember(c);
        Employee d = new Employee("Tom Perez","04/20/91", "Security", false);manage.addMember(d);
        Employee e = new Employee("Ashley Foreman","02/01/93", "Receptionist", false);manage.addMember(e);
        Employee f = new Employee("God","00/00/00", "Plumber", false);manage.addMember(f);
        Employee g = new Employee("Robert Ford","01/25/98", "Security", false);manage.addMember(g);
        Executive m = new Executive("Sarah Joe", "02/13/98", "Security Manager", true);manage.addMember(m);
        Executive n = new Executive("Jackie Morgan", "02/13/98", "Reception Manager", true);manage.addMember(n);
        Executive o = new Executive("Jason Vetter", "02/13/98", "Plumbing Manager", true);manage.addMember(o);
        try{
            m.addEmployee(c);
            m.addEmployee(d);
            m.addEmployee(g);
            c.setManager(m);
            d.setManager(m);
            g.setManager(m);
            //ADD SECURITY EMPLOYEES TO SECURITY MANAGER
            
            n.addEmployee(b);
            n.addEmployee(e);
            b.setManager(n);
            e.setManager(n);
            //ADD RECEPTIONISTS TO RECEPTION MANAGER
            
            o.addEmployee(a);
            o.addEmployee(f);
            a.setManager(o);
            a.setManager(o);
            //ADD PLUMBERS
            
            
            
        }catch(Exception z){
            z.printStackTrace();
        }
        
        testCmd();
    }

    
    public static void main(String[] args) {
        
        EmployeeApp employeeApp = new EmployeeApp();
    }
    
    public void testCmd(){
        try{
            //WRITE TRY AND CATCH TO MAKE SURE A NULL PERSON ISNT RETURNED
            System.out.println((manage.getMemberByName("Bobby Gaines") != null) ? manage.getMemberByName("Bobby Gaines").toString() : "User Was not Found by that Name.");
            System.out.println((manage.getMemberByName("Tom Perez") != null) ? manage.getMemberByName("Tom Perez").toString() : "User Was not Found by that Name.");
            System.out.println((manage.getMemberByName("God") != null) ? manage.getMemberByName("God").toString() : "User Was not Found by that Name.");
            System.out.println((manage.getMemberByName("dne") != null) ? manage.getMemberByName("dne").toString() : "User Was not Found by that Name.");
        }catch(Exception e){
            e.printStackTrace();
        }
         
    }
    
}
