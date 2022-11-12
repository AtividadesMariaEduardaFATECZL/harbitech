package br.com.harbitech.school.repository.dao;


import br.com.harbitech.school.model.course.Course;
import br.com.harbitech.school.model.course.dto.CourseDto;
import br.com.harbitech.school.model.course.dto.CourseReportDto;
import br.com.harbitech.school.model.course.CourseVisibility;
import br.com.harbitech.school.model.subcategory.dto.SubcategoryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Course course) throws SQLException {
        String sql = """
                INSERT INTO course (name, code_url,completion_time_in_hours,visibility,target_audience,
                instructor,description,developed_skills,subcategory_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)
                """;

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, course.getName());
            pstm.setString(2, course.getCodeUrl());
            pstm.setInt(3, course.getCompletionTimeInHours());
            pstm.setString(4, String.valueOf(course.getVisibility()));
            pstm.setString(5, course.getTargetAudience());
            pstm.setString(6, course.getInstructor());
            pstm.setString(7, course.getDescription());
            pstm.setString(8, course.getDevelopedSkills());
            pstm.setLong(9, course.getSubCategory().getId());
            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    course.setId(rst.getLong(1));
                }
            }
        }
    }

    public void delete(String urlCode) throws SQLException {
        String sql = "DELETE FROM course WHERE code_url = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, urlCode);
            pstm.execute();
        }
    }

    public void upgradeAllToPublicVisibility() throws SQLException {
        String sql = """
                UPDATE course SET visibility = ?
                """;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, CourseVisibility.PUBLIC.name());
            pstm.execute();
        }
    }

    public List<CourseDto> findAll() throws SQLException {
        List<CourseDto> courses = new ArrayList<>();
        String sql = """
                SELECT name AS name_course, code_url FROM course
                """;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            turnResultSetInCourse(courses, pstm);
        }
        return courses;
    }

    public List<CourseReportDto> searchAllWithPublicVisibility() throws SQLException {
        List<CourseReportDto> courses = new ArrayList<>();
        String sql = """
                SELECT
                 course.id AS id_course,
                 course.name AS name_course,
                 course.completion_time_in_hours,
                 subcategory.id AS id_subcategory,
                 subcategory.name AS name_subcategory
                FROM course course
                JOIN subcategory subcategory ON course.subcategory_id = subcategory.id\s
                JOIN category category ON subcategory.category_id = category.id
                WHERE course.visibility = ?
                """;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, CourseVisibility.PUBLIC.name());
            pstm.execute();
            turnResultSetInReportCourse(courses, pstm);
        }
        return courses;
    }

    private void turnResultSetInReportCourse(List<CourseReportDto> courses, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                CourseReportDto course = new CourseReportDto(rst.getLong("id_course"),
                        rst.getString("name_course"), rst.getInt("completion_time_in_hours"),
                        new SubcategoryDto(rst.getLong("id_subcategory"),
                                rst.getString("name_subcategory")));
                courses.add(course);
            }
        }
    }

    private void turnResultSetInCourse(List<CourseDto> courses, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                CourseDto course = new CourseDto(rst.getString("name_course"), rst.getString("code_url"));
                courses.add(course);
            }
        }
    }
}