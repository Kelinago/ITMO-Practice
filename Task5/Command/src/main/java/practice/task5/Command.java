package practice.task5;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import practice.task5.api.NewsTitles;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Command {
    private BundleContext bundleContext;
    private ServiceReference[] services;
    
    public Command(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }
    
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
    
    public void stats() {
        if (!loadServiceReferences(null)) {
            return;
        }
        int choice = -1;
        do {
            System.out.println("Available sources: ");
            int i = 1;
            for (ServiceReference source : services) {
                System.out.printf(" %d. %s (%s)\n",
                        i++,
                        source.getProperty("source"),
                        source.getProperty("sourceURL"));
            }
            System.out.printf(" %d. All sources\n", i);
            System.out.print("  Your choice number: ");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            if (!(services.length < --choice || choice < 0)) {
                break;
            }
            System.out.println("Source not registered");
        } while (true);
        if (choice == services.length) {
            stats(null);
        } else {
            stats((String) services[choice].getProperty("source"));
        }
    }
    
    public void stats(String param) {
        if (!loadServiceReferences(param)) {
            return;
        }
        
        ArrayList<String> words = new ArrayList<>();
        for (ServiceReference service : services) {
            String[] titles = ((NewsTitles) bundleContext.getService(service)).getTitles();
            Collections.addAll(words, titles);
        }
        if (!words.isEmpty()) {
            System.out.println("Top-10 most frequent words in news titles (based on "
                    + (services.length > 1 ? "all sources API" : services[0].getProperty("source") + " API")
                    + "): ");
            int i = 1;
            for (String word : getTenMostFrequentWords(words.toArray(new String[]{}))) {
                System.out.printf(" %2d. %s\n", i++, word);
            }
        } else {
            System.out.println("There is no data");
        }
    }
    
    private boolean loadServiceReferences(String param) {
        try {
            String filterStr = param == null ? param : "(|(source=" + param + ")(sourceURL=" + param + "))";
            services = bundleContext.getServiceReferences(NewsTitles.class.getName(), filterStr);
        } catch (InvalidSyntaxException e) {
            System.out.println("Invalid filter string syntax");
            return false;
        }
        if (services == null) {
            System.out.println("There is no active NewsStats services" +
                    (param == null ? "" : " with source " + param));
            return false;
        }
        return true;
    }
}
