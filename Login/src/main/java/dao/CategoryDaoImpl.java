package dao;

import java.sql.*;
import java.util.*;
import model.Category;
import utils.DBConnection;

public class CategoryDaoImpl implements CategoryDao {

    private Category map(ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setId(rs.getInt("cate_id"));
        c.setName(rs.getString("cate_name"));
        c.setIcon(rs.getString("icons"));
        return c;
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT cate_id,cate_name,icons FROM dbo.Category ORDER BY cate_id DESC";
        List<Category> list = new ArrayList<>();
        try (Connection c = new DBConnection().getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Category findById(int id) {
        String sql = "SELECT cate_id,cate_name,icons FROM dbo.Category WHERE cate_id=?";
        try (Connection c = new DBConnection().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public int create(Category x) {
        String sql = "INSERT INTO dbo.Category(cate_name,icons) VALUES(?,?)";
        try (Connection c = new DBConnection().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, x.getName());
            ps.setString(2, x.getIcon());
            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public int update(Category x) {
        String sql = "UPDATE dbo.Category SET cate_name=?, icons=? WHERE cate_id=?";
        try (Connection c = new DBConnection().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, x.getName());
            ps.setString(2, x.getIcon());
            ps.setInt(3, x.getId());
            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM dbo.Category WHERE cate_id=?";
        try (Connection c = new DBConnection().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}
