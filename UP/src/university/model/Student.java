package university.model;
import java.util.Objects;
public class Student extends Person {
    private final int studentId;
    private int credits;

    public Student(String name,String address, int studentId){
        super(name, address);
        this.credits= 0;
        this.studentId = studentId;
    }
    public int getStudentId(){
        return this.studentId;
    }
    public int getCredits(){
        return this.credits;
    }
    public void study(){
        this.credits++;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Student)) return false;
        Student student = (Student) o;
        return this.studentId == student.studentId;
    }
    @Override
    public int hashCode(){
        return Objects.hash(studentId);

    }
}
