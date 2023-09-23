/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DAO
 */
public class Student implements Comparable<Student> {
    private String ID;
    private String Student_Name;
    private String semester;
    private String course_Name;

    public Student(String ID, String Student_Name, String semester, String course_Name) {
        this.ID = ID;
        this.Student_Name = Student_Name;
        this.semester = semester;
        this.course_Name = course_Name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStudent_Name() {
        return Student_Name;
    }

    public void setStudent_Name(String Student_Name) {
        this.Student_Name = Student_Name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourse_Name() {
        return course_Name;
    }

    public void setCourse_Name(String course_Name) {
        this.course_Name = course_Name;
    }
    
    @Override
    public int compareTo(Student t) {
        return t.getStudent_Name().compareTo(this.getStudent_Name());
    }
    
     public void print() {
        System.out.printf("%-15s%-15s%-15s\n", Student_Name, semester, course_Name);
    }
    
    
}
