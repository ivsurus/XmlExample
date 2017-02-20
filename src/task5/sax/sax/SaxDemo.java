package task5.sax.sax;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import task5.sax.bean.Appetizer;
import task5.sax.bean.Food;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SaxDemo {
    public static void main(String[] args) throws ParserConfigurationException,
            SAXException, IOException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        MenuSaxHandler handler = new MenuSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource("menu.xml"));
// включение проверки действительности
        reader.setFeature("http://xml.org/sax/features/validation", true);
// включение обработки пространств имен
        reader.setFeature("http://xml.org/sax/features/namespaces", true);
// включение канонизации строк
        reader.setFeature("http://xml.org/sax/features/string-interning",
                true);
// отключение обработки схем
        reader.setFeature("http://apache.org/xml/features/validation/schema",
                false);

        Map<Appetizer, List<Food>> menu = handler.getAppetizersMap();

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
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }
}
