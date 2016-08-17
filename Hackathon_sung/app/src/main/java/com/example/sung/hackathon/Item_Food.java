package com.example.sung.hackathon;

/**
 * Created by Sung on 2016-06-01.
 */
public class Item_Food {
    String name;
    String Expiration;
    int cnt;
    int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String expiration) {
        this.Expiration = expiration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int icon) {
        this.cnt = icon;
    }

    public Item_Food(String name, int cnt, String Expiration, int icon) {
        this.name = name;
        this.cnt = cnt;
        this.Expiration = Expiration;
        this.icon = icon;
    }
}
