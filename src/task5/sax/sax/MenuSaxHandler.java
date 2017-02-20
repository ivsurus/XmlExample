package task5.sax.sax;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import task5.sax.bean.Appetizer;
import task5.sax.bean.Food;
import task5.sax.bean.tag.MenuTagName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuSaxHandler extends DefaultHandler {

    private Map<Appetizer, List<Food>> appetizersMap = new HashMap<>();
    private List<Food> foodList;
    private Appetizer appetizer;
    private Food food;

    private StringBuilder text;

 /*   public List<Food> getFoodList() {
        return foodList;
    }*/

    public Map getAppetizersMap() {
        return appetizersMap;
    }

    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }

    public void endDocument() throws SAXException {
        System.out.println("Parsing ended.");
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        System.out.println("startElement -> " + "uri: " + uri + ", localName: " + localName + ", qName: " + qName);
        text = new StringBuilder();

        if (qName.equals("food")){
            food = new Food();
            food.setId(attributes.getValue("id"));
        }
        if (qName.equals("appetizer")){
            appetizer = new Appetizer();
            appetizer.setName(attributes.getValue("name"));
            foodList = new ArrayList<>();
        }
    }
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace("-", "_"));

        switch(tagName){
            case APPETIZER:
                appetizersMap.put(appetizer, foodList);
                appetizer = null;
                break;
            case NAME:
                food.setName(text.toString());
                break;
            case PRICE:
                food.setPrice(text.toString());
                break;
            case DESCRIPTION:
                food.setDescription(text.toString());
                break;
            case PORTION:
                food.setPortion(text.toString());
                break;
            case PICTURE:
                food.setPicture(text.toString());
                break;
            case FOOD:
                foodList.add(food);
                food = null;
                break;
        }
    }
    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }
    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }
    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
        throw (exception);
    }
}

