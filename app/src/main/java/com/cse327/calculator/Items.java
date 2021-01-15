package com.cse327.calculator;

public class Items {

    private String credit;
    private String gpa;
    private String total;

    public Items(String credit, String gpa, String total) {
        this.credit = credit;
        this.gpa = gpa;
        this.total = total;
    }

    public String getCredit() {
        return credit;
    }

    public String getGpa() {
        return gpa;
    }

    public String getTotal() {
        return total;
    }

}
