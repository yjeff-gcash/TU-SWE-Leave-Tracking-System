package org.example.request;

import org.example.interfaces.Approvable;
import org.example.model.Employee;

import java.util.ArrayList;

public abstract class LeaveRequest implements Approvable {
    private int requestId;
    private Employee employee;
    private String startDate;
    private String endDate;
    private String status;
    private String leaveType;
    private ArrayList<StatusChange> statusHistory = new ArrayList<>();

    public abstract int calculateLeaveDays();
    public boolean processRequest(){
        System.out.println("Processing generic leave request...");
        return true;
    };

    public void printStatusHistory(){
        for(StatusChange change : statusHistory){
            System.out.println("Status: " + change.newStatus + ", Date: " + change.changeDate + ", Changed By: " + change.changedBy);
        }
    }

    public LeaveRequest(int requestId, Employee employee, String startDate, String endDate, String leaveType){
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
        this.leaveType = leaveType;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeaveType() { return leaveType; }

    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }

    @Override
    public boolean approve(String approverName) {
        StatusChange change = new StatusChange("Approved", java.time.LocalDate.now().toString(), approverName);
        setStatus("Approved by " + approverName);
        statusHistory.add(change);
        return true;
    }

    @Override
    public boolean deny(String approverName, String reason) {
        StatusChange change = new StatusChange("Denied", java.time.LocalDate.now().toString(), approverName);
        setStatus("Denied by " + approverName + ". Reason: " + reason);
        statusHistory.add(change);
        return false;
    }

    public class StatusChange{
        private String newStatus;
        private String changeDate;
        private String changedBy;

        public StatusChange(String newStatus, String changeDate, String changedBy){
            this.newStatus = newStatus;
            this.changeDate = changeDate;
            this.changedBy = changedBy;
        }
    }
}


