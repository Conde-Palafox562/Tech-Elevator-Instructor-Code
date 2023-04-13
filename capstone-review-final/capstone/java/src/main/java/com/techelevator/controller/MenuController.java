package com.techelevator.controller;

import com.techelevator.dao.MenuDao;
import com.techelevator.model.Menu;
import com.techelevator.model.MenuItem;
import com.techelevator.model.Submenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @RequestMapping(path="/menu/{id}",method= RequestMethod.GET)
    public Menu getMenu(@PathVariable int id) {
        return menuDao.getMenu(id);
    }

    @RequestMapping(path="/menu",method= RequestMethod.POST)
    public Menu getMenu(@RequestBody Menu menu) {
        return menuDao.createMenu(menu);
    }

    @RequestMapping(path="/submenu",method= RequestMethod.POST)
    public Submenu getMenu(@RequestBody Submenu submenu) {
        return menuDao.createSubmenu(submenu);
    }

    @RequestMapping(path="/menuitem",method= RequestMethod.POST)
    public MenuItem getMenu(@RequestBody MenuItem item) {
        return menuDao.createMenuItem(item);
    }
}
