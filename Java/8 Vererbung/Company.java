import java.util.ArrayList;
import java.util.Date;

public class Company {
    ArrayList<Person> persons = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Contractor> contractors = new ArrayList<>();
    ArrayList<Supervisor> supervisors = new ArrayList<>();


    public Company() {
    }


//direct adds
    public Person addNewPerson(int id, String name, Date dateOfBirth) {
        Person person = new Person(id, name, dateOfBirth);
        persons.add(person);
        return person;
    }

    public Employee addNewEmployee(int id, String name, Date dateOfBirth,String department, int salary) {
        Employee employee = new Employee(id, name, dateOfBirth, department, salary);
        employees.add(employee);
        return employee;
    }

    public Contractor addNewContractor(int id, String name, Date dateofBirth, String department, int monthlyFee) {
        Contractor contractor = new Contractor(id, name, dateofBirth, department, monthlyFee);
        contractors.add(contractor);
        return contractor;
    }

    public Supervisor addNewSupervisor(int id, String name, Date dateOfBirth,String department, int salary) {
        Supervisor supervisor = new Supervisor(id, name, dateOfBirth, department, salary);
        supervisors.add(supervisor);
        return supervisor;
    }



//promotions

    public Person addEmployeeFromPerson(Person person, String department, int salary) {
        Employee employee = new Employee(person, department, salary);
        persons.remove(person);
        employees.add(employee);
        return employee;
    }

    public Contractor addContractorFromPerson(Person person, String department, int monthlyFee) {
        Contractor contractor = new Contractor(person, department, monthlyFee);
        persons.remove(person);
        contractors.add(contractor);
        return contractor;
    }

    public Supervisor addSupervisorFromPerson(Person person, String department, int salary) {
        Supervisor supervisor = new Supervisor(person.id, person.name, person.dateOfBirth, department, salary);
        persons.remove(person);
        supervisors.add(supervisor);
        return supervisor;
    }

    public Supervisor promoteEmployeeToSupervisor(Employee employee) {
        Supervisor supervisor= new Supervisor(employee.id, employee.name, employee.dateOfBirth, employee.department, employee.salary + 2000);
        supervisors.add(supervisor);
        employees.remove(employee);
        return supervisor;
    }



    public static class Person {
        public int id;
        public String name;
        public Date dateOfBirth;

        public Person(int id, String name, Date dateOfBirth) {
            this.id = id;
            this.name = name;
            this.dateOfBirth = dateOfBirth;
        }

        public void printSalary() {
            System.out.println("No Salary");
        }
    }

    public static class Employee extends Person {
        public String department;
        public int salary;

        public Employee(int id, String name, Date dateOfBirth, String department, int salary) {
            super(id, name, dateOfBirth);
            this.department = department;
            this.salary = salary;
        }

        public Employee(Person person, String department, int salary) {
            this(person.id, person.name, person.dateOfBirth, department, salary);
        }

        @Override
        public void printSalary() {
            System.out.println("Salary: " + salary + "€");
        }

    }

    public static class Contractor extends Person {
        public String department;
        public int monthlyFee;

        public Contractor(int id, String name, Date dateOfBirth, String department, int monthlyFee) {
            super(id, name, dateOfBirth);
            this.department = department;
            this.monthlyFee = monthlyFee;
        }

        public Contractor(Person person, String department, int monthlyFee) {
            this(person.id, person.name, person.dateOfBirth, department, monthlyFee);
        }

        @Override
        public void printSalary() {
            System.out.println("Contractor, gets Fee instead");
        }
        public void printFee() {
            System.out.println("Fee: " + monthlyFee + "€");
        }

    }

    public static class Supervisor extends Employee {
        public ArrayList<Employee> employees = new ArrayList<>();
        public ArrayList<Contractor> contractors = new ArrayList<>();

        public Supervisor(int id, String name, Date dateOfBirth, String department, int salary) {
            super(id, name, dateOfBirth, department, salary);
        }

        public Supervisor(Person person,String department, int salary) {
            this(person.id, person.name, person.dateOfBirth, department,salary);
        }

        public void addEmployee(Employee employee) {
            employees.add(employee);
        }

        public void removeEmployee(Employee employee) {
            employees.remove(employee);
        }

        public void addContractor(Contractor contractor) {
            contractors.add(contractor);
        }

        public void removeContractor(Contractor contractor) {
            contractors.add(contractor);
        }
    }
}
