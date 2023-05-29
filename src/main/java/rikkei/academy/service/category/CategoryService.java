package rikkei.academy.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikkei.academy.model.Category;
import rikkei.academy.repository.ICategoryRepository;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }
}

