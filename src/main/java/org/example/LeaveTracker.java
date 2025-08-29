package org.example;

import org.example.model.Employee;
import org.example.request.LeaveRequest;
import org.example.request.MaternityLeaveRequest;
import org.example.request.SickLeaveRequest;
import org.example.request.VacationLeaveRequest;

import java.util.ArrayList;
import java.util.Scanner;

public class LeaveTracker {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int request = 1;
        int empChoice;
        int leaveType;
        ArrayList<LeaveRequest> requests = new ArrayList<>();

        while (true) {
            System.out.print("""
                    ------------------------------------------
                    Welcome to the HR Leave Management System!
                    --- Main Menu ---
                    1. Create New Leave Request
                    2. Process a Pending Request
                    3. View All Leave Request Histories
                    4. Exit
                    Choose an option (1-4):""");
            int choice = console.nextInt();
            switch (choice) {
                case 1:
                    while(true) {
                        System.out.print("""
                                -- Create New Leave Request --
                                Select an employee:
                                1. Alice\s
                                2. Bob\s
                                Enter employee number:""");
                        empChoice = console.nextInt();
                        if (empChoice >= 1 && empChoice <= 2) {
                            break;
                        }
                        System.out.println("Invalid employee selection.");
                    }

                    while(true) {
                        System.out.print("""
                                Select leave type:
                                1. Sick Leave
                                2. Vacation Leave
                                3. Maternity Leave
                                Enter leave type number:""");
                        leaveType = console.nextInt();
                        if (leaveType >= 1 && leaveType <= 3) {
                            break;
                        }
                        System.out.println("Invalid leave type selection.");
                    }

                    Employee employee = empChoice == 1 ? new Employee(1, "Alice", "HR") : new Employee(2, "Bob", "IT");
                    LeaveRequest leaveRequest;

                    System.out.print("Enter start date (YYYY-MM-DD):");
                    String startDate = console.next();
                    System.out.print("Enter end date (YYYY-MM-DD):");
                    String endDate = console.next();

                    switch (leaveType) {
                        case 1:
                            System.out.print("Is a medical certificate provided? (true/false):");
                            boolean hasMedicalCertificate = console.nextBoolean();

                            leaveRequest = new SickLeaveRequest(request++, employee, startDate, endDate, hasMedicalCertificate);
                            requests.add(leaveRequest);
                            break;
                        case 2:
                            System.out.println("Is this paid time off? (true/false):");
                            boolean isPaidTimeOff = console.nextBoolean();

                            leaveRequest = new VacationLeaveRequest(request++, employee, startDate, endDate, isPaidTimeOff);
                            requests.add(leaveRequest);
                            break;
                        case 3:
                            System.out.println("Enter expected delivery date (YYYY-MM-DD):");
                            String expectedDeliveryDate = console.next();

                            leaveRequest = new MaternityLeaveRequest(request++, employee, startDate, endDate, expectedDeliveryDate);
                            requests.add(leaveRequest);
                            break;
                        default:
                            System.out.println("Invalid leave type.");
                            continue;
                    }

                    if (leaveRequest.processRequest()) {
                        System.out.println("Successfully created " + leaveRequest.getClass().getSimpleName() +
                                " for " + employee.getName());
                    }
                    break;
                case 2:
                    System.out.println("-- Processing Next Pending Request --");
                    LeaveRequest pending = requests.stream()
                            .filter(r -> r.getStatus().equals("Pending"))
                            .findFirst()
                            .orElse(null);

                    if (pending == null) {
                        System.out.println("No pending requests.");
                        break;
                    }

                    System.out.println("Request ID: " + pending.getRequestId());
                    System.out.println("Employee: " + pending.getEmployee().getName());
                    System.out.println("Leave Type: " + pending.getLeaveType());
                    System.out.println("Dates: " + pending.getStartDate() + " to " + pending.getEndDate());
                    System.out.println("Status: " + pending.getStatus());

                    boolean approved = pending.processRequest();
                    if (!approved) {
                        System.out.println("Request #" + pending.getRequestId() + " denied by System. Reason: System validation failed.");
                    } else {
                        pending.setStatus("Approved");
                        System.out.println("Request #" + pending.getRequestId() + " " + pending.getStatus());
                    }

                    System.out.println("---- Status History for Request #" + pending.getRequestId() + " ----");
                    pending.printStatusHistory();
                    System.out.println("------------------------------------");
                    break;
                case 3:
                    if (requests.isEmpty()) {
                        System.out.println("No leave requests found.");
                        break;
                    }
                    System.out.println("-- All Leave Request Histories --");
                    for (LeaveRequest req : requests) {
                        System.out.println("Request ID: " + req.getRequestId());
                        System.out.println("Employee: " + req.getEmployee().getName());
                        System.out.println("Leave Type: " + req.getLeaveType());
                        System.out.println("Dates: " + req.getStartDate() + " to " + req.getEndDate());
                        System.out.println("Status: " + req.getStatus());
                        System.out.println("---- Status History ----");
                        req.printStatusHistory();
                        System.out.println("------------------------");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1-4.");
            }
        }
    }
}