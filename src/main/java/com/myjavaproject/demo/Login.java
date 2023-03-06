package com.myjavaproject.demo;

import java.sql.SQLException;

public class Login {

    static void login_student() throws SQLException {

    }

    static void login_Faculty() {

    }

    static void login_Academics() {

    }

    public boolean login_val(String email, String password, String role) {
        // This is a mock implementation that always returns true
        return true;
    }

    // static void Signup() {
    // Scanner input = new Scanner(System.in);

    // System.out.println(" --------- SIGNUP --------- ");
    // System.out.print("Enter Username : ");
    // String username = input.nextLine();
    // System.out.print("Enter Password : ");
    // String password = input.nextLine();
    // System.out.print("Enter your Role : ");
    // String role = input.nextLine();
    // System.out.print("Enter your name : ");
    // String name = input.nextLine();
    // System.out.print("Enter your email : ");
    // String email = input.nextLine();
    // System.out.print("Enter your batch : ");
    // int batch = input.nextInt();
    // input.nextLine(); // add this line to consume the newline character
    // System.out.print("Enter your joining date : ");
    // String j_date = input.nextLine();

    // Queries.Insert_users(username, password, role, name, email, batch, j_date);
    // input.close();

    // }

}
