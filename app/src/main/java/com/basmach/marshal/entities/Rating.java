package com.basmach.marshal.entities;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.basmach.marshal.localdb.DBConstants;
import com.basmach.marshal.localdb.DBObject;
import com.basmach.marshal.localdb.annotations.Column;
import com.basmach.marshal.localdb.annotations.ColumnGetter;
import com.basmach.marshal.localdb.annotations.ColumnSetter;
import com.basmach.marshal.localdb.annotations.PrimaryKey;
import com.basmach.marshal.localdb.annotations.PrimaryKeySetter;
import com.basmach.marshal.localdb.annotations.TableName;
import com.basmach.marshal.utils.DateHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ido on 5/3/2016.
 */
@TableName(name = DBConstants.T_RATING)
public class Rating extends DBObject implements Parcelable{

    public Rating(Context context) {
        super(context);
    }

    @PrimaryKey(columnName = DBConstants.COL_ID)
    private long id;

    @Column(name = DBConstants.COL_USER_MAIL_ADDRESS)
    @Expose
    @SerializedName(value = "userMailAddress")
    String userMailAddress;

    @Column(name = DBConstants.COL_COURSE_CODE)
    @Expose
    @SerializedName(value = "courseCode")
    String courseCode;

    @Column(name = DBConstants.COL_RATING)
    @Expose
    @SerializedName(value = "rating")
    double rating;

    @Column(name = DBConstants.COL_COMMENT)
    @Expose
    @SerializedName(value = "comment")
    String comment;

    @ColumnGetter(columnName = DBConstants.COL_ID)
    public long getId() {
        return id;
    }

    @PrimaryKeySetter
    @ColumnSetter(columnName = DBConstants.COL_ID, type = TYPE_LONG)
    public void setId(long id) {
        this.id = id;
    }

    @ColumnGetter(columnName = DBConstants.COL_USER_MAIL_ADDRESS)
    public String getUserMailAddress() {
        return userMailAddress;
    }

    @ColumnSetter(columnName = DBConstants.COL_USER_MAIL_ADDRESS, type = TYPE_STRING)
    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    @ColumnGetter(columnName = DBConstants.COL_COURSE_CODE)
    public String getCourseCode() {
        return courseCode;
    }

    @ColumnSetter(columnName = DBConstants.COL_COURSE_CODE, type = TYPE_STRING)
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @ColumnGetter(columnName = DBConstants.COL_RATING)
    public double getRating() {
        return rating;
    }

    @ColumnSetter(columnName = DBConstants.COL_RATING, type = TYPE_DOUBLE)
    public void setRating(double rating) {
        this.rating = rating;
    }

    @ColumnGetter(columnName = DBConstants.COL_COMMENT)
    public String getComment() {
        return comment;
    }

    @ColumnSetter(columnName = DBConstants.COL_COMMENT, type = TYPE_STRING)
    public void setComment(String comment) {
        this.comment = comment;
    }

    ////////////////////// Parcelable methods ////////////////////////
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Storing the Cycle data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(userMailAddress);
        parcel.writeString(courseCode);
        parcel.writeDouble(rating);
        parcel.writeString(comment);
    }

    /**
     * Retrieving Student data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private Rating(Parcel in){
        this.id = in.readLong();
        this.userMailAddress = in.readString();
        this.courseCode = in.readString();
        this.rating = in.readDouble();
        this.comment = in.readString();
    }

    public static final Parcelable.Creator<Rating> CREATOR = new Parcelable.Creator<Rating>() {

        @Override
        public Rating createFromParcel(Parcel source) {
            return new Rating(source);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };
}
