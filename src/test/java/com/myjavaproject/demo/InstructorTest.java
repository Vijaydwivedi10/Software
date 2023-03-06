package com.myjavaproject.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.*;

import org.junit.jupiter.api.Test;

public class InstructorTest {
    @Test
    void testCase1() {
        // Set up test data
        String email = "john@example.com";
        int course_id = 104;
        int semester = 2;
        int newGrade = 9;

        // Call the function under test
        boolean result = Instructor.case1(email, course_id, semester, newGrade);

        // Assert the expected result
        assertTrue(result);
    }

    @Test
    void testCase2() {

    }

    @Test
    void testCase3() {
        String instructorEmail = "jane@example.com";
        assertTrue(Instructor.case3(instructorEmail));
    }

    @Test
    void testCase4() {

        boolean result = Instructor.case4("new address", "345678", "jane@example.com");

        // Check that the update was successful
        assertTrue(result);

        // Check that the updated data is correct
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres",
                "1234")) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            pstmt.setString(1, "jane@example.com");
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("new address", rs.getString("address"));
            assertEquals("345678", rs.getString("phone"));
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

    }

    @Test
    void testCase5() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Statement stmt = null;
        // Set up test data
        String newPassword = "np";

        // Call the method being tested
        boolean result = Instructor.case5(newPassword);

        // Check the result
        assertTrue(result, "Failed to update password");

        // Verify that the password was updated in the database
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.createStatement();

            Currentuser c = new Currentuser();
            String email = c.getEmail();

            String selectQuery = "SELECT password FROM users WHERE email = ?";
            pstmt = conn.prepareStatement(selectQuery);
            pstmt.setString(1, email);

            rs = pstmt.executeQuery();
            rs.next();
            String actualPassword = rs.getString("password");

            assertEquals(newPassword, actualPassword, "Password was not updated in the database");

            // Clean up
            pstmt.close();
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
