package org.example;

import org.example.model.Employee;
import org.example.model.LeaveRequest;

public class LeaveTracker {
    public static void main(String[] args) {
        Employee employee = new Employee(1, "Alice", "HR");
        LeaveRequest leaveRequest = new LeaveRequest(101, employee, "2024-07-01", "2024-07-05");

        System.out.println("Employee: " + employee.getName());
        System.out.println("Current Leave Balance: " + employee.getLeaveBalance());
        System.out.println("Requested 5 days leave.");
        employee.requestLeave(5);
        System.out.println("Leave Balance: " + employee.getLeaveBalance());
        System.out.println("Leave Request ID: " + leaveRequest.getRequestId());
        System.out.println("Leave Status: " + leaveRequest.getStatus());
        System.out.println("Leave Period: " + leaveRequest.getStartDate() + " to " + leaveRequest.getEndDate());
        System.out.println("Requested by: " + leaveRequest.getEmployee().getName());
        System.out.println("Employee Department: " + leaveRequest.getEmployee().getDepartment());
        System.out.println("Employee ID: " + leaveRequest.getEmployee().getEmployeeId());
        System.out.println("Employee Leave Balance: " + leaveRequest.getEmployee().getLeaveBalance());

        System.out.println("---");
        System.out.println("Employee: " + employee.getName());
        System.out.println("Current Leave Balance: " + employee.getLeaveBalance());
        System.out.println("Requested 16 days leave.");
        employee.requestLeave(16);
        System.out.println("Leave Balance: " + employee.getLeaveBalance());
    }
}