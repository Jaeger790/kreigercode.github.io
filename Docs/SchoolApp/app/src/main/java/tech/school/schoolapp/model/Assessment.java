package tech.school.schoolapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import tech.school.schoolapp.model.AssessmentType;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Course.class,parentColumns = "id",childColumns = "courseId",onDelete = ForeignKey.CASCADE))
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String type;
    @ColumnInfo
    private Date endDate;
    @ColumnInfo
    private int courseId;

    public Assessment(String type, int id, String title, Date endDate, int courseId) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.endDate = endDate;
        this.courseId = courseId;
    }

    public Assessment() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return title;
    }
}
