package org.example.request;

import org.example.model.Employee;

public class MaternityLeaveRequest extends LeaveRequest {
    private String expectedDeliveryDate;

    public MaternityLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String expectedDeliveryDate) {
        super(requestId, employee, startDate, endDate, "Maternity Leave");
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    @Override
    public int calculateLeaveDays() {
        return 90;
    }

    @Override
    public boolean processRequest(){
        System.out.println("Processing maternity leave request with special considerations...");
        return true;
    }
}
