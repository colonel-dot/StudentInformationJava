import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String exits;
    static List<Student> students = new ArrayList<>();
    static List<Teacher> teachers = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    static String name, gender, number, account, password;
    static int score, age;
    public static void main(String[] args) throws IOException {
        String flag;
        getStudentInformation();
        getTeacherInformation();

        System.out.println("选择你要进入的端口：\n1.学生端\n2.教师端");
        flag = in.nextLine();
        switch (flag) {
            case "1": studentScreen(); break;
            case "2": teacherScreen(); break;
            default:
                System.out.println("输入有误，输入0重新选择或者其他任意字符退出");
                flag = in.nextLine();
                if(flag.equals("0")) {
                    return;
                }
        }
    }

    public static void studentScreen(){

        Student self = (Student) login(1);
        while(true){
            System.out.print("\033[H\033[2J");  // 或使用Unicode形式：\u001B[H\u001B[2J
            System.out.flush();
            System.out.println("选择你要进行的操作：\n1.查看个人信息\n2.查看班级成绩\n3.上课签到");
            System.out.println("4.退出程序");
            String choice = in.nextLine();
            if(choice.equals("1")) {
                System.out.println(self.getName() + " " + self.getNumber() + " " + self.getAge() + " " + self.getScore());
                System.out.println("输入任意字符返回");
                exits = in.nextLine();
            }else if(choice.equals("2")) {
                for(Student s : students){
                    System.out.println(s.getName() + " " + s.getNumber() + " " + s.getAge() + " " + s.getScore());
                }
                System.out.println("输入任意字符返回");
                exits = in.nextLine();
            }else if(choice.equals("3")) {

            }else if(choice.equals("4")) {
                return;
            }
            else{
                System.out.println("输入错误，输入0重新选择或者其他字符退出");
                choice = in.nextLine();
            }
        }
    }

    public static void teacherScreen() throws IOException {
        TeacherFunctions teacherFunctions = new TeacherFunctions();
        login(2);
        while(true){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("选择你要进行的操作：\n1.添加学生信息\n2.删除学生信息\n3.查看学生信息");
            System.out.println("4.修改学生信息\n5.按成绩进行排序\n6.查看学生签到\n7.退出程序");
            String choice = in.nextLine();
            if(choice.equals("1")) {
                System.out.println("请依次输入学生的姓名，性别，年龄，学号和成绩");
                name = in.nextLine();
                gender = in.nextLine();
                age = Integer.parseInt(in.nextLine());
                number = in.nextLine();
                score = Integer.parseInt(in.nextLine());
                teacherFunctions.addStudents(new Student(name, gender, age, number, score, number, number));
            } else if(choice.equals("2")) {
                teacherFunctions.deleteStudent(students);
            } else if(choice.equals("3")) {
                for(Student s : students){
                    System.out.println(s.getName() + " " + s.getNumber() + " " + s.getAge() + " " + s.getScore());
                }
                System.out.println("输入任意字符返回");
                exits = in.nextLine();
            } else if(choice.equals("4")) {

            } else if(choice.equals("5")) {

            } else if(choice.equals("6")) {

            }else if(choice.equals("7")) {
                return;
            } else{
                System.out.println("输入错误，输入0重新选择或者其他字符退出");
                choice = in.nextLine();
                if(!choice.equals("0")) {
                    return;
                }
            }
        }
    }

    public static Person login(int port){
        int flag = 0;
        String account;
        String password;
        if(port == 1) {
            System.out.print("输入你的账号(学生初始账号密码均为学号)：");
            account = in.nextLine();
            System.out.println();
            System.out.print("输入你的密码:");
            password = in.nextLine();
            for(Student student : students) {
                if(student.getAccount().equals(account)) {
                    if(student.getPassword().equals(password)) {
                        flag = 1;
                        return student;
                    }
                }
            }
        }else{
            System.out.print("输入你的账号：");
            account = in.nextLine();
            System.out.println();
            System.out.print("输入你的密码:");
            password = in.nextLine();
            /*for(Teacher teacher : teachers) {
                if(teacher.getAccount().equals(account)) {
                    if(teacher.getPassword().equals(password)) {
                        flag = 1;
                        break;
                    }
                }
            }*/
            if(account.equals("1234567") && password.equals("1234567")) {
                flag = 1;
                return new Teacher("唐僧", "男", 2600, "1234567", "1234567");
            }
        }
        if(flag == 0) {
            try{
                throw(new LoginException("账号登陆失败"));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return null;
    }

    public static void getStudentInformation() throws IOException {
        File file = new File("C:\\Users\\30371\\IdeaProjects\\StudentManagement\\src\\studentInformation.txt");
        if(file.length() == 0){
            return;
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            while(true){
                try {
                    Student student = (Student) in.readObject();
                    students.add(student);
                } catch (EOFException e) {
                    break;
                } catch(InvalidClassException e){
                    System.out.println(e);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    public static void getTeacherInformation() throws IOException {
        File file = new File("C:\\Users\\30371\\IdeaProjects\\StudentManagement\\src\\teacherInformation.txt");
        if(file.length() == 0){
            return;
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            while(true){
                try{
                    Teacher teacher = (Teacher) in.readObject();
                    teachers.add(teacher);
                }catch(EOFException e){
                    System.out.println();
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
