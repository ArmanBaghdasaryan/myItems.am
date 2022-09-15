package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {


    private final Connection connection = DBConnectionProvider.getINSTANCE().getConnection();

    public List<Category> getAllCategory() {
        String sql = "SELECT * FROM category";
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                categories.add(getCategoryFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return categories;
    }



    public Category getById(int id) {
        String sql = "SELECT * FROM category WHERE id = " + id;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getCategoryFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        return Category.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();

    }

}
