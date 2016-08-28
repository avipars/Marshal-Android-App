package com.basmapp.marshal.entities;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import com.basmapp.marshal.localdb.DBConstants;
import com.basmapp.marshal.localdb.DBObject;
import com.basmapp.marshal.localdb.annotations.Column;
import com.basmapp.marshal.localdb.annotations.ColumnGetter;
import com.basmapp.marshal.localdb.annotations.ColumnSetter;
import com.basmapp.marshal.localdb.annotations.EntityArraySetter;
import com.basmapp.marshal.localdb.annotations.ForeignKeyEntityArray;
import com.basmapp.marshal.localdb.annotations.PrimaryKey;
import com.basmapp.marshal.localdb.annotations.PrimaryKeySetter;
import com.basmapp.marshal.localdb.annotations.TableName;
import com.basmapp.marshal.utils.MarshalServiceProvider;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Date;

@TableName(name = DBConstants.T_COURSE)
public class Course extends DBObject implements Parcelable{

    public static final String CATEGORY_SOFTWARE = "software";
    public static final String CATEGORY_CYBER = "cyber";
    public static final String CATEGORY_IT = "it";
    public static final String CATEGORY_TOOLS = "tools";
    public static final String CATEGORY_SYSTEM = "system";

    // TODO RETROFIT SerializedName
    @PrimaryKey(columnName = DBConstants.COL_ID)
    private long id;

    @Expose
    @SerializedName("CourseCode")
    @Column(name = DBConstants.COL_COURSE_CODE)
    private String courseCode;

    @Expose
    @SerializedName("Name")
    @Column(name = DBConstants.COL_NAME)
    private String name;

    @Expose
    @SerializedName("MinimumPeople")
    @Column(name = DBConstants.COL_MIN_PEOPLE)
    private int minimumPeople;

    @Expose
    @SerializedName("MaximumPeople")
    @Column(name = DBConstants.COL_MAX_PEOPLE)
    private int maximumPeople;

    @Expose
    @SerializedName("Description")
    @Column(name = DBConstants.COL_DESCRIPTION)
    private String description;

    @Column(name = DBConstants.COL_PREREQUISITES)
    private String prerequisites;

    @Expose
    @SerializedName("TargetPopulation")
    @Column(name = DBConstants.COL_TARGET_POPULATION)
    private String targetPopulation;

    @Expose
    @SerializedName("ProfessionalDomain")
    @Column(name = DBConstants.COL_PROFESSIONAL_DOMAIN)
    private String professionalDomain;

    @Expose
    @SerializedName("Syllabus")
    @Column(name = DBConstants.COL_SYLLABUS)
    private String syllabus;

    @Expose
    @SerializedName("DayTime")
    @Column(name = DBConstants.COL_DAYTIME)
    private String dayTime;

    @Expose
    @SerializedName("DurationInHours")
    @Column(name = DBConstants.COL_DURATION_IN_HOURS)
    private int durationInHours;

    @Expose
    @SerializedName("DurationInDays")
    @Column(name = DBConstants.COL_DURATION_IN_DAYS)
    private int durationInDays;

    @Expose
    @SerializedName("Comments")
    @Column(name = DBConstants.COL_COMMENTS)
    private String comments;

    @Expose
    @SerializedName("PassingGrade")
    @Column(name = DBConstants.COL_PASSING_GRADE)
    private int passingGrade;

    @Column(name = DBConstants.COL_PRICE)
    private long price;

    @Expose
    @SerializedName("cycleList")
    @ForeignKeyEntityArray(fkColumnName = DBConstants.COL_CYCLES, entityClass = Cycle.class)
    private ArrayList<Cycle> cycles = new ArrayList<>();

    @Expose
    @SerializedName("PictureUrl")
    @Column(name = DBConstants.COL_IMAGE_URL)
    private String imageUrl;

    @Expose
    @SerializedName("IsMooc")
    @Column(name = DBConstants.COL_IS_MOOC)
    private boolean isMooc;

    @Expose
    @SerializedName("IsMeetup")
    @Column(name = DBConstants.COL_IS_MEETUP)
    private boolean isMeetup;

    @Expose
    @SerializedName("Category")
    @Column(name = DBConstants.COL_CATEGORY)
    private String category;

    @Column(name = DBConstants.COL_IS_USER_SUBSCRIBE)
    private boolean isUserSubscribe;

