package dao;

import model.User;

public interface UserDao {
    User get(String username);
    User getByEmail(String email);          
    boolean updatePassword(int userId, String newPassword);
}
