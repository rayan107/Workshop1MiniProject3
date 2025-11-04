package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CoursesStore {
    private static CoursesStore instance;
    private ObservableList<Course> courses;

    private CoursesStore() {
        courses = FXCollections.observableArrayList();
        // Add some sample data
        courses.add(new Course("CSC101", "Introduction to Programming", "Dr. Smith", 3, 45));
        courses.add(new Course("CSC201", "Data Structures", "Dr. Johnson", 4, 38));
        courses.add(new Course("MAT101", "Calculus I", "Dr. Brown", 3, 50));
    }

    public static CoursesStore getInstance() {
        if (instance == null) {
            instance = new CoursesStore();
        }
        return instance;
    }

    public ObservableList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Course getCourse(String crn) {
        for (Course course : courses) {
            if (course.getCrn().equals(crn)) {
                return course;
            }
        }
        return null;
    }

    public boolean updateCourse(String oldCrn, Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCrn().equals(oldCrn)) {
                courses.set(i, updatedCourse);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String crn) {
        return courses.removeIf(course -> course.getCrn().equals(crn));
    }
}