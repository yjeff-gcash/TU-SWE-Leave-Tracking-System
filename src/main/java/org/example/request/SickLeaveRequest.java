package org.example.request;

import org.example.model.Employee;

public class SickLeaveRequest extends LeaveRequest{
    private boolean medicalCertificateProvided;

    public SickLeaveRequest(int requestId, Employee employee, String startDate, String endDate, boolean medicalCertificateProvided) {
        super(requestId, employee, startDate, endDate, "Sick Leave");
        this.medicalCertificateProvided = medicalCertificateProvided;
    }

    @Override
    public int calculateLeaveDays() {
        return 5;
    }

    @Override
    public boolean processRequest(){
        if(calculateLeaveDays() > 2 && !medicalCertificateProvided){
            System.out.println("Sick leave request denied due to lack of medical certificate.");
            return false;
        }else{
            System.out.println("Processing sick leave request...");
            return true;
        }
    }
}
