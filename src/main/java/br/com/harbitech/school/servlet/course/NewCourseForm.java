package br.com.harbitech.school.servlet.course;

import br.com.harbitech.school.repository.dao.CourseDAO;
import br.com.harbitech.school.repository.dao.SubcategoryDAO;
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

@WebServlet("/novo-curso")
public class NewCourseForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = new ConnectionFactory().retrieveConnection()) {
            SubcategoryDAO subcategoryDAO = new SubcategoryDAO(connection);

            List<String> subcategoriesCode = subcategoryDAO.findAllCodes();

            request.setAttribute("subcategoriesCodes", subcategoriesCode);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/formNewCourse.jsp");
        rd.forward(request, response);
    }
}
