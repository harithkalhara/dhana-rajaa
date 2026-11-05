
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private int age;
    private String course;
    private double marks;

    public Student(int id, String name, int age, String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getGrade() {
        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public void displayDetails() {
        System.out.println("--------------------------------");
        System.out.println("Student ID   : " + id);
        System.out.println("Student Name : " + name);
        System.out.println("Age          : " + age);
        System.out.println("Course       : " + course);
        System.out.println("Marks        : " + marks);
        System.out.println("Grade        : " + getGrade());
        System.out.println("--------------------------------");
    }
}

class University {

    private ArrayList<Student> students = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public void addStudent() {
        try {
            System.out.print("Enter Student ID: ");
            int id = input.nextInt();
            input.nextLine();

            if (searchById(id) != null) {
                System.out.println("Student ID already exists!");
                return;
            }

            System.out.print("Enter Student Name: ");
            String name = input.nextLine();

            System.out.print("Enter Age: ");
            int age = input.nextInt();
            input.nextLine();

            System.out.print("Enter Course: ");
            String course = input.nextLine();

            System.out.print("Enter Marks: ");
            double marks = input.nextDouble();

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks!");
                return;
            }

            Student student = new Student(id, name, age, course, marks);

            students.add(student);

            System.out.println("Student added successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
            input.nextLine();
        }
    }

    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students) {
            student.displayDetails();
        }
    }

    public Student searchById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public void searchStudent() {

        System.out.print("Enter Student ID to search: ");
        int id = input.nextInt();

        Student student = searchById(id);

        if (student != null) {
            System.out.println("Student Found!");
            student.displayDetails();
        } else {
            System.out.println("Student not found!");
        }
    }

    public void deleteStudent() {

        System.out.print("Enter Student ID to delete: ");
        int id = input.nextInt();

        Student student = searchById(id);

        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    public void updateMarks() {

        System.out.print("Enter Student ID: ");
        int id = input.nextInt();

        Student student = searchById(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter new marks: ");
        double marks = input.nextDouble();

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks!");
            return;
        }

        student.setMarks(marks);

        System.out.println("Marks updated successfully!");
    }

    public void sortStudents() {

        Collections.sort(students,
                Comparator.comparingDouble(Student::getMarks).reversed());

        System.out.println("Students sorted by marks!");

        displayAllStudents();
    }

    public void calculateAverage() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        double total = 0;

        for (Student student : students) {
            total += student.getMarks();
        }

        double average = total / students.size();

        System.out.println("Average Marks: " + average);
    }

    public void findTopStudent() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        Student topStudent = students.get(0);

        for (Student student : students) {

            if (student.getMarks() > topStudent.getMarks()) {
                topStudent = student;
            }
        }

        System.out.println("Top Performing Student:");
        topStudent.displayDetails();
    }

    public void countPassedStudents() {

        int passed = 0;
        int failed = 0;

        for (Student student : students) {

            if (student.getMarks() >= 50) {
                passed++;
            } else {
                failed++;
            }
        }

        System.out.println("Passed Students: " + passed);
        System.out.println("Failed Students: " + failed);
    }

    public void displayMenu() {

        int choice = -1;

        do {

            System.out.println("\n================================");
            System.out.println(" UNIVERSITY MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student Marks");
            System.out.println("6. Sort Students");
            System.out.println("7. Calculate Average Marks");
            System.out.println("8. Find Top Student");
            System.out.println("9. Count Passed / Failed");
            System.out.println("0. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");

            try {
                choice = input.nextInt();

                switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        displayAllStudents();
                        break;

                    case 3:
                        searchStudent();
                        break;

                    case 4:
                        deleteStudent();
                        break;

                    case 5:
                        updateMarks();
                        break;

                    case 6:
                        sortStudents();
                        break;

                    case 7:
                        calculateAverage();
                        break;

                    case 8:
                        findTopStudent();
                        break;

                    case 9:
                        countPassedStudents();
                        break;

                    case 0:
                        System.out.println("Exiting system...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
                input.nextLine();
            }

        } while (choice != 0);
    }
}

 class Main {

    public static void main(String[] args) {

        University university = new University();

        System.out.println("Welcome to the University System!");

        university.displayMenu();

        System.out.println("Thank you for using our system!");
    }
}
