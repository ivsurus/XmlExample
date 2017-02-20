package task5.sax.dom;


import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import task5.sax.bean.Appetizer;
import task5.sax.bean.Food;
import task5.sax.bean.menuName.MenuAttributeName;
import task5.sax.bean.menuName.MenuTagName;

import java.io.IOException;
import java.util.*;


public class DOMMenuParser {

    public static void main(String[] args) throws SAXException, IOException{

        Map<Appetizer, List<Food>> appetizersMap = new HashMap<>();
        List<Food> menu = new ArrayList<Food>();
        //создание DOM-анализатора (Xerces)
        DOMParser parser = new DOMParser();
        parser.parse("menu.xml");

        Document document = parser.getDocument();

        Element appertizersElement = document.getDocumentElement();
        NodeList appertizerNodes = appertizersElement.getElementsByTagName(MenuTagName.APPETIZER.toString().toLowerCase());

        Appetizer appetizer = null;

        for (int i = 0; i < appertizerNodes.getLength(); i++) {
            appetizer = new Appetizer();
            //food = new Food();
            Element appertizerElement = (Element) appertizerNodes.item(i);
            appetizer.setName(appertizerElement.getAttribute(MenuAttributeName.NAME.toString().toLowerCase()));

            //  food.setName(getSingleChild(foodElement, "name").getTextContent().trim());
        //    food.setDescription(getSingleChild(foodElement, "description").getTextContent().trim());
        //    menu.add(food);
              appetizersMap.put(appetizer,null);
              NodeList foodNodes = appertizerElement.getElementsByTagName(MenuTagName.FOOD.toString().toLowerCase());
              Food food;

            for (int j = 0; j < foodNodes.getLength(); j++ ){
                food = new Food();
                Element foodElement = (Element) foodNodes.item(i);
                food.setId(foodElement.getAttribute(MenuAttributeName.ID.toString().toLowerCase()));
                food.setName(getSingleChild(foodElement, MenuTagName.NAME.toString().toLowerCase()).getTextContent().trim());
                food.setPicture(getSingleChild(foodElement, MenuTagName.PICTURE.toString().toLowerCase()).getTextContent().trim());
                food.setPortion(getSingleChild(foodElement, MenuTagName.PORTION.toString().toLowerCase()).getTextContent().trim());
            }
        }

        Iterator<Map.Entry<Appetizer,List<Food>>> iterator = appetizersMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Appetizer,List<Food>> pair = iterator.next();
            System.out.println("ТИПЫ: " + pair.getKey().getName());
        }

    }

    private static Element getSingleChild(Element element, String childName){
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }
}
