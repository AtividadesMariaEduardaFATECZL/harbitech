package br.com.harbitech.school;

import br.com.harbitech.school.model.course.Course;
import br.com.harbitech.school.model.course.CourseVisibility;
import br.com.harbitech.school.repository.dao.CourseDAO;
import br.com.harbitech.school.repository.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Teste {
    public static void main(String[] args) {
        try (Connection connection = new ConnectionFactory().retrieveConnection()) {

            CourseDAO courseDAO = new CourseDAO(connection);
            Course byId = courseDAO.findById(28l);

            System.out.println(byId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
