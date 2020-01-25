package org.narzew.myplaces;

public class Event {

    Integer id;
    Integer city_id;
    String name;
    String author;
    String description;
    String location;
    String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Event(Integer id, Integer city_id, String name, String author, String description, String location, String date) {
        this.id = id;
        this.city_id = city_id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.location = location;
        this.date = date;
    }
}
