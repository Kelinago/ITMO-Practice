package practice.task5.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import practice.task5.api.NewsTitles;

@Component (name = "AIF News")
@Service (value = NewsTitles.class)
@Property (name = "source", value = "aif")
public class AIFNews implements NewsTitles {
    static final String lentaURL = "http://www.aif.ru/rss/news.php";
    
    /* TODO: Realise titles getter from XML */
    @Override
    public String[] getTitles() {
        return new String[0];
    }
}
