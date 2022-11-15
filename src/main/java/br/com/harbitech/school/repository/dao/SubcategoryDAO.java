package br.com.harbitech.school.repository.dao;

import br.com.harbitech.school.model.subcategory.SubCategory;
import br.com.harbitech.school.model.subcategory.dto.SubcategoriesCodesDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubcategoryDAO {
    private Connection connection;

    public SubcategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<String> findAllCodes() throws SQLException {
        connection.setAutoCommit(false);
        List<String> subcategoriesNames = new ArrayList<>();
        String sql = """
                SELECT code_url FROM subcategory
                """;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            connection.commit();
            turnResultSetInSubcategory(subcategoriesNames, pstm);
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
        return subcategoriesNames;
    }

//    public SubCategory findById(Long id) throws SQLException {
//        SubCategory subCategory = new SubCategory();
//        connection.setAutoCommit(false);
//        String sql = """
//                SELECT s.name, s.code_url, s.category_id
//                FROM subcategory s WHERE id = ?
//                """;
//        try (PreparedStatement stm = connection.prepareStatement(sql)) {
//            stm.setLong(1, id);
//            stm.execute();
//            try (ResultSet rst = stm.getResultSet()) {
//                while (rst.next()) {
//                    subCategory = new SubCategory(rst.getString("s.name"), rst.getString("s.code_url"), category);
//                }
//            }
//            connection.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            connection.rollback();
//        } finally {
//            connection.close();
//        }
//        return subCategory;
//    }
//
    public SubCategory findByCodeUrl(String codeUrl) throws SQLException {
        SubCategory subCategory = new SubCategory();
        connection.setAutoCommit(false);
        String sql = """
                SELECT s.id, s.name, s.code_url
                FROM subcategory s WHERE s.code_url = ?
                """;
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, codeUrl);
            stm.execute();
            try (ResultSet rst = stm.getResultSet()) {
                while (rst.next()) {
                    subCategory = new SubCategory(rst.getLong("s.id"), rst.getString("s.name"),
                            rst.getString("s.code_url"));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
        return subCategory;
    }

    private void turnResultSetInSubcategory(List<String> subcategoriesNames, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                SubcategoriesCodesDto subCategory = new SubcategoriesCodesDto(rst.getString("code_url"));
                subcategoriesNames.add(subCategory.getName());
            }
        }
    }
}
