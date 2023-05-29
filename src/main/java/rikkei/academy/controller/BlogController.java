package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Blog;
import rikkei.academy.model.Category;
import rikkei.academy.service.blog.IBlogService;
import rikkei.academy.service.category.ICategoryService;


import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> provinces() {
        return categoryService.findAll();
    }

    @GetMapping("/findAllBlog")
    public String listBlog(@RequestParam("search") Optional<String> search,
                           @RequestParam("sortBy") Optional<String> sort
            , Pageable pageable, Model model) {
        Page<Blog> blogs = null;
        Sort sortAble = null;
        if (sort.isPresent()) {
            if (sort.get().equals("ASC")){
                sortAble = Sort.by("blogName").ascending();
            }else {
                sortAble = Sort.by("blogName").descending();
            }
        }else {
            sortAble = Sort.by("blogName").ascending();
        }
        pageable = PageRequest.of(pageable.getPageNumber(), 2, sortAble);

        if (search.isPresent()) {
            blogs = blogService.findAllByBlogNameContaining(search.get(), pageable);
        } else {
//            int pageSize = blogs.getSize();\
            blogs = blogService.findAllBlog(pageable);
        }

        model.addAttribute("listBlog", blogs);
        model.addAttribute("searchName", search.isPresent()?search.get():"");
        model.addAttribute("sortBy", sort.isPresent()?sort.get():"ASC");

        return "/blog/listBlog";
    }


    @GetMapping("/formCreateBlog")
    public String formCreateBlog(Model model) {
        Blog blog = new Blog();
        model.addAttribute("newBlog", blog);
        return "/blog/create";
    }

    @PostMapping("/createBlog")
    public String create(@ModelAttribute("newBlog") Blog blog) {
        blogService.save(blog);
        return "redirect:findAllBlog";
    }

    @GetMapping("/editBlog/{id}")
    public String editBlog(@PathVariable("id") int id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("editBlog", blog.get());
        }
        return "/blog/edit";
    }

    @PostMapping("/updateBlog")
    public String updateBlog(@ModelAttribute("editBlog") Blog blog) {
        blogService.save(blog);
        return "redirect:findAllBlog";
    }

    @GetMapping("/deleteBlog/{id}")
    public String deleteBlog(@PathVariable("id") String id) {
        blogService.remove(Integer.parseInt(id));
        return "redirect:/findAllBlog";
    }


}