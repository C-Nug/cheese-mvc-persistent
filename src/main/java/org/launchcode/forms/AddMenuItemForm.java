package org.launchcode.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

/**
 * Created by CNUG on 6/26/17.
 */
public class AddMenuItemForm {

    private Menu menu;
    private Iterable<Cheese> cheeses;

    @NotNull
    private Integer menuId;
    @NotNull
    private Integer cheeseId;

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }

    public AddMenuItemForm() { }

    public Menu getMenu() {
        return menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public Integer getCheeseId() {
        return cheeseId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public void setCheeseId(Integer cheeseId) {
        this.cheeseId = cheeseId;
    }
}
