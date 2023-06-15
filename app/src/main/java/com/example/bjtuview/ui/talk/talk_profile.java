package com.example.bjtuview.ui.talk;

public class talk_profile {
    String title;
    String content;
    String user;
    int id;

    String time;

    public talk_profile(String title, String content, String user, int id,String time) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.id = id;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


