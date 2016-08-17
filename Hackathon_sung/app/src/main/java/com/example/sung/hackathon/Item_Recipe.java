package com.example.sung.hackathon;

/**
 * Created by Sung on 2016-06-01.
 */
public class Item_Recipe {
    String title;
    String contents;
    //추가

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
