package university;

import university.exception.CourseFullException;
import university.exception.InvalidGradeException;
import university.exception.StudentNotFoundException;
import university.model.Course;
import university.model.CourseType;
import university.model.Grade;
import university.model.Student;
import university.model.Teacher;
import university.service.UniversityService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UniversityService service = new UniversityService();

    public static void main(String[] args) {
        service.loadData();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose option: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addTeacher();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    enrollStudent();
                    break;
                case 5:
                    assignGrade();
                    break;
                case 6:
                    listStudents();
                    break;
                case 7:
                    listTeachers();
                    break;
                case 8:
                    listCourses();
                    break;
                case 9:
                    listEnrollments();
                    break;
                case 10:
                    saveData();
                    break;
                case 0:
                    saveData();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("Program ended.");
    }

    private static void printMenu() {
        System.out.println("\n=== University Management System ===");
        System.out.println("1. Add student");
        System.out.println("2. Add teacher");
        System.out.println("3. Add course");
        System.out.println("4. Enroll student");
        System.out.println("5. Assign grade");
        System.out.println("6. List students");
        System.out.println("7. List teachers");
        System.out.println("8. List courses");
        System.out.println("9. List enrollments");
        System.out.println("10. Save data");
        System.out.println("0. Exit");
    }

    private static void addStudent() {
        String name = readString("Name: ");
        String address = readString("Address: ");
        int studentId = readInt("Student ID: ");
        Student student = new Student(name, address, studentId);
        service.addStudent(student);
        System.out.println("Student added.");
    }

    private static void addTeacher() {
        String name = readString("Name: ");
        String address = readString("Address: ");
        double salary = readDouble("Salary (euro): ");
        Teacher teacher = new Teacher(name, address, salary);
        service.addTeacher(teacher);
        System.out.println("Teacher added.");
    }

    private static void addCourse() {
        String code = readString("Course code: ");
        String name = readString("Course name: ");
        int maxStudents = readInt("Max students: ");
        CourseType type = readCourseType();
        Course course = new Course(code, name, maxStudents, type);
        service.addCourse(course);
        System.out.println("Course added.");
    }

    private static void enrollStudent() {
        int studentId = readInt("Student ID: ");
        String courseCode = readString("Course code: ");
        try {
            service.enrollStudent(studentId, courseCode);
            System.out.println("Enrollment successful.");
        } catch (StudentNotFoundException | CourseFullException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void assignGrade() {
        int studentId = readInt("Student ID: ");
        String courseCode = readString("Course code: ");
        Grade grade = readGrade();
        try {
            service.assignGrade(studentId, courseCode, grade);
            System.out.println("Grade assigned.");
        } catch (InvalidGradeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listStudents() {
        service.getStudents().forEach(System.out::println);
    }

    private static void listTeachers() {
        service.getTeachers().forEach(System.out::println);
    }

    private static void listCourses() {
        service.getCourses().forEach(System.out::println);
    }

    private static void listEnrollments() {
        service.getEnrollments().forEach(System.out::println);
    }

    private static void saveData() {
        service.saveData();
        System.out.println("Data saved.");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static Grade readGrade() {
        System.out.println("Choose grade: A, B, C, D, E, F");
        while (true) {
            String input = readString("Grade: ").toUpperCase();
            try {
                return Grade.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid grade.");
            }
        }
    }

    private static CourseType readCourseType() {
        System.out.println("Choose type: THEORETICAL, PRACTICAL, LABORATORY");
        while (true) {
            String input = readString("CourseType: ").toUpperCase();
            try {
                return CourseType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid course type.");
            }
        }
    }
}
