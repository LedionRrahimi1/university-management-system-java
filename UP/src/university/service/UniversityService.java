package university.service;

import university.exception.CourseFullException;
import university.exception.InvalidGradeException;
import university.exception.StudentNotFoundException;
import university.model.Course;
import university.model.Enrollment;
import university.model.Grade;
import university.model.Student;
import university.model.Teacher;
import university.util.FileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UniversityService {
    private final List<Student> students = new ArrayList<>();
    private final List<Teacher> teachers = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<Enrollment> enrollments = new ArrayList<>();

    private final Map<Course, Set<Student>> courseRegistrations = new HashMap<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void addCourse(Course course) {
        courses.add(course);
        courseRegistrations.putIfAbsent(course, new HashSet<>());
    }

    public void enrollStudent(int studentId, String courseCode)
            throws StudentNotFoundException, CourseFullException {
        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);

        Set<Student> set = courseRegistrations.get(course);
        if (set.size() >= course.getMaxStudents()) {
            throw new CourseFullException("The course is full.");
        }
        if (set.add(student)) {
            enrollments.add(new Enrollment(student, course));
            student.study();
        }
    }

    public void assignGrade(int studentId, String courseCode, Grade grade)
            throws InvalidGradeException {
        if (grade == null) {
            throw new InvalidGradeException("Invalid grade.");
        }
        for (Enrollment e : enrollments) {
            if (e.getStudent().getStudentId() == studentId &&
                    e.getCourse().getCourseCode().equalsIgnoreCase(courseCode)) {
                e.assignGrade(grade);
                return;
            }
        }
        throw new InvalidGradeException("Enrollment not found.");
    }

    private Student findStudentById(int studentId) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                return s;
            }
        }
        throw new StudentNotFoundException("Student not found.");
    }

    private Course findCourseByCode(String code) {
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void saveData() {
        FileManager.saveStudents(students);
        FileManager.saveCourses(courses);
    }

    public void loadData() {
        students.clear();
        courses.clear();
        students.addAll(FileManager.loadStudents());
        courses.addAll(FileManager.loadCourses());

        for (Course c : courses) {
            courseRegistrations.putIfAbsent(c, new HashSet<>());
        }
    }
}