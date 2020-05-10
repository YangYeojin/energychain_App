package org.techtown.energychain;

import android.os.Parcel;
import android.os.Parcelable;

public class mytokenInFo implements Parcelable {

        String mytoken_loggedIn;

        public mytokenInFo(String mytoken_loggedIn){
            this.mytoken_loggedIn = mytoken_loggedIn;
        }

        public mytokenInFo(Parcel in){
            mytoken_loggedIn=in.readString();
        }

        public static final Parcelable.Creator<org.techtown.energychain.mytokenInFo> CREATOR = new Creator<org.techtown.energychain.mytokenInFo>() {
            @Override
            public org.techtown.energychain.mytokenInFo createFromParcel(Parcel in) {
                return new org.techtown.energychain.mytokenInFo(in);
            }

            @Override
            public org.techtown.energychain.mytokenInFo[] newArray(int size) {
                return new org.techtown.energychain.mytokenInFo[size];
            }
        };

        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(mytoken_loggedIn);
        }

    }