    public Course (Context context) {
        super(context);
    }

    @ColumnGetter(columnName = DBConstants.COL_ID)
    public long getId() {
        return id;
    }

    @PrimaryKeySetter
    @ColumnSetter(columnName = DBConstants.COL_ID, type = TYPE_LONG)
    public void setId(long id) {
        this.id = id;
    }

    @ColumnGetter(columnName = DBConstants.COL_COURSE_CODE)
    public String getCourseCode() {
        return courseCode;
    }

    @ColumnSetter(columnName = DBConstants.COL_COURSE_CODE, type = TYPE_STRING)
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @ColumnGetter(columnName = DBConstants.COL_NAME)
    public String getName() {
        return name;
    }

    @ColumnSetter(columnName = DBConstants.COL_NAME, type = TYPE_STRING)
    public void setName(String name) {
        this.name = name;
    }

    @ColumnGetter(columnName = DBConstants.COL_MIN_PEOPLE)
    public int getMinimumPeople() {
        return minimumPeople;
    }

    @ColumnSetter(columnName = DBConstants.COL_MIN_PEOPLE, type = TYPE_INT)
    public void setMinimumPeople(int minimumPeople) {
        this.minimumPeople = minimumPeople;
    }

    @ColumnGetter(columnName = DBConstants.COL_MAX_PEOPLE)
    public int getMaximumPeople() {
        return maximumPeople;
    }

    @ColumnSetter(columnName = DBConstants.COL_MAX_PEOPLE, type = TYPE_INT)
    public void setMaximumPeople(int maximumPeople) {
        this.maximumPeople = maximumPeople;
    }

    @ColumnGetter(columnName = DBConstants.COL_DESCRIPTION)
    public String getDescription() {
        return description;
    }

    @ColumnSetter(columnName = DBConstants.COL_DESCRIPTION, type = TYPE_STRING)
    public void setDescription(String description) {
        this.description = description;
    }

    @ColumnGetter(columnName = DBConstants.COL_PREREQUISITES)
    public String getPrerequisites() {
        return prerequisites;
    }

    @ColumnSetter(columnName = DBConstants.COL_PREREQUISITES, type = TYPE_STRING)
    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    @ColumnGetter(columnName = DBConstants.COL_TARGET_POPULATION)
    public String getTargetPopulation() {
        return targetPopulation;
    }

    @ColumnSetter(columnName = DBConstants.COL_TARGET_POPULATION, type = TYPE_STRING)
    public void setTargetPopulation(String targetPopulation) {
        this.targetPopulation = targetPopulation;
    }

    @ColumnGetter(columnName = DBConstants.COL_PROFESSIONAL_DOMAIN)
    public String getProfessionalDomain() {
        return professionalDomain;
    }

    @ColumnSetter(columnName = DBConstants.COL_PROFESSIONAL_DOMAIN, type = TYPE_STRING)
    public void setProfessionalDomain(String professionalDomain) {
        this.professionalDomain = professionalDomain;
    }

    @ColumnGetter(columnName = DBConstants.COL_SYLLABUS)
    public String getSyllabus() {
        return syllabus;
    }

    @ColumnSetter(columnName = DBConstants.COL_SYLLABUS, type = TYPE_STRING)
    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    @ColumnGetter(columnName = DBConstants.COL_DAYTIME)
    public String getDayTime() {
        return dayTime;
    }

    @ColumnSetter(columnName = DBConstants.COL_DAYTIME, type = TYPE_STRING)
    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    @ColumnGetter(columnName = DBConstants.COL_DURATION_IN_HOURS)
    public int getDurationInHours() {
        return durationInHours;
    }

    @ColumnSetter(columnName = DBConstants.COL_DURATION_IN_HOURS, type = TYPE_INT)
    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    @ColumnGetter(columnName = DBConstants.COL_DURATION_IN_DAYS)
    public int getDurationInDays() {
        return durationInDays;
    }

    @ColumnSetter(columnName = DBConstants.COL_DURATION_IN_DAYS, type = TYPE_INT)
    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    @ColumnGetter(columnName = DBConstants.COL_COMMENTS)
    public String getComments() {
        return comments;
    }

    @ColumnSetter(columnName = DBConstants.COL_COMMENTS, type = TYPE_STRING)
    public void setComments(String comments) {
        this.comments = comments;
    }

