package practice.task5.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
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
@Properties({
        @Property (name = "source", value = "aif"),
        @Property(name = "sourceURL", value = AIFNews.aifURL)
})
public class AIFNews implements NewsTitles {
    static final String aifURL = "http://www.aif.ru/rss/news.php";
    
    /* TODO: Realise error messages */
    @Override
    public String[] getTitles() {
        ArrayList<String> result = new ArrayList<>();
        try {
            URL url = new URL(aifURL);
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(url.openStream());
            NodeList nodes = document.getElementsByTagName("title");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node item = nodes.item(i);
                if (item.getParentNode().getNodeName().equals("item")) {
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
