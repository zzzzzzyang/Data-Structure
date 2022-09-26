package HashCode;
import java.util.HashSet;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int a = 42;
        System.out.println(((Integer)a).hashCode());


        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String d = "code";
        System.out.println(d.hashCode());

        Student student = new Student(3, 2, "BoBo", "liu");
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> map = new HashMap<>();
        map.put(student, 100);

        Student student1 = new Student(3, 2, "BoBo", "liu");
        System.out.println(student1.hashCode());
    }
}
