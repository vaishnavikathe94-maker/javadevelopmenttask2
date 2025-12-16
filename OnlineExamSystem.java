import java.util.*;

class User {
    String username;
    String password;
    String name;

    User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}

public class OnlineExamSystem {
    static Scanner sc = new Scanner(System.in);
    static User user = new User("user123", "pass123", "Vaishnavi");
    static boolean sessionActive = false;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== ONLINE EXAMINATION SYSTEM ===");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                login();
            } else {
                System.out.println("Thank you! Exiting...");
                break;
            }
        }
    }

    // LOGIN FUNCTION
    static void login() {
        System.out.print("\nEnter Username: ");
        String u = sc.next();
        System.out.print("Enter Password: ");
        String p = sc.next();

        if (u.equals(user.username) && p.equals(user.password)) {
            System.out.println("\nLogin Successful!");
            sessionActive = true;
            dashboard();
        } else {
            System.out.println("Invalid Credentials! Try again.");
        }
    }

    // DASHBOARD MENU
    static void dashboard() {
        while (sessionActive) {
            System.out.println("\n--- DASHBOARD ---");
            System.out.println("1. Update Profile");
            System.out.println("2. Update Password");
            System.out.println("3. Start MCQ Test");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1: updateProfile(); break;
                case 2: updatePassword(); break;
                case 3: startExam(); break;
                case 4: logout(); break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // UPDATE PROFILE
    static void updateProfile() {
        System.out.print("\nEnter new Name: ");
        user.name = sc.next();
        System.out.println("Profile updated successfully!");
    }

    // UPDATE PASSWORD
    static void updatePassword() {
        System.out.print("\nEnter old password: ");
        String old = sc.next();
        if (!old.equals(user.password)) {
            System.out.println("Incorrect password!");
            return;
        }
        System.out.print("Enter new password: ");
        user.password = sc.next();
        System.out.println("Password changed successfully!");
    }

    // MCQ EXAM WITH TIMER
    static void startExam() {
        System.out.println("\nExam Started! You have 20 seconds.");

        int score = 0;
        long start = System.currentTimeMillis();

        // QUESTIONS
        String[] q = {
            "1. Java is ___ language?\nA. Programming\nB. Markup\nC. Scripting",
            "2. OOP stands for?\nA. Object Oriented Programming\nB. Original Object Program\nC. Object Oriented Process",
            "3. Java runs on?\nA. Compiler\nB. JVM\nC. Assembler"
        };

        char[] ans = {'A', 'A', 'B'};

        for (int i = 0; i < q.length; i++) {
            long now = System.currentTimeMillis();
            if ((now - start) / 1000 >= 20) {
                System.out.println("\n⏳ Time Up! Auto-submitting...");
                break;
            }

            System.out.println("\n" + q[i]);
            System.out.print("Your Answer: ");
            char userAns = Character.toUpperCase(sc.next().charAt(0));

            if (userAns == ans[i]) score++;
        }

        System.out.println("\nYour Score: " + score + "/" + q.length);
    }

    // LOGOUT
    static void logout() {
        System.out.println("\nLogging out...");
        sessionActive = false;
    }
}