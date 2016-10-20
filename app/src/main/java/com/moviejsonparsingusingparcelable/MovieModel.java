package com.moviejsonparsingusingparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aalishan on 20/10/16.
 */
public class MovieModel implements Parcelable{

    public String movie;
    public int year;

    public MovieModel() {

    }
    protected MovieModel(Parcel in) {
        movie = in.readString();
        year = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movie);
        dest.writeInt(year);
    }
}
