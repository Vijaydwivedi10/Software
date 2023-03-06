package com.myjavaproject.demo;

import java.sql.*;
import java.util.*;

public class Instructor {
    static Scanner input = new Scanner(System.in);

    static Connection conn = null;
    static Statement stmt = null;

    static boolean case1(String email, int course_id, int semester, int newGrade) {
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();

            String updateQuery = "UPDATE grades SET grade = ? WHERE email = ? AND course_id = ? AND semester = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setInt(1, newGrade);
            pstmt.setString(2, email);
            pstmt.setInt(3, course_id);
            pstmt.setInt(4, semester);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated == 0) {
                System.out.println("No record found to update for email: " + email + ", course_id: " + course_id
                        + ", and semester: " + semester);
            } else {
                System.out.println("Grade updated successfully for email: " + email + ", course_id: " + course_id
                        + ", and semester: " + semester);
            }

            // Close the result set and statement
            pstmt.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQL error occurred: " + e.getMessage());
            return false;
        }

        return true;
    }

    static boolean case2() {

        return true;
    }

    static boolean case3(String instructorEmail) {

        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();
            // Prepare the SQL query to get all courses offered by the instructor
            String sql = "SELECT c.course_id, c.course_name FROM course_catalog c, course_offerings o, instructors i "
                    + "WHERE c.course_id = o.course_id AND o.instructor_id = i.instructor_id AND i.email = ?";

            // Create a PreparedStatement object and set the parameter
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, instructorEmail);

            // Execute the query and get the ResultSet object
            ResultSet rs = pstmt.executeQuery();

            // Loop through the result set and print the courses offered by the instructor
            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                System.out.println("Course ID: " + courseId + ", Course Name: " + courseName);
            }

            // Close the ResultSet and PreparedStatement objects
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    static boolean case5(String password) {

        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();

            Currentuser c = new Currentuser();
            String email = c.getEmail();

            String updateQuery = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, password);
            pstmt.setString(2, email);

            pstmt.executeUpdate();

            // Close the result set and statement

            pstmt.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("SQL error occurred: " + e.getMessage());
            return false;
        }

        return true;

    }

    static boolean case4(String address, String phone, String email) {

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres",
                    "1234");
            // Update the address and phone of the user with email "example@example.com"

            String updateQuery = "UPDATE users SET address = ?, phone = ? WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);

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

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
            }
        }

        return true;
    }

}
