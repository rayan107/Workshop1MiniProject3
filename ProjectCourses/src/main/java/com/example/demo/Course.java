package com.example.demo;

import javafx.beans.property.*;

public class Course {
    private final StringProperty crn;
    private final StringProperty courseName;
    private final StringProperty instructor;
    private final IntegerProperty credits;
    private final IntegerProperty nbOfStudents;

    public Course(String crn, String courseName, String instructor, int credits, int nbOfStudents) {
        this.crn = new SimpleStringProperty(crn);
        this.courseName = new SimpleStringProperty(courseName);
        this.instructor = new SimpleStringProperty(instructor);
        this.credits = new SimpleIntegerProperty(credits);
        this.nbOfStudents = new SimpleIntegerProperty(nbOfStudents);
    }

    public StringProperty crnProperty() {
        return crn;
    }

    public String getCrn() {
        return crn.get();
    }

    public void setCrn(String crn) {
        this.crn.set(crn);
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public String getCourseName() {
        return courseName.get();
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public StringProperty instructorProperty() {
        return instructor;
    }

    public String getInstructor() {
        return instructor.get();
    }

    public void setInstructor(String instructor) {
        this.instructor.set(instructor);
    }

    public IntegerProperty creditsProperty() {
        return credits;
    }

    public int getCredits() {
        return credits.get();
    }

    public void setCredits(int credits) {
        this.credits.set(credits);
    }

    public IntegerProperty nbOfStudentsProperty() {
        return nbOfStudents;
    }

    public int getNbOfStudents() {
        return nbOfStudents.get();
    }

    public void setNbOfStudents(int nbOfStudents) {
        this.nbOfStudents.set(nbOfStudents);
    }
}
