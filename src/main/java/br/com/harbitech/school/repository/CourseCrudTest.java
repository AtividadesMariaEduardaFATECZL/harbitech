package br.com.harbitech.school.repository;


import br.com.harbitech.school.model.category.Category;
import br.com.harbitech.school.model.course.Course;
import br.com.harbitech.school.model.course.CourseVisibility;
import br.com.harbitech.school.model.subcategory.SubCategory;
import br.com.harbitech.school.repository.dao.CourseDAO;
import br.com.harbitech.school.repository.factory.ConnectionFactory;
import br.com.harbitech.school.model.subcategory.SubCategoryStatus;

import java.sql.Connection;
import java.sql.SQLException;

public class CourseCrudTest {
    public static void main(String[] args) throws SQLException {
        Category category = new Category("Programacao", "programacao");

        SubCategory subCategory = new SubCategory(2L,"Java", "java-e-persistencia",
                1,"Persistencia com banco de dados",null, SubCategoryStatus.ACTIVE,
                category);

        Course jdbc = new Course("Java e JDBC: Trabalhando com um banco de dados", "jdbc",
                12, CourseVisibility.from("PÚBLICA"), "Pessoas com uma base de POO e BD",
                "Paulo Silveira", "\n" +
                "\n" +
                "    Comunique-se com um banco de dados relacional\n" +
                "    Indo além do Statement e do ResultSet\n" +
                "    Encapsule o acesso em um DAO\n" +
                "    Connection pool, datasources e outros recursos importantes\n" +
                "\n", "Entender melhor o banco de dados e um CRUD", subCategory);

        try (Connection connection = new ConnectionFactory().retrieveConnection()) {
            CourseDAO courseDAO = new CourseDAO(connection);
//            courseDAO.save(jdbc);
//            courseDAO.upgradeAllToPublicVisibility();
            courseDAO.delete("jdbc");
        }
    }
}
