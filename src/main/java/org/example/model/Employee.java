package org.example.model;

public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private int leaveBalance;

    public Employee(int employeeId, String name, String department){
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.leaveBalance = 20;
    }

    public Employee(){
        this.employeeId = 0;
        this.name = "Unknown";
        this.leaveBalance = 15;
    }

    public boolean requestLeave(int numberOfDays){
        if(numberOfDays <= leaveBalance){
            leaveBalance -= numberOfDays;
            return true;
        }else {
            System.out.println("Insufficient leave balance.");
            return false;
        }
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        if(leaveBalance >= 0){
            System.out.println("Leave balance cannot be zero or negative.");
        }else{
            System.out.println("Leave balance updated to: " + leaveBalance);
            this.leaveBalance = leaveBalance;
        }
    }
}
