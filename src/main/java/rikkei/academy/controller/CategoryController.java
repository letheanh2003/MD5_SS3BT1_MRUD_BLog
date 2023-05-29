package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Category;
import rikkei.academy.service.category.ICategoryService;

import java.util.Optional;
@Controller
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/")
    public ModelAndView findAllCategory() {
        ModelAndView mav= new ModelAndView("/category/list");
        Iterable<Category> listCategory= categoryService.findAll();
        mav.addObject("listCategory", listCategory);
        return mav;
    }

    @GetMapping("/formCreate")
    public ModelAndView showForm() {
        ModelAndView mav= new ModelAndView("/category/create");
        mav.addObject("category",new Category());
        return mav;
    }

    @PostMapping("/createCategory")
    public String createCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        categoryService.remove(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,Model model) {
        Optional<Category> category= categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("editCategory", category.get());
        }
        return "/category/edit";
    }

    @PostMapping("/updateCategory")
    public String update(@ModelAttribute("editCategory") Category category) {
        categoryService.save(category);
        return "redirect:/";
    }

}
