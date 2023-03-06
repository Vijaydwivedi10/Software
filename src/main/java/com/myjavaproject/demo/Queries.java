package com.myjavaproject.demo;

import java.sql.*;
import java.util.*;

class Queries {

    static boolean Login_val(String username, String password, String role) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();
            // --------------------

            PreparedStatement pstmt = conn.prepareStatement("select role from users where email=? and password=?;");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            String role2 = "";
            if (rs.next()) {
                role2 = rs.getString("role");
            }
            if (role2.equals(role)) {
                return true;
            } else {
                return false;
            }

            // -------------------------

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;

    }

    static void insert_Course() {
        // Connection conn = null;
        // PreparedStatement pstmt = null;
        // try {
        // conn = ConnectionUtil.getConnection();
        // pstmt = conn.prepareStatement(
        // "INSERT INTO course_catalog (course_id, course_name, credit_structure) VALUES
        // (?, ?, ?)");

        // Scanner input = new Scanner(System.in);

        // System.out.print("Enter the course ID: ");
        // int course_id = input.nextInt();
        // input.nextLine(); // Consume the new line character

        // System.out.print("Enter the course name: ");
        // String course_name = input.nextLine();

        // System.out.print("Enter the credit structure: ");
        // String credit_structure = input.nextLine();

        // pstmt.setInt(1, course_id);
        // pstmt.setString(2, course_name);
        // pstmt.setString(3, credit_structure);

        // pstmt.executeUpdate();

        // System.out.println("Data inserted successfully!");

        // input.close();
        // pstmt.close();
        // conn.close();
        // } catch (SQLException se) {
        // se.printStackTrace();
        // } catch (Exception e) {
        // e.printStackTrace();
        // } finally {
        // try {
        // if (pstmt != null)
        // pstmt.close();
        // } catch (SQLException se2) {
        // }
        // try {
        // if (conn != null)
        // conn.close();
        // } catch (SQLException se) {
        // se.printStackTrace();
        // }
        // }
    }

    static void show_Courses() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM course_catalog");

            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String credit_structure = rs.getString("credit_structure");

                System.out.println("\nCourse ID: " + course_id);
                System.out.println("Course Name: " + course_name);
                System.out.println("Credit Structure: " + credit_structure);
                System.out.println();
            }

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void Insert_Prerequisite() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            pstmt = conn
                    .prepareStatement("INSERT INTO prerequisites (course_id, prerequisite_course_id) VALUES (?, ?)");

            Scanner input = new Scanner(System.in);

            System.out.print("Enter The course_id: ");
            int course_id = input.nextInt();
            input.nextLine(); // Consume the new line character

            System.out.print("Enter The prerequisite_course_id: ");
            int prerequisite_course_id = input.nextInt();
            input.nextLine(); // Consume the new line character

            pstmt.setInt(1, course_id);
            pstmt.setInt(2, prerequisite_course_id);

            pstmt.executeUpdate();

            App.main(null);

            input.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void Show_Prerequisites() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("");

            try {
                rs = stmt.executeQuery("SELECT * FROM prerequisites");
            } catch (Exception e) {

            }

            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                int prerequisite_course_id = rs.getInt("prerequisite_course_id");

                System.out.println("\ncourse_id: " + course_id);
                System.out.println("prerequisite_course_id: " + prerequisite_course_id);
                System.out.println("\n");
            }

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void Insert_course_curriculum() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            pstmt = conn.prepareStatement(
                    "INSERT INTO course_curriculum (batch, semester, department, course_id, course_type) VALUES (?, ?, ?, ?, ?)");

            Scanner input = new Scanner(System.in);

            System.out.print("Enter The batch: ");
            int batch = input.nextInt();

            System.out.print("Enter The semester: ");
            int semester = input.nextInt();
            input.nextLine(); // Consume the new line character

            System.out.print("Enter The department: ");
            String department = input.nextLine();

            System.out.print("Enter The course_id: ");
            int course_id = input.nextInt();
            input.nextLine(); // Consume the new line character

            System.out.print("Enter The course_type: ");
            String course_type = input.nextLine();

            pstmt.setInt(1, batch);
            pstmt.setInt(2, semester);
            pstmt.setString(3, department);
            pstmt.setInt(4, course_id);
            pstmt.setString(5, course_type);

            pstmt.executeUpdate();

            App.main(null);

            input.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void Show_course_curriculum() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("");

            try {
                rs = stmt.executeQuery("SELECT * FROM course_curriculum");
            } catch (Exception e) {

            }

            while (rs.next()) {
                int batch = rs.getInt("batch");
                int semester = rs.getInt("semester");
                String department = rs.getString("department");
                int course_id = rs.getInt("course_id");
                String course_type = rs.getString("course_type");

                System.out.println("\nbatch: " + batch);
                System.out.println("semester: " + semester);
                System.out.println("department: " + department);
                System.out.println("course_id: " + course_id);
                System.out.println("course_type: " + course_type);
                System.out.println("\n");
            }

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void Insert_users() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            pstmt = conn.prepareStatement(
                    "INSERT INTO users (email, password, name, address, phone, role, token) VALUES (?, ?, ?, ?, ?, ?, ?)");

            Scanner input = new Scanner(System.in);

            System.out.print("Enter The email: ");
            String email = input.nextLine();

            System.out.print("Enter The password: ");
            String password = input.nextLine();

            System.out.print("Enter The name: ");
            String name = input.nextLine();

            System.out.print("Enter The address: ");
            String address = input.nextLine();

            System.out.print("Enter The phone: ");
            String phone = input.nextLine();

            System.out.print("Enter The role: ");
            String role = input.nextLine();

            System.out.print("Enter The token: ");
            String token = input.nextLine();

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, address);
            pstmt.setString(5, phone);
            pstmt.setString(6, role);
            pstmt.setString(7, token);

            pstmt.executeUpdate();

            App.main(null);

            input.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void Show_users() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("");

            try {
                rs = stmt.executeQuery("SELECT * FROM users");
            } catch (Exception e) {

            }

            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String role = rs.getString("role");
                String token = rs.getString("token");

                System.out.println("\nemail: " + email);
                System.out.println("password: " + password);
                System.out.println("name: " + name);
                System.out.println("address: " + address);
                System.out.println("phone: " + phone);
                System.out.println("role: " + role);
                System.out.println("token: " + token);
                System.out.println("\n");
            }

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void insert_instructor() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO instructors (department, email, joining_date) VALUES (?, ?, ?)");

            Scanner input = new Scanner(System.in);

            System.out.print("Enter the department: ");
            String department = input.nextLine();

            System.out.print("Enter the email: ");
            String email = input.nextLine();

            System.out.print("Enter the joining date (yyyy-mm-dd): ");
            String joining_date = input.nextLine();

            pstmt.setString(1, department);
            pstmt.setString(2, email);
            pstmt.setDate(3, java.sql.Date.valueOf(joining_date));

            pstmt.executeUpdate();

            System.out.println("Instructor added successfully!");

            input.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void show_instructors() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("");

            try {
                rs = stmt.executeQuery("SELECT * FROM instructors");
            } catch (Exception e) {

            }

            while (rs.next()) {
                int instructor_id = rs.getInt("instructor_id");
                String department = rs.getString("department");
                String email = rs.getString("email");
                String joining_date = rs.getString("joining_date");

                System.out.println("\nInstructor ID: " + instructor_id);
                System.out.println("Department: " + department);
                System.out.println("Email: " + email);
                System.out.println("Joining Date: " + joining_date);
                System.out.println("\n");
            }

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void insert_students() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            pstmt = conn.prepareStatement(
                    "INSERT INTO students (email, entry_number, batch, department, curr_sem, cgpa) VALUES (?, ?, ?, ?, ?, ?)");

            Scanner input = new Scanner(System.in);

            System.out.print("Enter the student's email: ");
            String email = input.nextLine();

            System.out.print("Enter the student's entry number: ");
            String entry_number = input.nextLine();

            System.out.print("Enter the student's batch: ");
            int batch = input.nextInt();
            input.nextLine(); // Consume the new line character

            System.out.print("Enter the student's department: ");
            String department = input.nextLine();

            System.out.print("Enter the student's current semester: ");
            int curr_sem = input.nextInt();
            input.nextLine(); // Consume the new line character

            System.out.print("Enter the student's CGPA: ");
            float cgpa = input.nextFloat();

            pstmt.setString(1, email);
            pstmt.setString(2, entry_number);
            pstmt.setInt(3, batch);
            pstmt.setString(4, department);
            pstmt.setInt(5, curr_sem);
            pstmt.setFloat(6, cgpa);

            pstmt.executeUpdate();

            App.main(null);

            input.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void show_students() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("");

            try {
                rs = stmt.executeQuery("SELECT * FROM students");
            } catch (Exception e) {

            }

            while (rs.next()) {
                String email = rs.getString("email");
                String entry_number = rs.getString("entry_number");
                int batch = rs.getInt("batch");
                String department = rs.getString("department");
                int curr_sem = rs.getInt("curr_sem");
                float cgpa = rs.getFloat("cgpa");

                System.out.println("\nEmail: " + email);
                System.out.println("Entry Number: " + entry_number);
                System.out.println("Batch: " + batch);
                System.out.println("Department: " + department);
                System.out.println("Current Semester: " + curr_sem);
                System.out.println("CGPA: " + cgpa);
                System.out.println("\n");
            }

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void Insert_Course_Offerings() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            pstmt = conn.prepareStatement(
                    "INSERT INTO course_offerings (course_id, instructor_id, cgpa_constraint, min_credits) VALUES (?, ?, ?, ?)");

            Scanner input = new Scanner(System.in);

            System.out.print("Enter The course_id: ");
            int course_id = input.nextInt();
            input.nextLine(); // Consume the new line character

            System.out.print("Enter The instructor_id: ");
            int instructor_id = input.nextInt();
            input.nextLine(); // Consume the new line character

            System.out.print("Enter The cgpa_constraint: ");
            float cgpa_constraint = input.nextFloat();
            input.nextLine(); // Consume the new line character

            System.out.print("Enter The min_credits: ");
            int min_credits = input.nextInt();
            input.nextLine(); // Consume the new line character

            pstmt.setInt(1, course_id);
            pstmt.setInt(2, instructor_id);
            pstmt.setFloat(3, cgpa_constraint);
            pstmt.setInt(4, min_credits);

            pstmt.executeUpdate();

            App.main(null);

            input.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void Show_Course_Offerings() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("");

            try {
                rs = stmt.executeQuery("SELECT * FROM course_offerings");
            } catch (Exception e) {

            }

            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                int instructor_id = rs.getInt("instructor_id");
                float cgpa_constraint = rs.getFloat("cgpa_constraint");
                int min_credits = rs.getInt("min_credits");

                System.out.println("\ncourse_id: " + course_id);
                System.out.println("instructor_id: " + instructor_id);
                System.out.println("cgpa_constraint: " + cgpa_constraint);
                System.out.println("min_credits: " + min_credits);
                System.out.println("\n");
            }

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void Insert_Grades() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionUtil.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO grades (email, course_id, semester, grade) VALUES (?, ?, ?, ?)");

            Scanner input = new Scanner(System.in);

            System.out.print("Enter the student's email: ");
            String email = input.nextLine();

            System.out.print("Enter the course ID: ");
            int course_id = input.nextInt();
            input.nextLine(); // Consume the new line character

            System.out.print("Enter the semester: ");
            int semester = input.nextInt();
            input.nextLine(); // Consume the new line character

            System.out.print("Enter the grade: ");
            int grade = input.nextInt();
            input.nextLine(); // Consume the new line character

            pstmt.setString(1, email);
            pstmt.setInt(2, course_id);
            pstmt.setInt(3, semester);
            pstmt.setInt(4, grade);

            pstmt.executeUpdate();

            App.main(null);

            input.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    static void Show_Grades(String email) {

        try {
            // Establishing a database connection
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres",
                    "1234");

            // Preparing the select statement
            String selectQuery = "SELECT course_id, semester, grade FROM grades WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
            pstmt.setString(1, email);

            // Executing the select statement
            ResultSet rs = pstmt.executeQuery();

            int x = 0;

            // Iterating over the result set and printing the grades
            while (rs.next()) {
                x = 1;
                int courseId = rs.getInt("course_id");
                int semester = rs.getInt("semester");
                int grade = rs.getInt("grade");

                System.out.println("Course ID: " + courseId + " | Semester: " + semester + " | Grade: " + grade);
            }

            if (x == 0)
                System.out.println("Student with " + email + " does not exist");

            // Closing the result set, statement, and database connection
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    static void delete_course(int course_id) {

        try {
            // Establishing a database connection
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres",
                    "1234");

            // Preparing the delete statement
            String deleteQuery = "DELETE FROM course_catalog WHERE course_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, course_id);

            // Executing the delete statement
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted == 0)
                System.out.println("Course with course_id " + course_id + " does not exist");
            else
                System.out.println("Course with course_id " + course_id + " deleted successfully");

            // Closing the statement and database connection
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    public static void UpdateUserDetails(String address, String phone, String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Scanner input = new Scanner(System.in);

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres",
                    "1234");
            // Update the address and phone of the user with email "example@example.com"

            String updateQuery = "UPDATE users SET address = ?, phone = ? WHERE email = ?";
            pstmt = conn.prepareStatement(updateQuery);

            // Set the new address and phone values
            pstmt.setString(1, address);
            pstmt.setString(2, phone);

            // Set the email of the user to update
            pstmt.setString(3, email);

            // Execute the update statement
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User details updated successfully");
            } else {
                System.out.println("No user found with the specified email");
            }
            App.main(null);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
            }
        }
        input.close();
    }
}
