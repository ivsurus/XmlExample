package task5.sax;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
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
        reader.parse(new InputSource("menu2.xml"));
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
        Iterator<Map.Entry<Appetizer, List<Food>>> iterator = menu.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Appetizer, List<Food>> pair = iterator.next();
            System.out.println(pair.getKey().getName());
            for (Food food : pair.getValue()) {
                System.out.println(food.getId());
                System.out.println(food.getPicture());
                System.out.println(food.getName());
                System.out.println(food.getDescription());
                System.out.println(food.getPortion());
                System.out.println(food.getPrice());
                System.out.println();
            }
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }
}
