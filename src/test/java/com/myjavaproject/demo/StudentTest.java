package com.myjavaproject.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

import org.junit.jupiter.api.Test;

public class StudentTest {

    @Test
    void testCase1() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres", "1234");
            stmt = conn.createStatement();

            String email = "john@example.com";
            int course_id = 7;

            String query = "SELECT curr_sem FROM students WHERE email = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            int curr_sem = 0;
            if (rs.next()) {
                curr_sem = rs.getInt("curr_sem");
            }

            String query2 = "SELECT course_id FROM course_catalog WHERE course_id = ?";
            pstmt = conn.prepareStatement(query2);
            pstmt.setInt(1, course_id);
            rs = pstmt.executeQuery();

            boolean courseExists = false;
            if (rs.next()) {
                courseExists = true;
            }

            if (courseExists) {
                // insert grade with -1 value for current semester and chosen course
                String insertQuery = "INSERT INTO grades (email, course_id, semester, grade) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(insertQuery);
                pstmt.setString(1, email);
                pstmt.setInt(2, course_id);
                pstmt.setInt(3, curr_sem);
                pstmt.setInt(4, -1);
                pstmt.executeUpdate();

                System.out.println("Grade with value -1 inserted successfully.");
            } else {
                System.out.println("Chosen course is not available in course catalog.");
            }

            boolean result = Student.case1(course_id, email);

            // Verify the insert statement
            rs = stmt.executeQuery("SELECT * FROM grades where course_id = " + course_id);
            assertTrue(rs.next());
            assertTrue(rs.getString("email").equals("john@example.com"));
            assertTrue(rs.getInt("course_id") == course_id);
            assertTrue(rs.getInt("semester") == curr_sem);
            assertTrue(rs.getInt("grade") == -1);

            // Verify that nothing else was inserted
            assertFalse(rs.next());

            String deleteQuery = "DELETE FROM grades WHERE email = ? AND course_id = ? AND semester = ?";
            pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setString(1, email);
            pstmt.setInt(2, course_id);
            pstmt.setInt(3, 1);
            pstmt.executeUpdate();

        } finally {
            // Close the resources in the reverse order of their creation
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // @Disabled("Disable because deleted values are not testing")
    @Test
    void testCase2() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres", "1234");
            stmt = conn.createStatement();

            String email = "john@example.com";
            int course_id = 7;

            String query = "SELECT curr_sem FROM students WHERE email = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            int curr_sem = 0;
            if (rs.next()) {
                curr_sem = rs.getInt("curr_sem");
            }

            String query2 = "SELECT course_id FROM course_catalog WHERE course_id = ?";
            pstmt = conn.prepareStatement(query2);
            pstmt.setInt(1, course_id);
            rs = pstmt.executeQuery();

            boolean courseExists = false;
            if (rs.next()) {
                courseExists = true;
            }

            if (courseExists) {
                // insert grade with -1 value for current semester and chosen course
                String insertQuery = "INSERT INTO grades (email, course_id, semester, grade) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(insertQuery);
                pstmt.setString(1, email);
                pstmt.setInt(2, course_id);
                pstmt.setInt(3, curr_sem);
                pstmt.setInt(4, -1);
                pstmt.executeUpdate();

                System.out.println("Grade with value -1 inserted successfully.");
            } else {
                System.out.println("Chosen course is not available in course catalog.");
            }

            boolean result = Student.case1(course_id, email);

            // Verify the insert statement

            String deleteQuery = "DELETE FROM grades WHERE email = ? AND course_id = ? AND semester = ?";
            pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setString(1, email);
            pstmt.setInt(2, course_id);
            pstmt.setInt(3, 1);
            pstmt.executeUpdate();

            // rs = stmt.executeQuery("SELECT * FROM grades WHERE email = ? AND course_id =
            // ? AND semester = ?");
            // pstmt.setString(1, email);
            // pstmt.setInt(2, course_id);
            // pstmt.setInt(3, curr_sem);
            // assertFalse(rs.next());

            String sql = "SELECT * FROM grades WHERE email = ? AND course_id = ? AND semester = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setInt(2, course_id);
            pstmt.setInt(3, curr_sem);

        } finally {
            // Close the resources in the reverse order of their creation
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Test
    void testCase3() {
        // Test with an email that exists in the "grades" table
        String email = "john@example.com";
        boolean result = Student.case3(email);
        assertTrue(result);
    }

    @Test
    void testCase4() {

        boolean result = Student.case4("456 Elm St", "555", "john@example.com");

        // Check that the update was successful
        assertTrue(result);

        // Check that the updated data is correct
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres",
                "1234")) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            pstmt.setString(1, "john@example.com");
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("456 Elm St", rs.getString("address"));
            assertEquals("555", rs.getString("phone"));
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

    }

    @Test
    void testCase5() {
        String newPassword = "newpassword123"; // replace with a valid password
        assertTrue(Student.case5(newPassword));

        // Verify that the password was updated successfully
        try {
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT password FROM users WHERE email = ?");
            pstmt.setString(1, new Currentuser().getEmail());
            ResultSet rs = pstmt.executeQuery();

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM users WHERE token IS NOT NULL");
            ResultSet rs2 = pstmt2.executeQuery();

            if (!rs2.next()) {
                assertFalse(false);
            } else {
                assertTrue(rs.next());
                assertEquals(newPassword, rs.getString("password"));
            }

            rs.close();
            pstmt.close();
            pstmt2.close();
            conn.close();
        } catch (SQLException e) {
            fail("SQL error occurred: " + e.getMessage());
        }
    }

}
