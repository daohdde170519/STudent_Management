/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.Student;

/**
 *
 * @author DAO
 */
public class Check_Valid {
    private final static Scanner sc = new Scanner(System.in);
    
    public String checkInputString() {
    String input;
    while (true) {
        input = sc.nextLine().trim();
        if (!input.isEmpty()) {
            return input;
        } else {
            System.out.print("Please enter a valid string: ");
        }
    }
   }
   
    public boolean checkIdExist(ArrayList<Student> students, String id, String name) {
        for (Student student : students) {
            if (id.equalsIgnoreCase(student.getID())
                    && !name.equalsIgnoreCase(student.getStudent_Name())) {
                return false;
            }
        }
        return true;
    }

    //check user input course
    public  String checkInputCourse() {
        //loop until user input correct
        while (true) {
            String result = checkInputString().trim();
            //check input course in java/ .net/ c/c++
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            System.err.println("There are only three courses: Java, .Net, C/C++");
            System.out.print("Enter again: ");
        }
    }
    
    //check student exist
    public boolean checkStudentExist(ArrayList<Student> ls, String id,
            String studentName, String semester, String courseName) {
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getID())
                    && studentName.equalsIgnoreCase(student.getStudent_Name())
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourse_Name())) {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkInputYN() {
    String input;
    while (true) {
        input = sc.nextLine().trim().toUpperCase();
        switch (input) {
            case "Y", "YES" -> {
                return true; // Nếu người dùng nhập "Y" hoặc "YES", trả về true
            }
            case "N", "NO" -> {
                return false; // Nếu người dùng nhập "N" hoặc "NO", trả về false
            }
            default -> System.out.print("Please enter Y/N: ");
        }
    }
}

   public boolean checkInputUD(){
           String input;
    while (true) {
        input = sc.nextLine().trim().toUpperCase();
        switch (input) {
            case "U", "UPDATE" -> {
                return true; 
            }
            case "N", "DELETE" -> {
                return false; 
            }
            default -> System.out.print("Please enter Update/Delete: ");
        }
    }
   }
   
    //check user change or not
    public boolean checkChangeInfomation(Student student, String id,
            String name, String semester, String course) {
        return !(id.equalsIgnoreCase(student.getID())
                && name.equalsIgnoreCase(student.getStudent_Name())
                && semester.equalsIgnoreCase(student.getSemester())
                && course.equalsIgnoreCase(student.getCourse_Name()));
    }
    
        //check user input number limit
    public int checkInputIntLimit(int min, int max) {
        //loop until user input correct
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }
}
