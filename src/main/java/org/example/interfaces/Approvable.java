package org.example.interfaces;

public interface Approvable {
    boolean approve(String approverName);
    boolean deny(String approverName, String reason);
}
