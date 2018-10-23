package com.nding1718.personalProjects.BlogSite.domain;


import java.util.Date;

public class Post {

    public final Date createDate;
    public Date updateDate;


    public Post() {
        this.createDate = new Date();
        this.updateDate = new Date();
    }

}
