package com.example.shivamgupta.attendancemanager;

/**
 * Created by Shivam Gupta on 09-01-2018.
 */

public class subjects {
    String subjectName;
    Float percentage;

    public subjects(String subjectName, Float percentage) {
        this.subjectName = subjectName;
        this.percentage = percentage;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Float getPercentage() {
        return percentage;
    }
}