package br.com.harbitech.school.servlet.course;

import br.com.harbitech.school.model.category.Category;
import br.com.harbitech.school.model.course.Course;
import br.com.harbitech.school.model.course.CourseVisibility;
import br.com.harbitech.school.model.subcategory.SubCategory;
import br.com.harbitech.school.repository.dao.CourseDAO;
import br.com.harbitech.school.repository.dao.SubcategoryDAO;
import br.com.harbitech.school.repository.factory.ConnectionFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/cria-curso")
public class NewCourse extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String code = request.getParameter("codeUrl");
        String completionTimeInHours = request.getParameter("completionTimeInHours");
        String instructor = request.getParameter("instructor");
        String targetAudience = request.getParameter("targetAudience");
        String visibility = request.getParameter("visibility");
        String description = request.getParameter("description");
        String developedskills = request.getParameter("developedskills");
        //TODO alterar esse nome aqui
        String idSubCategory = request.getParameter("idSubCategory");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = new ConnectionFactory().retrieveConnection()) {

            CourseDAO courseDAO = new CourseDAO(connection);
            courseDAO.save(new Course(name, code, Integer.parseInt(completionTimeInHours),
                    CourseVisibility.from(visibility), targetAudience,
                    instructor, description, developedskills, teste(idSubCategory)));

            response.sendRedirect("/Harbitech_war/cursos");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SubCategory teste(String idSubCategory) {
        try (Connection connection = new ConnectionFactory().retrieveConnection()) {

            SubcategoryDAO subcategoryDAO = new SubcategoryDAO(connection);

            SubCategory subcategory = subcategoryDAO.findByCodeUrl(idSubCategory);

            return subcategory;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}