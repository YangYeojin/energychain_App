package org.techtown.energychain;

import android.os.Parcel;
import android.os.Parcelable;

public class kwInFo implements Parcelable {
    String kw_loggedIn;

    public kwInFo(String kw){
        this.kw_loggedIn = kw;
    }

    public kwInFo(Parcel in){
        kw_loggedIn=in.readString();
    }

    public static final Parcelable.Creator<kwInFo> CREATOR = new Creator<kwInFo>() {
        @Override
        public kwInFo createFromParcel(Parcel in) {
            return new kwInFo(in);
        }

        @Override
        public kwInFo[] newArray(int size) {
            return new kwInFo[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kw_loggedIn);
    }

}

