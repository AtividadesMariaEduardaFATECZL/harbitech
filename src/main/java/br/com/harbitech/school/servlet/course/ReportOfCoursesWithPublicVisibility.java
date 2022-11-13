package br.com.harbitech.school.servlet.course;

import br.com.harbitech.school.model.course.dto.CourseDto;
import br.com.harbitech.school.model.course.dto.CourseReportDto;
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
import java.util.List;

@WebServlet("/relatorio")
public class ReportOfCoursesWithPublicVisibility extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = new ConnectionFactory().retrieveConnection()) {
            CourseDAO courseDAO = new CourseDAO(connection);
            List<CourseReportDto> courses = courseDAO.searchAllWithPublicVisibilitySubcategoryAndCategory();

            request.setAttribute("courses", courses);

            RequestDispatcher rd = request.getRequestDispatcher("courseReport.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
