import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherFunctions {


    private List<Teacher> teachers;
    //private List<Student> students;
    public TeacherFunctions() {
        teachers = new ArrayList<Teacher>();
    }
    Scanner sc = new Scanner(System.in);
    public void addStudents(Student student) throws FileNotFoundException {
        File file = new File("C:\\Users\\30371\\IdeaProjects\\StudentManagement\\src\\studentInformation.txt");
        boolean flag = file.exists() && file.length() > 0;
        try(ObjectOutputStream out = flag ? new AppendObjectOutPutStream(new FileOutputStream(file, true)): new ObjectOutputStream(new FileOutputStream(file, true))){
            out.writeObject(student);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("学生信息添加成功,输入任意字符返回\n");
        char temp = sc.nextLine().charAt(0);
    }

     public void deleteStudent(List<Student> students) throws IOException {
        System.out.println("输入你要删除学生的姓名：");
        String name = sc.nextLine();
        for (int i = 0; i < students.size(); i++) {
            if(name.equals(students.get(i).getName())) {
                students.remove(i);
                File file = new File("C:\\Users\\30371\\IdeaProjects\\StudentManagement\\src\\studentInformation.txt");
                new FileOutputStream(file).close();
                try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, true));) {
                    for (Student student : students) {
                        out.writeObject(student);
                    }
                }catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("删除成功（输入任意字符返回）");
                name = sc.nextLine();
                return;
            }
        }
        System.out.println("没有找到该学生信息（输入任意字符返回）");
        name = sc.nextLine();
     }
     public void reviseStudent(List<Student> students) throws IOException {
         System.out.print("输入你要修改学生的姓名：");
         String name = sc.nextLine();
         System.out.println();
         System.out.println("输入你要修改的分数");
         int score = sc.nextInt();
         for (int i = 0; i < students.size(); i++) {
             if(name.equals(students.get(i).getName())) {
                 students.get(i).setScore(score);
                 File file = new File("C:\\Users\\30371\\IdeaProjects\\StudentManagement\\src\\studentInformation.txt");
                 new FileOutputStream(file).close();
                 try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, true));) {
                     for (Student student : students) {
                         out.writeObject(student);
                     }
                 }catch (Exception e) {
                     System.out.println(e);
                 }
                 System.out.println("修改成功（输入任意字符返回）");
                 name = sc.nextLine();
                 return;
             }
         }
         System.out.println("没有找到该学生信息（输入任意字符返回）");
         name = sc.nextLine();
     }

     public void viewApperance(List<Student> students) throws IOException, InterruptedException {
        String exits;
         List<Thread> threads = new ArrayList<>();
         for(int i = 0; i < students.size(); i++){
             threads.add(new Thread(students.get(i)));
         }
         for(Thread t : threads){
             t.start();
         }
         System.out.println("(输入任意字符返回）");
         exits = sc.nextLine();
     }
}
