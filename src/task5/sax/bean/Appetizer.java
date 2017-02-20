package task5.sax.bean;


import java.io.Serializable;

public class Appetizer implements Serializable {

    private String name;

    public Appetizer(){}

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appetizer appetizer = (Appetizer) o;

        return name != null ? name.equals(appetizer.name) : appetizer.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
