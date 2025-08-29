package service;

import java.util.List;
import dao.CategoryDao;
import dao.CategoryDaoImpl;
import model.Category;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao dao = new CategoryDaoImpl();

    public List<Category> findAll() { return dao.findAll(); }
    public Category findById(int id) { return dao.findById(id); }
    public boolean create(Category c) { return dao.create(c) == 1; }
    public boolean update(Category c) { return dao.update(c) == 1; }
    public boolean delete(int id) { return dao.delete(id) == 1; }
}
