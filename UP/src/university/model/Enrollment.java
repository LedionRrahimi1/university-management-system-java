package university.model;

import university.contract.Gradable;

import java.time.LocalDate;

public class Enrollment implements Gradable {
    private Student student;
    private Course course;
    private Grade grade;
    private LocalDate enrollmentDate;

    public Enrollment(Student student, Course course){
        this.student = student;
        this.course = course;
        this.grade = Grade.NONE;
        this.enrollmentDate = LocalDate.now();
    }
    public Student getStudent(){
        return this.student;
    }
    public Course getCourse(){
        return this.course;
    }
    public Grade getGrade(){
        return this.grade;
    }
    public LocalDate getEnrollmentDate(){
        return this.enrollmentDate;
    }
    @Override
    public void assignGrade(Grade grade){
        this.grade = grade;
    }
    @Override
    public String toString(){
        return "Enrollment: " + this.student.getName() + " - "
                + this.course.getName() + " (" + this.grade + ", " + this.enrollmentDate + ")";
    }
}
