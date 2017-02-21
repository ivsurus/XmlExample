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

    public final static String MENU = "menu.xml";

    public static Map parseMenu() throws SAXException, IOException{

        DOMParser parser = new DOMParser();
        parser.parse(MENU);
        Document document = parser.getDocument();
        Map<Appetizer, List<Food>> appetizersMap = new HashMap<>();
        List<Food> foodList;
        Element appertizersElement = document.getDocumentElement();
        NodeList appertizerNodes = appertizersElement.getElementsByTagName(MenuTagName.APPETIZER.toString().toLowerCase());

        Appetizer appetizer;
        for (int i = 0; i < appertizerNodes.getLength(); i++) {
            foodList = new ArrayList<>();
            appetizer = new Appetizer();
            Element appertizerElement = (Element) appertizerNodes.item(i);
            appetizer.setName(appertizerElement.getAttribute(MenuAttributeName.NAME.toString().toLowerCase()));
            appetizersMap.put(appetizer, null);
            NodeList foodNodes = appertizerElement.getElementsByTagName(MenuTagName.FOOD.toString().toLowerCase());
            Food food;

            for (int j = 0; j < foodNodes.getLength(); j++ ){
                food = new Food();
                Element foodElement = (Element) foodNodes.item(j);
                food.setId(foodElement.getAttribute(MenuAttributeName.ID.toString().toLowerCase()));
                food.setName(getSingleChild(foodElement, MenuTagName.NAME.toString().toLowerCase()).getTextContent().trim());
                food.setPicture(getSingleChild(foodElement, MenuTagName.PICTURE.toString().toLowerCase()).getTextContent().trim());
                food.setPortion(getSingleChild(foodElement, MenuTagName.PORTION.toString().toLowerCase()).getTextContent().trim());
                foodList.add(food);
                NodeList typeNodes = foodElement.getElementsByTagName(MenuTagName.TYPE.toString().toLowerCase());
                for (int k=0; k < typeNodes.getLength(); k++) {
                    Element typeElement = (Element) typeNodes.item(k);
                    List<String> type = new ArrayList<>();
                    type.add(typeElement.getAttribute(MenuAttributeName.ID.toString().toLowerCase()));
                    type.add(getSingleChild(typeElement, MenuTagName.DESCRIPTION.toString().toLowerCase()).getTextContent().trim());
                    type.add(getSingleChild(typeElement, MenuTagName.PRICE.toString().toLowerCase()).getTextContent().trim());
                    food.setTypes(type.get(0),type.get(1),type.get(2));
                }
            }
            appetizersMap.put(appetizer,foodList);
        }
        return appetizersMap;
    }

    private static Element getSingleChild(Element element, String childName){
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }
}
