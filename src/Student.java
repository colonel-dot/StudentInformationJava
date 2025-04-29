import java.io.Serializable;

public class Student extends Person implements Serializable, Runnable {
    private static final long serialVersionUID = 1L;
    private String number;
    private int score;
    Student() {}

    Student(String name, String sex, int age, String account, String password) {
        super(name, sex, age, account, password);
    }

    public Student(String name, String sex, int age, String number, int score, String account, String password) {
        super(name, sex, age, account, password);
        this.number = number;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number='" + number + '\'' +
                ", score=" + score +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void run() {
        System.out.println(getName() + "进行了签到");
    }
}
