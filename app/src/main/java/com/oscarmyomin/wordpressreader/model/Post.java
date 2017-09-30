package com.oscarmyomin.wordpressreader.model;

/**
 * Created by philippe on 9/23/17.
 */

public class Post {
    String image, title, content, author_image, author_name, time, link;

    public Post() {
    }

    public Post(String image, String title, String author_image, String author_name, String content, String time, String link) {
        this.image = image;
        this.title = title;
        this.author_image = author_image;
        this.author_name = author_name;
        this.content = content;
        this.time = time;
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_image() {
        return author_image;
    }

    public void setAuthor_image(String author_image) {
        this.author_image = author_image;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
