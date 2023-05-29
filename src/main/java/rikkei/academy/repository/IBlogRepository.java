package rikkei.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import rikkei.academy.model.Blog;
import rikkei.academy.model.Category;

import java.util.List;

public interface IBlogRepository extends PagingAndSortingRepository<Blog, Integer> {
    // Copntaining : tương đương
    Page<Blog> findAllByBlogNameContaining(String blogName, Pageable pageable);
    List<Blog> findBlogByCategoryId(int CategoryId);
}