    @ColumnGetter(columnName = DBConstants.COL_PASSING_GRADE)
    public int getPassingGrade() {
        return passingGrade;
    }

    @ColumnSetter(columnName = DBConstants.COL_PASSING_GRADE, type = TYPE_INT)
    public void setPassingGrade(int passingGrade) {
        this.passingGrade = passingGrade;
    }

    @ColumnGetter(columnName = DBConstants.COL_PRICE)
    public long getPrice() {
        return price;
    }

    @ColumnSetter(columnName = DBConstants.COL_PRICE, type = TYPE_LONG)
    public void setPrice(long price) {
        this.price = price;
    }

    @ColumnGetter(columnName = DBConstants.COL_CYCLES)
    public ArrayList<Cycle> getCycles() {
        return cycles;
    }

    @EntityArraySetter(fkColumnName = DBConstants.COL_CYCLES, entityClass = Cycle.class)
    public void setCycles(ArrayList<Cycle> cycles) {
        this.cycles = cycles;
    }

    @ColumnGetter(columnName = DBConstants.COL_IMAGE_URL)
    public String getImageUrl() {
        return imageUrl;
    }

    @ColumnSetter(columnName = DBConstants.COL_IMAGE_URL, type = TYPE_STRING)
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ColumnGetter(columnName = DBConstants.COL_IS_MOOC)
    public Boolean getIsMooc() {
        return isMooc;
    }

    @ColumnSetter(columnName = DBConstants.COL_IS_MOOC, type = TYPE_BOOLEAN)
    public void setIsMooc(Boolean isMooc) {
        this.isMooc = isMooc;
    }

    @ColumnGetter(columnName = DBConstants.COL_CATEGORY)
    public String getCategory() {
        return category;
    }

    @ColumnSetter(columnName = DBConstants.COL_CATEGORY, type = TYPE_STRING)
    public void setCategory(String category) {
        this.category = category;
    }

    @ColumnGetter(columnName = DBConstants.COL_IS_MEETUP)
    public Boolean getIsMeetup() {
        return isMeetup;
    }

    @ColumnSetter(columnName = DBConstants.COL_IS_MEETUP, type = TYPE_BOOLEAN)
    public void setIsMeetup(Boolean isMeetup) {
        this.isMeetup = isMeetup;
    }

    @ColumnGetter(columnName = DBConstants.COL_IS_USER_SUBSCRIBE)
    public boolean getIsUserSubscribe() {
        return this.isUserSubscribe;
    }

    @ColumnSetter(columnName = DBConstants.COL_IS_USER_SUBSCRIBE, type = TYPE_BOOLEAN)
    public void setIsUserSubscribe(boolean isUserSubscribe) {
        this.isUserSubscribe = isUserSubscribe;
    }

    /////////////////////////// methods ////////////////////////////

    public void addCycle(Cycle cycle) {
        cycles.add(cycle);
    }

    public void getPhotoViaGlide(Context context, final ImageView imageView) {
        Glide.with(context)
                .load(this.getImageUrl())
                .into(imageView);
    }

