package com.example.moonapplication;

public class MoonDTO {
    private String lunAge;
    private String solYear;
    private String solMonth;
    private String solDay;
    private String solWeek;

    public MoonDTO(String lunAge, String solYear, String solMonth, String solDay, String solWeek) {
        this.lunAge = lunAge;
        this.solYear = solYear;
        this.solMonth = solMonth;
        this.solDay = solDay;
        this.solWeek = solWeek;
    }

    public String getLunAge() {
        return lunAge;
    }

    public void setLunAge(String lunAge) {
        this.lunAge = lunAge;
    }

    public String getSolYear() {
        return solYear;
    }

    public void setSolYear(String solYear) {
        this.solYear = solYear;
    }

    public String getSolMonth() {
        return solMonth;
    }

    public void setSolMonth(String solMonth) {
        this.solMonth = solMonth;
    }

    public String getSolDay() {
        return solDay;
    }

    public void setSolDay(String solDay) {
        this.solDay = solDay;
    }

    public String getSolWeek() {
        return solWeek;
    }

    public void setSolWeek(String solWeek) {
        this.solWeek = solWeek;
    }
}
