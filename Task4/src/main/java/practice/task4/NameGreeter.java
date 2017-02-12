package practice.task4;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import practice.task4.able.Greeter;

/**
 * NameGreeter class is implementation of a simple GoGo shell command
 *
 * @author Maxim Pyshnyy
 * @version 1.0 12 Feb 2016
 */
@Component (name = "Name Greeter Command")
@Service (value = Greeter.class)
@Properties ({
        @Property (name = "osgi.command.scope", value = "practice"),
        @Property (name = "osgi.command.function", value = "hello")
})
public class NameGreeter implements Greeter {
    /**
     * Simple greeting
     */
    public void hello() {
        System.out.println("Hello, unnamed");
    }
    
    /**
     * Simple greeting a person
     *
     * @param name Name of a person
     */
    public void hello(String name) {
        System.out.println("Hello, " + name);
    }
}
