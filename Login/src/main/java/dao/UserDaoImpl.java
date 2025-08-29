package dao;

import java.sql.*;
import model.User;
import utils.DBConnection;

public class UserDaoImpl implements UserDao {
    public Connection conn; public PreparedStatement ps; public ResultSet rs;

    @Override
    public User get(String username) {
        String sql = "SELECT id,email,userName AS username,fullName AS fullname," +
                     "passWord AS password,avatar,roleId AS roleid,phone,createdDate " +
                     "FROM dbo.[User] WHERE userName=?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) return map(rs);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        String sql = "SELECT id,email,userName AS username,fullName AS fullname," +
                     "passWord AS password,avatar,roleId AS roleid,phone,createdDate " +
                     "FROM dbo.[User] WHERE email=?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) return map(rs);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public boolean updatePassword(int userId, String newPassword) {
        String sql = "UPDATE dbo.[User] SET passWord=? WHERE id=?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);   // NOTE: plain text cho khớp code hiện tại
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    private User map(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setUserName(rs.getString("username"));
        u.setFullName(rs.getString("fullname"));
        u.setPassWord(rs.getString("password"));
        u.setAvatar(rs.getString("avatar"));
        u.setRoleid(rs.getInt("roleid"));
        u.setPhone(rs.getString("phone"));
        u.setCreatedDate(rs.getDate("createdDate"));
        return u;
    }
}
