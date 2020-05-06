package org.techtown.energychain;

import android.os.Parcel;
import android.os.Parcelable;

public class mInFo implements Parcelable {
    String id_loggedIn;
    String pw_loggedIn;
    String name_loggedIn;
    String ph_loggedIn;
    String email_loggedIn;
    String residentnum_loggedIn;
    String bank_loggedIn;
    String banknum_loggedIn;
    String carnum_loggedIn;

    // 생성자
    public mInFo(String id_loggedIn, String pw_loggedIn, String name_loggedIn, String ph_loggedIn, String email_loggedIn, String residentnum_loggedIn, String bank_loggedIn, String banknum_loggedIn, String carnum_loggedIn){
        this.id_loggedIn = id_loggedIn;
        this.pw_loggedIn = pw_loggedIn;
        this.name_loggedIn = name_loggedIn;
        this.ph_loggedIn = ph_loggedIn;
        this.email_loggedIn = email_loggedIn;
        this.residentnum_loggedIn = residentnum_loggedIn;
        this.bank_loggedIn = bank_loggedIn;
        this.banknum_loggedIn = banknum_loggedIn;
        this.carnum_loggedIn = banknum_loggedIn;
    }

    public mInFo(Parcel in) {
        id_loggedIn = in.readString();
        pw_loggedIn = in.readString();
        name_loggedIn = in.readString();
        ph_loggedIn = in.readString();
        email_loggedIn = in.readString();
        residentnum_loggedIn = in.readString();
        bank_loggedIn = in.readString();
        banknum_loggedIn = in.readString();
        carnum_loggedIn = in.readString();
    }

    public static final Parcelable.Creator<mInFo> CREATOR = new Creator<mInFo>() {
        @Override
        public mInFo createFromParcel(Parcel in) {
            return new mInFo(in);
        }

        @Override
        public mInFo[] newArray(int size) {
            return new mInFo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_loggedIn);
        dest.writeString(pw_loggedIn);
        dest.writeString(name_loggedIn);
        dest.writeString(ph_loggedIn);
        dest.writeString(email_loggedIn);
        dest.writeString(residentnum_loggedIn);
        dest.writeString(bank_loggedIn);
        dest.writeString(banknum_loggedIn);
        dest.writeString(carnum_loggedIn);
    }
}
