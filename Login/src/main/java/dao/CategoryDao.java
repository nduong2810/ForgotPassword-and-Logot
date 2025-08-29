package dao;
import java.util.List;
import model.Category;

public interface CategoryDao {
    List<Category> findAll();
    Category findById(int id);
    int create(Category c);
    int update(Category c);
    int delete(int id);
}
