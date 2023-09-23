/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package controller;

import java.util.ArrayList;
import model.Report_Student;
import model.Student;
import view.Menu;

/**
 *
 * @author DAO
 */
public class STUDENT_MANAGEMENT extends Menu<String>  {
    static String[] mc = {"Create","Find and Sort","Update/Delete","Report", "Exit"};
    private ArrayList<Student> students ;
    private Check_Valid checkValid;
    private int count = 0;
    public STUDENT_MANAGEMENT(){
        super("Student Management",mc);
        students = new ArrayList<>();
        checkValid = new Check_Valid();
    }
    
        @Override
    public void execute(int choice) {
        switch(choice){
            case 1 -> {create_Student();
            }
            case 2 -> {
                Find_and_Sort();
            }
            case 3 -> {
                Update_or_Delete();
            }
            case 4 -> {
                report();
            }
            default -> System.out.println("Try again");
        }
    }
    
    public void create_Student(){
        //if number of students greater than 10 ask user continue or not
        if (count > 9) {
            System.out.print("Do you want to continue (Y/N): ");
            if (!checkValid.checkInputYN()) {
                return;
            }
        }
         while (true) {
            System.out.print("Enter id: ");
            String id = checkValid.checkInputString();
            System.out.print("Enter name student: ");
            String name = checkValid.checkInputString();
            if (!checkValid.checkIdExist(students, id, name)) {
                System.err.println("Id has exist student. Pleas re-input.");
                continue;
            }
            System.out.print("Enter semester: ");
            String semester = checkValid.checkInputString();
            System.out.print("Enter name course: ");
            String course = checkValid.checkInputCourse();
            //check student exist or not
            if (checkValid.checkStudentExist(students, id, name, semester, course)) {
                students.add(new Student(id, name, semester, course));
                count++;
                System.out.println("Add student success.");
                return;
            }
            System.err.println("Duplicate.");

        }
    }

 public void Find_and_Sort() {
    // Check list empty 
    if (students.isEmpty()) {
        System.err.println("List empty.");
        return;
    }
    
    System.out.print("Enter name to search: ");
    String name = checkValid.checkInputString();
    
    boolean found = false; // Sử dụng để kiểm tra xem có sinh viên nào thỏa mãn hay không
    
    System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");
    
    // Lặp qua danh sách sinh viên và in ra thông tin của các sinh viên thỏa mãn
    for (Student student : students) {
        if (student.getStudent_Name().contains(name)) {
            student.print();
            found = true; // Đánh dấu là đã tìm thấy ít nhất một sinh viên thỏa mãn
        }
    }
    
    if (!found) {
        System.err.println("Not exist!");
    }
}


