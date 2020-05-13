package org.techtown.energychain;

import android.os.Parcel;
import android.os.Parcelable;

public class mykwInFo implements Parcelable {
    String mykw_loggedIn;

    public mykwInFo(String mykw_loggedIn){
        this.mykw_loggedIn = mykw_loggedIn;
    }

    public mykwInFo(Parcel in){
        mykw_loggedIn=in.readString();
    }

    public static final Parcelable.Creator<mykwInFo> CREATOR = new Creator<mykwInFo>() {
        @Override
        public mykwInFo createFromParcel(Parcel in) {
            return new mykwInFo(in);
        }

        @Override
        public mykwInFo[] newArray(int size) {
            return new mykwInFo[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mykw_loggedIn);
    }

}

