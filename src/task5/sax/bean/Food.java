package task5.sax.bean;


import java.io.Serializable;

public class Food implements Serializable {


    private String id;
    private String picture;
    private String name;
    private String description;
    private String portion;
    private String price;

    public Food(){}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getPortion() {
        return portion;
    }
    public void setPortion(String portion) {
        this.portion = portion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (id != null ? !id.equals(food.id) : food.id != null) return false;
        if (picture != null ? !picture.equals(food.picture) : food.picture != null) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (description != null ? !description.equals(food.description) : food.description != null) return false;
        if (portion != null ? !portion.equals(food.portion) : food.portion != null) return false;
        return price != null ? price.equals(food.price) : food.price == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (portion != null ? portion.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}