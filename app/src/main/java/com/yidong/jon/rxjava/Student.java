package com.yidong.jon.rxjava;

/**
 * Created by jon on 2017/2/9
 */

public class Student {
    private String name;
    private Course[] course;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Course[] course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course[] getCourse() {
        return course;
    }

    public void setCourse(Course[] course) {
        this.course = course;
    }
}
