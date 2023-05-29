package rikkei.academy.service.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rikkei.academy.model.Blog;
import rikkei.academy.service.IGenericService;

public interface IBlogService extends IGenericService<Blog> {
    Page<Blog> findAllBlog(Pageable pageable);
    Page<Blog> findAllByBlogNameContaining(String blogName, Pageable pageable);
}
