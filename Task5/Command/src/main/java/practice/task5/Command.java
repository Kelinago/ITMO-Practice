package practice.task5;

import org.apache.felix.scr.annotations.*;
import org.apache.felix.scr.annotations.Properties;
import practice.task5.api.NewsTitles;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Component (name = "NewsStats Command")
@Service (value = Command.class)
@Properties ({
        @Property (name = "osgi.command.scope", value = "news"),
        @Property (name = "osgi.command.function", value = "stats")
})
public class Command {
    @Reference (cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
            policy = ReferencePolicy.DYNAMIC, bind = "setService", unbind = "unsetService",
            referenceInterface = NewsTitles.class)
    private List<NewsTitles> services;
    
    String[] getTenMostFrequentWords(String[] titles) {
        HashMap<String, AtomicInteger> map = new HashMap<>();
        if (titles != null) {
            for (String str : titles) {
                String[] splitted = str.split("[ ,\"\'«»]");
                for (String word : splitted) {
                    if (!word.isEmpty() && !word.matches("\\d*.*\\d")) {
                        checkWord(map, word.toLowerCase());
                    }
                }
            }
            String[] result = new String[10];
            Map.Entry<String, AtomicInteger> currentMax;
            for (int i = 0; i < result.length; i++) {
                currentMax = Collections.max(map.entrySet(), Comparator.comparingInt(x -> x.getValue().get()));
                result[i] = currentMax.getKey();
                map.remove(currentMax.getKey());
            }
            return result;
        } else {
            return null;
        }
    }
    
    private void checkWord(Map<String, AtomicInteger> hashMap, String word) {
        if (!hashMap.containsKey(word)) {
            hashMap.put(word, new AtomicInteger(1));
        } else {
            hashMap.get(word).incrementAndGet();
        }
    }
    
    public void setService(NewsTitles service) {
        if (services == null) {
            services = new ArrayList<NewsTitles>();
        }
        services.add(service);
    }
    
    public void unsetService(NewsTitles service) {
        services.remove(service);
    }
    
    /* TODO: Realise user-friendly interface */
    public void stats() {
        ArrayList<String> words = new ArrayList<>();
        for (NewsTitles service : services) {
            Collections.addAll(words, service.getTitles());
        }
        for (String word : getTenMostFrequentWords((String[]) words.toArray())) {
            System.out.println(word);
        }
    }
    
    /* TODO: Realise stats() with param which define a service */
    public void stats(String[] params) {
        services.get(0).getTitles();
    }
}
