package com.schoolapp.desenho.schoolapp.models;

import java.util.Date;

public class Exam extends Event{
    private Float grade;
    private String contentExam;

    public Exam(Integer eventId, Date dateEvent, Date startTime, Date endTime, String localEvent,
                Integer disciplineClassId, Float grade, String contentExam){
        super(eventId, dateEvent, startTime, endTime, localEvent, disciplineClassId);
        setGrade(grade);
        setContentExam(contentExam);
    }

    public float getGrade() {
        return this.grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setContentExam(String contentExam){
        this.contentExam = contentExam;
    }

    public String getContentExam (){
        return this.contentExam;
    }

}
