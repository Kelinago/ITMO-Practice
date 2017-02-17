package practice.task5.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import practice.task5.api.NewsTitles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component (name = "Lenta News")
@Service (value = NewsTitles.class)
@Property (name = "source", value = "lenta")
public class LentaNews implements NewsTitles {
    static final String lentaURL = "https://api.lenta.ru/lists/latest";
    
    @Override
    public String[] getTitles() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try {
            URL lenta = new URL("https://api.lenta.ru/lists/latest");
            BufferedReader urlReader = new BufferedReader(new InputStreamReader(lenta.openStream(), "UTF-8"));
            jsonObject = (JSONObject) parser.parse(urlReader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        JSONArray headlines = (JSONArray) jsonObject.get("headlines");
        List<String> titles = new ArrayList<>(headlines.size());
        for (Object obj : headlines) {
            titles.add((String) ((JSONObject) ((JSONObject) obj).get("info")).get("title"));
        }
        return titles.toArray(new String[]{});
    }
}