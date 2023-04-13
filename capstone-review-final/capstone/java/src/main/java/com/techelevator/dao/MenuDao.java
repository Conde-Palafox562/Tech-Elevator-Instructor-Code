package com.techelevator.dao;

import com.techelevator.model.Menu;
import com.techelevator.model.MenuItem;
import com.techelevator.model.Submenu;

import java.util.List;

public interface MenuDao {
    public Menu getMenu(int menuId);
    public List<Submenu> getSubMenus(int menuId);
    public Submenu getSubMenu(int id);
    public List<MenuItem> getMenuItems(int subMenuId);
    public MenuItem getMenuItem(int id);
    public Menu createMenu(Menu menu);
    public Submenu createSubmenu(Submenu submenu);
    public MenuItem createMenuItem(MenuItem menuItem);
}
