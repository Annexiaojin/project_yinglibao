package com.xa;

public class School {
    private String schName;
    private String schAddr;

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    public String getSchAddr() {
        return schAddr;
    }

    public void setSchAddr(String schAddr) {
        this.schAddr = schAddr;
    }

    @Override
    public String toString() {
        return "School{" +
                "schName='" + schName + '\'' +
                ", schAddr='" + schAddr + '\'' +
                '}';
    }
}
