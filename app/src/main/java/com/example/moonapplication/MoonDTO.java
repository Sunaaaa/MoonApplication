package com.example.moonapplication;

public class MoonDTO {
    private String lunAge;
    private String solYear;
    private String solMonth;
    private String solDay;
    private String solWeek;
    private String imgSrc;

    public MoonDTO(String lunAge, String solYear, String solMonth, String solDay, String solWeek) {
        this.lunAge = lunAge;
        this.solYear = solYear;
        this.solMonth = solMonth;
        this.solDay = solDay;
        this.solWeek = solWeek;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String lunAge) {
        switch (lunAge){
            case "0.1": case "1.1": case "0.4": case "1.4": case "2.4":
                this.imgSrc = "newmoon.png";
            case "3.4": case "4.4": case "5.4": case "6.4": case "7.4": case "8.4":
                this.imgSrc = "ctoymoon.png";
            case "9.4":
                this.imgSrc = "youngmoon.png";
            case "10.4": case "11.4": case "12.4": case "13.4": case "14.4":
                this.imgSrc = "ytofmoon.png";
            case "15.4":
                this.imgSrc = "fullmoon.png";
            case "16.4": case "17.4": case "18.4": case "19.4": case "20.4": case "21.4": case "22.4":
                this.imgSrc = "ftoomoon.png";
            case "23.4":
                this.imgSrc = "oldmoon.png";
            case "24.4": case "25.4": case "26.4": case "27.4": case "28.4":
                this.imgSrc = "darkmoon.png";
        }
        this.imgSrc = imgSrc;
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