    ///////////////////// Parcelable methods //////////////////////

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Storing the Course data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(id);
        dest.writeString(courseCode);
        dest.writeString(name);
        dest.writeInt(minimumPeople);
        dest.writeInt(maximumPeople);
        dest.writeString(description);
        dest.writeString(prerequisites);
        dest.writeString(targetPopulation);
        dest.writeString(professionalDomain);
        dest.writeString(syllabus);
        dest.writeString(dayTime);
        dest.writeInt(durationInHours);
        dest.writeInt(durationInDays);
        dest.writeString(comments);
        dest.writeInt(passingGrade);
        dest.writeLong(price);
        dest.writeTypedList(cycles);
        dest.writeString(imageUrl);
        dest.writeString(category);
        dest.writeInt((isMooc) ? 1 : 0);
        dest.writeInt((isMeetup) ? 1 : 0);
        dest.writeInt((isUserSubscribe) ? 1 : 0);
    }

    /**
     * Retrieving Student data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private Course(Parcel in){
        this.id = in.readLong();
        this.courseCode = in.readString();
        this.name = in.readString();
        this.minimumPeople = in.readInt();
        this.maximumPeople = in.readInt();
        this.description = in.readString();
        this.prerequisites = in.readString();
        this.targetPopulation = in.readString();
        this.professionalDomain = in.readString();
        this.syllabus = in.readString();
        this.dayTime = in.readString();
        this.durationInHours = in.readInt();
        this.durationInDays = in.readInt();
        this.comments = in.readString();
        this.passingGrade = in.readInt();
        this.price = in.readLong();
        in.readTypedList(cycles, Cycle.CREATOR);
        this.imageUrl = in.readString();
        this.category = in.readString();
        this.isMooc = (in.readInt() != 0);
        this.isMeetup = (in.readInt() != 0);
        this.isUserSubscribe = (in.readInt() != 0);
    }

    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {

        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public SQLiteStatement getStatement(SQLiteStatement statement, int objectId) throws Exception {
        statement.clearBindings();
        if (getCourseCode() != null && getName() != null && getCategory() != null) {
            statement.bindLong(1, objectId);
            statement.bindString(2, getCourseCode());
            statement.bindString(3, getName());
            statement.bindLong(4, getMinimumPeople());
            statement.bindLong(5, getMaximumPeople());
            if (description == null)
                description = "";
            statement.bindString(6, getDescription());
            if (prerequisites == null)
                prerequisites = "";
            statement.bindString(7, getPrerequisites());
            if (targetPopulation == null)
                targetPopulation = "";
            statement.bindString(8, getTargetPopulation());
            if (professionalDomain == null)
                professionalDomain = "";
            statement.bindString(9, getProfessionalDomain());
            if (syllabus == null)
                syllabus = "";
            statement.bindString(10, getSyllabus());
            if (dayTime == null)
                dayTime = "";
            statement.bindString(11, getDayTime());
            statement.bindLong(12, getDurationInHours());
            statement.bindLong(13, getDurationInDays());
            if (comments == null)
                comments = "";
            statement.bindString(14, getComments());
            statement.bindLong(15, getPassingGrade());
            statement.bindDouble(16, getPrice());
            statement.bindString(17, getCyclesIdsString());
            statement.bindLong(18, (getIsMooc() ? 1 : 0));
            statement.bindLong(19, (getIsMeetup() ? 1 : 0));
            if (category == null)
                category = "";
            statement.bindString(20, getCategory());
            if (imageUrl == null)
                imageUrl = "";
            statement.bindString(21, MarshalServiceProvider.IMAGES_URL + courseCode);

            return statement;
        } else {
            return null;
        }
    }

    private String getCyclesIdsString() throws Exception {
        String ids = "";

        if (getCycles() != null && getCycles().size() > 0) {
            for (Cycle cycle : getCycles()) {
                if (ids.equals(""))
                    ids = String.valueOf(cycle.getId());
                else
                    ids += ("," + String.valueOf(cycle.getId()));
            }
        }

        return ids;
    }

    public static String getCloestCoursesSqlQuery(int count, boolean filterByNowTimestamp) {
        String query = "select * from " + DBConstants.T_COURSE
                + " where " + DBConstants.COL_COURSE_CODE + " IN " +
                "(select distinct " + DBConstants.COL_COURSE_CODE + " from "+ DBConstants.T_CYCLE;

        if (filterByNowTimestamp)
            query += " where " + DBConstants.COL_START_DATE + " >= " + String.valueOf(new Date().getTime());

        query += " order by " + DBConstants.COL_START_DATE + " ASC limit " + String.valueOf(count) + ");\n";

        return query;
    }

    public Cycle getFirstCycle() {
        if (cycles != null && cycles.size() > 0) {
            Cycle firstCycle = cycles.get(0);

            for (Cycle cycle : cycles) {
                try {
                    if (firstCycle.getStartDate() != null && cycle.getStartDate() != null) {
                        if (firstCycle.getStartDate().compareTo(cycle.getStartDate()) > 0) {
                            firstCycle = cycle;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return firstCycle;
        } else {
            return null;
        }
    }

    public String getInsertSql() {
        String sql = null;
        imageUrl = MarshalServiceProvider.IMAGES_URL + courseCode;

        try {
            sql = "INSERT INTO " + DBConstants.T_COURSE + "(" +
                    DBConstants.COL_COURSE_CODE + "," +
                    DBConstants.COL_NAME + "," +
                    DBConstants.COL_MIN_PEOPLE + "," +
                    DBConstants.COL_MAX_PEOPLE + "," +
                    DBConstants.COL_DESCRIPTION + "," +
                    DBConstants.COL_PREREQUISITES + "," +
                    DBConstants.COL_TARGET_POPULATION + "," +
                    DBConstants.COL_PROFESSIONAL_DOMAIN + "," +
                    DBConstants.COL_SYLLABUS + "," +
                    DBConstants.COL_DAYTIME + "," +
                    DBConstants.COL_DURATION_IN_HOURS + "," +
                    DBConstants.COL_DURATION_IN_DAYS + "," +
                    DBConstants.COL_COMMENTS + "," +
                    DBConstants.COL_PASSING_GRADE + "," +
                    DBConstants.COL_PRICE + "," +
                    DBConstants.COL_CYCLES + "," +
                    DBConstants.COL_IS_MOOC + "," +
                    DBConstants.COL_IS_MEETUP + "," +
                    DBConstants.COL_CATEGORY + "," +
                    DBConstants.COL_IMAGE_URL + "," +
                    DBConstants.COL_IS_UP_TO_DATE + ")" +
                    " VALUES (" + prepareStringForSql(courseCode) +
                    "," + prepareStringForSql(name) +
                    "," + minimumPeople +
                    "," + maximumPeople +
                    "," + prepareStringForSql(description) +
                    "," + prepareStringForSql(prerequisites) +
                    "," + prepareStringForSql(targetPopulation) +
                    "," + prepareStringForSql(professionalDomain) +
                    "," + prepareStringForSql(syllabus) +
                    "," + prepareStringForSql(dayTime) +
                    "," + durationInHours +
                    "," + durationInDays +
                    "," + prepareStringForSql(comments) +
                    "," + passingGrade +
                    "," + price +
                    "," + prepareStringForSql(getCyclesIdsString()) +
                    "," + (isMooc ? 1 : 0) +
                    "," + (isMeetup ? 1 : 0) +
                    "," + prepareStringForSql(category) +
                    "," + prepareStringForSql(imageUrl) + ",1);";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sql;
    }

    public String getUpdateSql(long objectId) {
        String sql = null;
        imageUrl = MarshalServiceProvider.IMAGES_URL + courseCode;

        try {
            sql = "UPDATE " + DBConstants.T_COURSE + " SET " +
                    DBConstants.COL_COURSE_CODE + " = " + prepareStringForSql(courseCode) + "," +
                    DBConstants.COL_NAME + " = " + prepareStringForSql(name) + "," +
                    DBConstants.COL_MIN_PEOPLE + " = " + minimumPeople + "," +
                    DBConstants.COL_MAX_PEOPLE + " = " + maximumPeople + "," +
                    DBConstants.COL_DESCRIPTION + " = " + prepareStringForSql(description) + "," +
                    DBConstants.COL_PREREQUISITES + " = " + prepareStringForSql(prerequisites) + "," +
                    DBConstants.COL_TARGET_POPULATION + " = " + prepareStringForSql(targetPopulation) + "," +
                    DBConstants.COL_PROFESSIONAL_DOMAIN + " = " + prepareStringForSql(professionalDomain) + "," +
                    DBConstants.COL_SYLLABUS + " = " + prepareStringForSql(syllabus) + "," +
                    DBConstants.COL_DAYTIME + " = " + prepareStringForSql(dayTime) + "," +
                    DBConstants.COL_DURATION_IN_HOURS + " = " + durationInHours + "," +
                    DBConstants.COL_DURATION_IN_DAYS + " = " + durationInDays + "," +
                    DBConstants.COL_COMMENTS + " = " + prepareStringForSql(comments) + "," +
                    DBConstants.COL_PASSING_GRADE + " = " + passingGrade + "," +
                    DBConstants.COL_PRICE + " = " + price + "," +
                    DBConstants.COL_CYCLES + " = " + prepareStringForSql(getCyclesIdsString()) + "," +
                    DBConstants.COL_IS_MOOC + " = " + (isMooc ? 1 : 0) + "," +
                    DBConstants.COL_IS_MEETUP + " = " + (isMeetup ? 1 : 0) + "," +
                    DBConstants.COL_CATEGORY + " = " + prepareStringForSql(category) + "," +
                    DBConstants.COL_IMAGE_URL + " = " + prepareStringForSql(imageUrl) + "," +
                    DBConstants.COL_IS_UP_TO_DATE + " = 1" +
                    " WHERE " + DBConstants.COL_ID + " = " + objectId + ";";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sql;
    }
}