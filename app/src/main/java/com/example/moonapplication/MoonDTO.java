package com.example.moonapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class MoonDTO implements Parcelable {
    private String lunAge;
    private String solYear;
    private String solMonth;
    private String solDay;
    private String solWeek;

    public MoonDTO() {
    }

    public MoonDTO(String lunAge, String solYear, String solMonth, String solDay, String solWeek) {
        this.lunAge = lunAge;
        this.solYear = solYear;
        this.solMonth = solMonth;
        this.solDay = solDay;
        this.solWeek = solWeek;
    }

    protected MoonDTO(Parcel in) {
        lunAge = in.readString();
        solYear = in.readString();
        solMonth = in.readString();
        solDay = in.readString();
        solWeek = in.readString();
    }

    public static final Creator<MoonDTO> CREATOR = new Creator<MoonDTO>() {
        @Override
        public MoonDTO createFromParcel(Parcel in) {
            return new MoonDTO(in);
        }

        @Override
        public MoonDTO[] newArray(int size) {
            return new MoonDTO[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lunAge);
        dest.writeString(solYear);
        dest.writeString(solMonth);
        dest.writeString(solDay);
        dest.writeString(solWeek);
    }
}
