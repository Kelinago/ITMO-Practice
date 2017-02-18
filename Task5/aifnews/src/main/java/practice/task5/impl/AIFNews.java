package practice.task5.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import practice.task5.api.NewsTitles;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;


@Component (name = "AIF News")
@Service (value = NewsTitles.class)
@Property (name = "source", value = "aif")
public class AIFNews implements NewsTitles {
    private static final String aifURL = "http://www.aif.ru/rss/news.php";
    
    /* TODO: Realise titles getter from XML */
    @Override
    public String[] getTitles() {
        ArrayList<String> result = new ArrayList<>();
        try {
            URL url = new URL(aifURL);
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(url.openStream());
            NodeList nodes = document.getElementsByTagName("title");
            for (int i = 0; i < nodes.getLength(); i++){
                Node item = nodes.item(i);
                if (!item.getParentNode().getNodeName().equals("channel")){
                    result.add(item.getTextContent());
                }
            }
            return result.toArray(new String[result.size()]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}
