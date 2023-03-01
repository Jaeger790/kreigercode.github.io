package tech.school.schoolapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = Instructor.class, parentColumns = "id",childColumns = "instructorId", onDelete = ForeignKey.CASCADE),
                        @ForeignKey(entity = Term.class,parentColumns = "id",childColumns = "termId",onDelete = ForeignKey.CASCADE)})
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private Date startDate;
    @ColumnInfo
    private Date endDate;
    @ColumnInfo
    private String status;
    @ColumnInfo
    private int instructorId;
    @ColumnInfo
    private int termId;
    @ColumnInfo
    private String note;


    public Course(int id, String title, Date startDate, Date endDate, String status, int instructorId, int termId, String note) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instructorId = instructorId;
        this.termId = termId;
        this.note = note;
    }

    public Course(int id, String title, Date startDate, Date endDate, String status, int instructorId, int termId) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instructorId = instructorId;
        this.termId = termId;

    }

    public Course(String title, Date startDate, Date endDate, String status,int instructorId) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instructorId = instructorId;
    }

    public Course(int id, String title, int instructorId) {
        this.id = id;
        this.title = title;
        this.instructorId = instructorId;

    }
    public Course() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString(){
       return title;
    }

}



