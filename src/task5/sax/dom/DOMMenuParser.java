package task5.sax.dom;


import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import task5.sax.bean.Appetizer;
import task5.sax.bean.Food;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DOMMenuParser {

    public static void main(String[] args) throws SAXException, IOException{

        Map<Appetizer, List<Food>> appetizersMap = new HashMap<>();
        List<Food> menu = new ArrayList<Food>();
        //создание DOM-анализатора (Xerces)
        DOMParser parser = new DOMParser();
        parser.parse("menu2.xml");

        Document document = parser.getDocument();
        Element root = document.getDocumentElement();


        NodeList foodNodes = root.getElementsByTagName("food");
        Food food = null;

        for (int i = 0; i < foodNodes.getLength(); i++) {
            food = new Food();
            Element foodElement = (Element) foodNodes.item(i);
            food.setId(foodElement.getAttribute("id"));
            food.setName(getSingleChild(foodElement, "name").getTextContent().trim());
            food.setDescription(getSingleChild(foodElement, "description").getTextContent().trim());
            menu.add(food);
        }
        for (Food f: menu) {
            System.out.println(f.getName() + ", " + f.getId() + ", " + f.getDescription());
        }
    }

    private static Element getSingleChild(Element element, String childName){
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }
}
