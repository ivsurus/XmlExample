package task5.sax.stax;

import task5.sax.bean.Appetizer;
import task5.sax.bean.Food;
import task5.sax.bean.menuName.MenuAttributeName;
import task5.sax.bean.menuName.MenuTagName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;


public class StAXMenuParser {

    public static void main(String[] args) throws FileNotFoundException {

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        try {
            InputStream input = new FileInputStream("menu.xml");

            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);

            Map<Appetizer,List<Food>> menu = process(reader);

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
                System.out.println("StaXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            }


        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static Map<Appetizer,List<Food>> process(XMLStreamReader reader) throws XMLStreamException {

        Map<Appetizer, List<Food>> appetizersMap = new HashMap<>();
        Appetizer appetizer = null;
        List<Food> foodList = null;
        Food food = null;
        MenuTagName elementName = null;
        List <String> type = null;

        while (reader.hasNext()) {

            switch (reader.next()) {

                case XMLStreamConstants.START_ELEMENT:

                    elementName = MenuTagName.valueOf(reader.getLocalName().toUpperCase());

                    switch (elementName) {
                        case FOOD:
                            food = new Food();
                            food.setId(reader.getAttributeValue(null, MenuAttributeName.ID.toString().toLowerCase()));
                            break;
                        case APPETIZER:
                            appetizer = new Appetizer();
                            foodList = new ArrayList<>();
                            appetizer.setName(reader.getAttributeValue(null, MenuAttributeName.NAME.toString().toLowerCase()));
                            break;
                        case TYPE:
                            type = new ArrayList<>();
                            type.add(reader.getAttributeValue(null, MenuAttributeName.ID.toString().toLowerCase()));
                            break;
                    }
                break;

                case XMLStreamConstants.CHARACTERS:

                    String text = reader.getText().trim();

                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case NAME:
                            food.setName(text);
                            break;
                        case PRICE:
                            type.add(text);
                            break;
                        case DESCRIPTION:
                            type.add(text);
                            break;
                        case PORTION:
                            food.setPortion(text);
                            break;
                        case PICTURE:
                            food.setPicture(text);
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:

                    elementName = MenuTagName.valueOf(reader.getLocalName().toUpperCase());

                    switch (elementName) {
                        case FOOD:
                            foodList.add(food);
                            food = null;
                            break;
                        case APPETIZER:
                            appetizersMap.put(appetizer, foodList);
                            appetizer = null;
                            break;
                        case TYPE:
                            food.setTypes(type.get(0), type.get(1), type.get(2));
                            break;
                    }
                    break;
            }
        }
        return appetizersMap;
    }
}
