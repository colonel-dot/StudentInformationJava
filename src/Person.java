import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String sex;
    private int age;
    private String account;
    private String password;
    Person() {

    }
    public Person(String name, String sex, int age, String account, String password) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.account = account;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
