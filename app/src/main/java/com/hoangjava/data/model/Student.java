package com.hoangjava.data.model;

import java.util.HashSet;
import java.util.Set;

public class Student {
    private String name;
    private String studentClass;
    private Set<Hobby> hobbies;
    private Gender gender;

    public Student(String name, String studentClass, Set<Hobby> hobby, Gender gender) {
        this.name = name;
        this.studentClass = studentClass;
        this.hobbies = hobby;
        this.gender = gender;
    }

    public Student(){
        this.name = "";
        this.studentClass = "";
        this.hobbies = new HashSet<>();
        this.gender = Gender.Female;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}


