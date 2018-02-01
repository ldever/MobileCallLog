package com.example.jiashunz.mobilecalllog;

/**
 * Created by zhoujiashun on 1/31/18.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Call implements Parcelable {
    public String phoneNumber;
    public String timeStamp;
    public String callDirection;

    public Call(String phoneNumber, String timeStamp, String callDirection) {
        this.phoneNumber = phoneNumber;
        this.timeStamp = timeStamp;
        this.callDirection = callDirection;
    }

    protected Call(Parcel in) {
        this.phoneNumber = in.readString();
        this.timeStamp = in.readString();
        this.callDirection = in.readString();
    }

    public static final Creator<Call> CREATOR = new Creator<Call>() {
        @Override
        public Call createFromParcel(Parcel in) {
            return new Call(in);
        }

        @Override
        public Call[] newArray(int size) {
            return new Call[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phoneNumber);
        dest.writeString(this.timeStamp);
        dest.writeString(this.callDirection);
    }
}
