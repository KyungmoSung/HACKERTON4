package com.example.sung.hackathon;

/**
 * Created by Sung on 2016-06-01.
 */
public class Item_Recipe {
    String title;
    String sub_title;
    String contents;
    String[] food;
    int img;
    //추가

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public int getImg() {
        return img;
    }

    public Item_Recipe(String title, String sub_title, String contents, String[] food, int img) {
        this.title = title;
        this.sub_title = sub_title;
        this.contents = contents;
        this.food = food;
        this.img = img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String[] getFood() {
        return food;
    }

    public void setFood(String[] food) {
        this.food = food;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String time) {
        this.contents = time;
    }

    public Item_Recipe(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
