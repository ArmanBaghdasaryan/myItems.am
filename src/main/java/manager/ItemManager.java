package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Item;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private final Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
    private final UserManager userManager = new UserManager();
    private final CategoryManager categoryManager = new CategoryManager();


    public void add(Item item) {
        String sql = "insert into item(title,price,category_id,pic_url,user_id) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getCategory().getId());
            ps.setString(4, item.getPicUrl());
            ps.setInt(5, item.getUser().getId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Item> getAllItems() {
        String sql = "SELECT * FROM item";
        List<Item> items = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return items;
    }

    public List<Item> getTwentyItems() {
        String sql = "select * from item order by id desc limit 20";
        List<Item> items = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return items;
    }


    public List<Item> getItemsByCategoryId(int id) {
        String sql = "SELECT * FROM item where category_id = " + id + " order by id desc LIMIT 20 ";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }


    public void deleteItemById(int id) {
        String sql = "delete  from item where id =" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt("id"));
        item.setTitle(resultSet.getString("title"));
        item.setPrice(resultSet.getDouble("price"));
        int categoryId = resultSet.getInt("category_id");
        item.setPicUrl(resultSet.getString("pic_url"));
        int userId = resultSet.getInt("user_id");
        User user = userManager.getById(userId);
        Category category = categoryManager.getById(categoryId);

        item.setCategory(category);
        item.setUser(user);


        return item;
    }

}


