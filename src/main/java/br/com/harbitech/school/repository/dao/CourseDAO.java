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

    public void update(Course course) throws SQLException {
        connection.setAutoCommit(false);
        String sql = """
                UPDATE course SET name = ?, code_url = ?, completion_time_in_hours = ?, visibility = ?,
                    target_audience = ?, instructor = ?, description = ?,developed_skills = ? WHERE id = ?;
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
                pstm.setLong(9, course.getId());
                pstm.executeUpdate();
                connection.commit();
        }
    }

    public void save(Course course) throws SQLException {
        String sql = """
                DELIMITER //
                CREATE PROCEDURE `includes_ongoing_course`(name1 VARCHAR(70), code_url2 VARCHAR(70), completion_time_in_hours3 INT,
                                                           visibility4 ENUM ('PUBLIC','PRIVATE'), target_audience5 VARCHAR(250),
                                                           instructor6 VARCHAR(70), description7 TEXT,
                                                           developed_skills8 TEXT, subcategory_id9 BIGINT)
                BEGIN
                    INSERT INTO course(name, code_url,completion_time_in_hours,visibility,target_audience,
                                        instructor, description, developed_skills, subcategory_id)
                    VALUES(name1, code_url2,completion_time_in_hours3,visibility4,target_audience5,
                            instructor6, description7, developed_skills8, subcategory_id9);
                END//
                """;

        try (CallableStatement statement = connection.prepareCall("{call includes_ongoing_course(?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            statement.setString(1, course.getName());
            statement.setString(2, course.getCodeUrl());
            statement.setInt(3, course.getCompletionTimeInHours());
            statement.setString(4, String.valueOf(course.getVisibility()));
            statement.setString(5, course.getTargetAudience());
            statement.setString(6, course.getInstructor());
            statement.setString(7, course.getDescription());
            statement.setString(8, course.getDevelopedSkills());
            statement.setLong(9, course.getSubCategory().getId());
            statement.executeQuery();
        }
    }

    public void delete(String urlCode) throws SQLException {
        String sql = "DELETE FROM course WHERE code_url = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, urlCode);
            pstm.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    public void deleteBy(Long id) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "DELETE FROM course WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, id);
            pstm.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
    }


    public void updatePublicVisibility() throws SQLException {
        String sql = "UPDATE course SET visibility = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.execute();
            stm.setString(1, CourseVisibility.PUBLIC.name());
            Integer updateLines = stm.getUpdateCount();
            System.out.println("Modified Lines: " + updateLines);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public List<CourseDto> findAll() throws SQLException {
        connection.setAutoCommit(false);
        List<CourseDto> courses = new ArrayList<>();
        String sql = """
                SELECT id, name AS name_course, code_url FROM course
                """;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            connection.commit();
            turnResultSetInCourse(courses, pstm);
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
        return courses;
    }

    public Course findById(Long id) throws SQLException {
        Course course = new Course();
        connection.setAutoCommit(false);
        String sql = """
                SELECT c.id, c.name, c.code_url, c.completion_time_in_hours, c.visibility, c.instructor, c.target_audience,
                 c.description, c.developed_skills
                FROM course c WHERE id = ?
                """;
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.execute();
            try (ResultSet rst = stm.getResultSet()) {
                while (rst.next()) {
                    course = new Course(rst.getLong("c.id"), rst.getString("c.name"), rst.getString("c.code_url"),
                            rst.getInt("c.completion_time_in_hours"),
                            rst.getString("c.target_audience"), rst.getString("c.instructor"),
                            rst.getString("c.description"), rst.getString("c.developed_skills"),
                            CourseVisibility.valueOf(rst.getString("c.visibility")));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
        return course;
    }

    public List<CourseReportDto> searchAllWithPublicVisibilitySubcategoryAndCategory() throws SQLException {
        connection.setAutoCommit(false);
        List<CourseReportDto> courses = new ArrayList<>();
        String sql = """
                SELECT
                 course.id AS id_course,
                 course.name AS name_course,
                 course.completion_time_in_hours,
                 subcategory.id AS id_subcategory,
                 subcategory.name AS name_subcategory
                FROM course course
                JOIN subcategory subcategory ON course.subcategory_id = subcategory.id
                JOIN category category ON subcategory.category_id = category.id
                WHERE course.visibility = ?
                """;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, CourseVisibility.PUBLIC.name());
            pstm.execute();
            connection.commit();
            turnResultSetInReportCourse(courses, pstm);
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
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
                CourseDto course = new CourseDto(rst.getLong("id"),
                        rst.getString("name_course"), rst.getString("code_url"));
                courses.add(course);
            }
        }
    }

    private CourseDto turnResultSetInCourse(PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            CourseDto course = new CourseDto(rst.getLong("id"),
                    rst.getString("name_course"), rst.getString("code_url"));
            return course;
        }
    }
}