package com.techelevator.model;

import java.util.List;

public class Menu {

    private int menuId;
    private int truckId;
    private String hoursAvailable;
    private boolean active;
    private String menuName;
    private List<Submenu> subMenuList;

    public List<Submenu> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<Submenu> subMenuList) {
        this.subMenuList = subMenuList;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public String getHoursAvailable() {
        return hoursAvailable;
    }

    public void setHoursAvailable(String hoursAvailable) {
        this.hoursAvailable = hoursAvailable;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
