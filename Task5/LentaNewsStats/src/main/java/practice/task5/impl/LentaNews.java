package practice.task5.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import practice.task5.api.NewsTitles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component (name = "Lenta News")
@Service (value = NewsTitles.class)
@Properties ({
        @Property (name = "source", value = "lenta"),
        @Property (name = "sourceURL", value = LentaNews.lentaURL)
})
public class LentaNews implements NewsTitles {
    static final String lentaURL = "https://api.lenta.ru/lists/latest";
    
    @Override
    public String[] getTitles() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            URL lenta = new URL("https://api.lenta.ru/lists/latest");
            InputStreamReader streamReader = new InputStreamReader(lenta.openStream(), "UTF-8");
            BufferedReader urlReader = new BufferedReader(streamReader);
            jsonObject = (JSONObject) parser.parse(urlReader);
        } catch (ParseException e) {
            System.out.println("Can't parse from Lenta API");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Lenta API Encoding");
        } catch (IOException e) {
            System.out.println("Lenta News: Can't open stream. Something wrong with your internet connection...");
        }
        if (jsonObject == null) {
            return new String[0];
        }
        
        JSONArray headlines = (JSONArray) jsonObject.get("headlines");
        List<String> titles = new ArrayList<>(headlines.size());
        for (Object obj : headlines) {
            titles.add(
                    ((String)
                            ((JSONObject)
                                    ((JSONObject) obj).get("info")
                            ).get("title")
                    ).replace("?", "")
            );
        }
        return titles.toArray(new String[]{});
    }
}