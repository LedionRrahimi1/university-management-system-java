package university.model;

import java.util.Objects;

public class Course {
    private String courseCode;
    private String name;
    private int maxStudents;
    private CourseType type;

    public Course(String courseCode, String name, int maxStudents, CourseType type){
        this.courseCode = courseCode;
        this.name = name;
        this.maxStudents = maxStudents;
        this.type = type;
    }
    public String getCourseCode(){
        return this.courseCode;
    }
    public void setCourseCode(String courseCode){
        this.courseCode = courseCode;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getMaxStudents(){
        return this.maxStudents;
    }
    public void setMaxStudents(int maxStudents){
        this.maxStudents = maxStudents;
    }
    public CourseType getType(){
        return this.type;
    }
    public void setType(CourseType type){
        this.type = type;
    }
   
    @Override
    public String toString(){
        return "Course: " + this.courseCode + " - " + this.name
                + " (" + this.type + ", max students: " + this.maxStudents + ")";

    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode);
    }
    @Override
    public int hashCode(){
        return Objects.hash(courseCode);
    }
}

