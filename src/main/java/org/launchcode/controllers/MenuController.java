package org.launchcode.controllers;

import org.launchcode.forms.AddMenuItemForm;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by CNUG on 6/26/17.
 */
@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());

        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMenuForm(Model model) {
        model.addAttribute("title", "Add New Menu");
        model.addAttribute(new Menu());

        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenuForm(Model model, @ModelAttribute @Valid Menu newMenu,
                                     Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add New Menu");
            return "menu/add";
        }
        else {
            menuDao.save(newMenu);
            return "redirect:view/" + newMenu.getId();
        }
    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable Integer menuId) {
        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("title", menu.getName());
        model.addAttribute("menu", menu);

        return "menu/view";

    }

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable Integer menuId) {
        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("menu", menu);

        AddMenuItemForm menuItemForm = new AddMenuItemForm(menu, cheeseDao.findAll());
        model.addAttribute("form", menuItemForm);

        return "menu/add-item";
    }

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.POST)
    public String processAddItem(Model model, @ModelAttribute @Valid AddMenuItemForm menuItemForm,
                          Errors errors, @PathVariable Integer menuId) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add item to menu: " + menuItemForm.getMenuId());
            return "menu/add-item/" + menuId;
        }
        else {
            Cheese cheese = cheeseDao.findOne(menuItemForm.getCheeseId());
            Menu menu = menuDao.findOne(menuItemForm.getMenuId());
            menu.addItem(cheese);
            menuDao.save(menu);
            return "redirect:/menu/view/" + menuId;
        }

    }

}
