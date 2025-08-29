package org.example.request;

import org.example.model.Employee;

public class VacationLeaveRequest extends LeaveRequest{
    private boolean isPaidTimeOff;

    public VacationLeaveRequest(int requestId, Employee employee, String startDate, String endDate, boolean isPaidTimeOff) {
        super(requestId, employee, startDate, endDate, "Vacation Leave");
        this.isPaidTimeOff = isPaidTimeOff;
    }

    @Override
    public int calculateLeaveDays() {
        return 10;
    }

    @Override
    public boolean processRequest(){
        System.out.println("Processing vacation leave request...");
        return true;
    }
}
