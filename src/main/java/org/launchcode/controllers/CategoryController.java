package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by CNUG on 6/19/17.
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getCategories(Model model) {
        Iterable categories = categoryDao.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("title", "Cheese Categories");
        return "category/index";

    }

    @RequestMapping(path = "add", method = RequestMethod.GET)
    public String addCategory(Model model) {
        model.addAttribute(new Category());
        model.addAttribute("title", "Add Category");

        return "category/add";
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
    public String addCategory(Model model, @ModelAttribute @Valid Category category, Errors errors) {

        if (errors.hasErrors()) {
            return "category/add";
        }
        else {
            categoryDao.save(category);
            return "redirect:";
        }

    }
}
