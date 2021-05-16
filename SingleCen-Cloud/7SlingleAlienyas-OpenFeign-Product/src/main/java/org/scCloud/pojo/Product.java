package org.scCloud.pojo;

import java.util.Date;

public class Product {
    private String name;
    private String access;
    private String price;
    private Date date;
    private String describe;

    public Product() {
    }

    public Product(String name, String access, String price, Date date, String describe) {
        this.name = name;
        this.access = access;
        this.price = price;
        this.date = date;
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", access='" + access + '\'' +
                ", price='" + price + '\'' +
                ", date=" + date +
                ", describe='" + describe + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
