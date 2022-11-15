package br.com.harbitech.school.servlet.course;

import br.com.harbitech.school.model.course.Course;
import br.com.harbitech.school.repository.dao.CourseDAO;
import br.com.harbitech.school.repository.factory.ConnectionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/atualiza-curso")
public class UpdateCourseForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String paramId = request.getParameter("id");
        Long id = Long.valueOf(paramId);

        try (Connection connection = new ConnectionFactory().retrieveConnection()) {
            CourseDAO courseDAO = new CourseDAO(connection);
            Course course = courseDAO.findById(id);

            request.setAttribute("course", course);

            RequestDispatcher rd = request.getRequestDispatcher("/formUpdateCourse.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/Harbitech_war/cursos");
    }
}