 public void Update_or_Delete() {
    // Check list empty
    if (students.isEmpty()) {
        System.err.println("List empty.");
        return;
    }

    System.out.print("Enter student ID: ");
    String id = checkValid.checkInputString();

    // Tìm sinh viên có ID tương ứng
    ArrayList<Student> studentsToUpdateOrDelete = new ArrayList<>();
    for (Student student : students) {
        if (student.getID().equalsIgnoreCase(id)) {
            studentsToUpdateOrDelete.add(student);
        }
    }

    if (studentsToUpdateOrDelete.isEmpty()) {
        System.err.println("Student with ID " + id + " not found.");
        return; // Không tìm thấy sinh viên, in ra thông báo lỗi và thoát
    }

    System.out.println("List of students with ID " + id + ":");
    int Count = 1;
    System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");
    for (Student student : studentsToUpdateOrDelete) {
        System.out.println(Count + ". " );
        student.print();
        Count++;
    }

    System.out.print("Do you want to update (U) or delete (D) student? ");
    if (checkValid.checkInputUD()) {
        System.out.print("Enter the number of the student to update/delete: ");
        int choice = checkValid.checkInputIntLimit(1, studentsToUpdateOrDelete.size());

        Student studentToUpdateOrDelete = studentsToUpdateOrDelete.get(choice - 1);

        // Cập nhật sinh viên
        System.out.print("Enter new ID: ");
        String newId = checkValid.checkInputString();
        System.out.print("Enter new student name: ");
        String newName = checkValid.checkInputString();
        System.out.print("Enter new semester: ");
        String newSemester = checkValid.checkInputString();
        System.out.print("Enter new course name: ");
        String newCourse = checkValid.checkInputCourse();

        if (!checkValid.checkChangeInfomation(studentToUpdateOrDelete, newId, newName, newSemester, newCourse)) {
            System.err.println("Nothing changed.");
            return;
        }

        if (checkValid.checkStudentExist(students, newId, newName, newSemester, newCourse)) {
            studentToUpdateOrDelete.setID(newId);
            studentToUpdateOrDelete.setStudent_Name(newName);
            studentToUpdateOrDelete.setSemester(newSemester);
            studentToUpdateOrDelete.setCourse_Name(newCourse);
            System.err.println("Update success.");
        }
    } else {
    // Xóa sinh viên
       students.removeAll(studentsToUpdateOrDelete);
       count -= studentsToUpdateOrDelete.size();
       System.err.println("Delete success.");

    }
}

    
//// Sửa hàm report
//public void report() {
//    if (students.isEmpty()) {
//        System.err.println("List empty.");
//        return;
//    }
//    
//    HashMap<String, HashMap<String, Integer>> studentCourseCounts = new HashMap<>();
//
//    for (Student student : students) {
//        String studentName = student.getStudent_Name();
//        String courseName = student.getCourse_Name();
//
//        // Nếu sinh viên chưa có thông tin trong HashMap, thêm nó vào
//        studentCourseCounts.putIfAbsent(studentName, new HashMap<>());
//
//        // Lấy HashMap của sinh viên đó
//        HashMap<String, Integer> courseCounts = studentCourseCounts.get(studentName);
//
//        // Tăng số lượng khóa học cho sinh viên đó cho khóa học cụ thể
//        courseCounts.put(courseName, courseCounts.getOrDefault(courseName, 0) + 1);
//    }
//
//    // In ra thông tin từ HashMap
//    for (String studentName : studentCourseCounts.keySet()) {
//        HashMap<String, Integer> courseCounts = studentCourseCounts.get(studentName);
//        System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");
//        for (String courseName : courseCounts.keySet()) {
//            int totalCourses = courseCounts.get(courseName);
//            System.out.printf("%-15s|%-15s|%-15d\n", studentName, courseName, totalCourses);
//        }
//    }
//}
  
   public void report() {
    if (students.isEmpty()) {
        System.err.println("List empty.");
        return;
    }

    // Tạo danh sách sinh viên duyệt qua
    ArrayList<Student> uniqueStudents = new ArrayList<>();
    ArrayList<Report_Student> reportList = new ArrayList<>();
    for (Student student : students) {
        boolean found = false;
        // Kiểm tra xem sinh viên đã được thêm vào danh sách chưa
        for (Student uniqueStudent : uniqueStudents) {
            if (uniqueStudent.getStudent_Name().equals(student.getStudent_Name()) &&
                uniqueStudent.getCourse_Name().equals(student.getCourse_Name())) {
                found = true;
                break;
            }
        }
        // Nếu sinh viên chưa được thêm vào danh sách, thêm nó vào
        if (!found) {
            uniqueStudents.add(student);
        }
    }

    // In ra thông tin từ danh sách đã tạo
    System.out.printf("%-15s%-15s%-15s\n", "Student name", "Course Name", "Total Courses");
    for (Student uniqueStudent : uniqueStudents) {
        int totalCourses = countTotalCourses(uniqueStudent.getStudent_Name(), uniqueStudent.getCourse_Name());
        reportList.add(new Report_Student(uniqueStudent.getStudent_Name(), uniqueStudent.getCourse_Name(), totalCourses));
    }
    
    for(Report_Student rp : reportList){
        System.out.printf("%-15s%-15s%-15s\n", rp.getStudentName(), rp.getCourseName(), rp.getTotalCourse());
    }
}

// Hàm đếm tổng số lượng khóa học của một sinh viên cho một khóa học cụ thể
private int countTotalCourses(String studentName, String courseName) {
    int totalCourses = 0;
    for (Student student : students) {
        if (student.getStudent_Name().equals(studentName) && student.getCourse_Name().equals(courseName)) {
            totalCourses++;
        }
    }
    return totalCourses;
}

}
