package com.example.sung.hackathon.mall;

/**
 * Created by Minwoo on 2016. 8. 19..
 */
public class Mall {
    private String id;
    private String pw;
    private String current;
    private boolean sync;

    public Mall() {
        setCurrent(null);
        setSync(false);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
}
