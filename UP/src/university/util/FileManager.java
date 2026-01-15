package university.util;

import university.model.Course;
import university.model.CourseType;
import university.model.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String STUDENTS_FILE = "students.txt";
    private static final String COURSES_FILE = "courses.txt";

    public static void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student s : students) {
                writer.write(s.getName() + ";" + s.getAddress() + ";" + s.getStudentId() + ";" + s.getCredits());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students.");
        }
    }

    public static List<Student> loadStudents() {
        List<Student> list = new ArrayList<>();
        File file = new File(STUDENTS_FILE);
        if (!file.exists()) {
            return list;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    Student s = new Student(parts[0], parts[1], Integer.parseInt(parts[2]));
                    int credits = Integer.parseInt(parts[3]);
                    for (int i = 0; i < credits; i++) {
                        s.study();
                    }
                    list.add(s);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students.");
        }
        return list;
    }

    public static void saveCourses(List<Course> courses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COURSES_FILE))) {
            for (Course c : courses) {
                writer.write(c.getCourseCode() + ";" + c.getName() + ";" + c.getMaxStudents() + ";" + c.getType());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving courses.");
        }
    }

    public static List<Course> loadCourses() {
        List<Course> list = new ArrayList<>();
        File file = new File(COURSES_FILE);
        if (!file.exists()) {
            return list;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    Course c = new Course(parts[0], parts[1], Integer.parseInt(parts[2]),
                            CourseType.valueOf(parts[3]));
                    list.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading courses.");
        }
        return list;
    }
}

