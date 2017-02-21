package task5.sax.runner;


import org.xml.sax.SAXException;
import task5.sax.bean.Appetizer;
import task5.sax.bean.Food;
import task5.sax.dom.DOMMenuParser;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException, SAXException {
        Map <Appetizer, List<Food>> menu;
        DOMMenuParser domParser = new DOMMenuParser();
        menu = domParser.parseMenu();
        
        showMenu(menu);
    }

    public static void showMenu(Map menu){
        //вывод
        Iterator<Map.Entry<Appetizer, List<Food>>> foodIterator = menu.entrySet().iterator();

        while (foodIterator.hasNext()){

            Map.Entry<Appetizer, List<Food>> pair = foodIterator.next();

            System.out.println(pair.getKey().getName());

            for (Food food : pair.getValue()) {

                System.out.println("FOOD ID: " + food.getId());
                System.out.println("FOOD PICTURE: " + food.getPicture());
                System.out.println("FOOD NAME: " + food.getName());
                System.out.println("FOOD PORTION: " + food.getPortion());
                System.out.println("Количество типов " + food.getTypes().size());
                Iterator<Map.Entry<String, Map<String,String>>> typesIterator = food.getTypes().entrySet().iterator();

                while (typesIterator.hasNext()){
                    Map.Entry<String, Map<String,String>> typesPair = typesIterator.next();
                    System.out.println("TYPE ID: " + typesPair.getKey());
                    Iterator<Map.Entry<String,String>> typeIterator = typesPair.getValue().entrySet().iterator();
                    while(typeIterator.hasNext()){
                        Map.Entry<String,String> typePair = typeIterator.next();
                        System.out.println("TYPE DESCRIPTION: " + typePair.getKey());
                        System.out.println("TYPE PRICE: " + typePair.getValue());
                    }
                }
            }
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
}
