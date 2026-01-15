package university.model;

import university.contract.Payable;

public class Teacher extends Person implements Payable {
    private double salary;

    public Teacher(String name, String address, double salary){
        super(name, address);
        this.salary = salary;
    }
    public double getSalary(){

        return this.salary;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }    
    @Override
    public double calculatePayment(){
        return this.salary;
    }
    @Override
    public String toString(){
        return "Teacher: " + getName() + "\n  salary " + this.salary + " euro/month";
    }
}
