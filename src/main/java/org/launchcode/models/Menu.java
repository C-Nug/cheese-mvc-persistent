package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CNUG on 6/26/17.
 */
@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @ManyToMany
    private List<Cheese> cheeses = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public Menu() {}

    public void addItem(Cheese item) {
        cheeses.add(item);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public void setName(String name) {
        this.name = name;
    }


}
