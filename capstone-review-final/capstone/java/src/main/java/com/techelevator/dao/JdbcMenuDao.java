package com.techelevator.dao;

import com.techelevator.model.Menu;
import com.techelevator.model.MenuItem;
import com.techelevator.model.Submenu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMenuDao implements MenuDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMenuDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Menu getMenu(int menuId) {

        Menu menu = null;

        String sql = "SELECT menu_id, truck_id, hours_available, active, menu_name\n" +
                "FROM menu\n" +
                "WHERE menu_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, menuId);

        while(results.next()){
            menu = convertRowToMenu(results);
        }

        return menu;
    }

    private Menu convertRowToMenu(SqlRowSet results) {
        Menu menu = new Menu();
        menu.setActive(results.getBoolean("active"));
        menu.setHoursAvailable(results.getString("hours_available"));
        menu.setMenuId(results.getInt("menu_id"));
        menu.setMenuName(results.getString("menu_name"));
        menu.setTruckId(results.getInt("truck_id"));

        //get sub menus
        List<Submenu> submenus = getSubMenus(menu.getMenuId());
        menu.setSubMenuList(submenus);

        return menu;
    }

    public List<Submenu> getSubMenus(int menuId){
        List<Submenu> submenus = new ArrayList<>();

        String sql = "SELECT submenu_id, menu_id, submenu_name \n" +
                "FROM submenu\n" +
                "WHERE menu_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, menuId);

        while(results.next()){
            Submenu submenu = convertRowToSubmenu(results);
            submenu.setMenuItems(getMenuItems(submenu.getSubMenuId()));
            submenus.add(submenu);
        }

        return submenus;
    }

    public Submenu getSubMenu(int id){
        Submenu submenu = null;

        String sql = "SELECT submenu_id, menu_id, submenu_name \n" +
                "FROM submenu\n" +
                "WHERE submenu_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while(results.next()){
            submenu = convertRowToSubmenu(results);
            submenu.setMenuItems(getMenuItems(submenu.getSubMenuId()));
        }

        return submenu;
    }

    private Submenu convertRowToSubmenu(SqlRowSet results) {
        Submenu submenu = new Submenu();
        submenu.setMenuId( results.getInt("menu_id"));
        submenu.setSubMenuId( results.getInt("submenu_id"));
        submenu.setSubMenuName( results.getString("submenu_name"));
        return submenu;
    }

    public List<MenuItem> getMenuItems(int subMenuId){
        List menuItems = new ArrayList<MenuItem>();

        String sql = "SELECT menu_item_id, submenu_id, active, price, menu_item_name, description\n" +
                "FROM menu_items\n" +
                "WHERE submenu_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, subMenuId);
        while(results.next()){

            MenuItem item = convertRowToMenuItem(results);
            menuItems.add(item);
        }

        return menuItems;
    }

    public MenuItem getMenuItem(int id){
        MenuItem item = null;

        String sql = "SELECT menu_item_id, submenu_id, active, price, menu_item_name, description\n" +
                "FROM menu_items\n" +
                "WHERE menu_item_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()){
            item = convertRowToMenuItem(results);
        }

        return item;
    }

    private MenuItem convertRowToMenuItem(SqlRowSet results) {
        MenuItem item = new MenuItem();
        item.setActive(results.getBoolean("active"));
        item.setDescription(results.getString("description"));
        item.setMenuItemId(results.getInt("menu_item_id"));
        item.setMenuItemName(results.getString("menu_item_name"));
        item.setPrice(results.getBigDecimal("price"));
        item.setSubMenuId(results.getInt("submenu_id"));

        return item;
    }

    public Menu createMenu(Menu menu) {

        String sql = "INSERT INTO menu(truck_id, hours_available, active, menu_name)\n" +
                "VALUES(?,?,?,?) RETURNING menu_id;";

        int menuId = jdbcTemplate.queryForObject(sql, int.class, menu.getTruckId(),
                menu.getHoursAvailable(), menu.isActive(), menu.getMenuName());

        return getMenu(menuId);
    }

    public Submenu createSubmenu(Submenu submenu){
        String sql = "INSERT INTO submenu(menu_id, submenu_name)\n" +
                "VALUES(?,?) RETURNING submenu_id;";

        int submenuId = jdbcTemplate.queryForObject(sql, int.class, submenu.getMenuId(),
                           submenu.getSubMenuName());
        return getSubMenu(submenuId);
    }

    public MenuItem createMenuItem(MenuItem menuItem){
        String sql = "INSERT INTO menu_items(submenu_id, active, price, menu_item_name, description)\n" +
                "VALUES(?,?,?,?,?) RETURNING menu_item_id;";

        int menuItemId = jdbcTemplate.queryForObject(sql, int.class, menuItem.getSubMenuId(), menuItem.isActive(), menuItem.getPrice(),
                menuItem.getMenuItemName(), menuItem.getDescription());

        return getMenuItem(menuItemId);
    }


}
