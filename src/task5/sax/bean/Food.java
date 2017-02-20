package task5.sax.bean;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Food implements Serializable {


    private String id;
    private String picture;
    private String name;
    private String portion;
    private Map<String, Map<String,String >> types = new HashMap<>();


    public Food(){}

    public Map getTypes(){
        return types;
    }

    public void setTypes(String id, String description, String price){
        Map<String,String > type = new HashMap<>();
        type.put(description, price);
        types.put(id, type);
    }
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
        if (portion != null ? !portion.equals(food.portion) : food.portion != null) return false;
        return types != null ? types.equals(food.types) : food.types == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (portion != null ? portion.hashCode() : 0);
        result = 31 * result + (types != null ? types.hashCode() : 0);
        return result;
    }
}