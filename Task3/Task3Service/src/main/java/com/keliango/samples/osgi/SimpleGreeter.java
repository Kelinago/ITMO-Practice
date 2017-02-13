package com.keliango.samples.osgi;

import com.keliango.samples.osgi.able.Greeter;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

/**
 * Simple greeter service
 *
 * @author Maxim Pyshnyy
 * @version 1.0
 */
@Component (name = "SimpleGreeter")
@Service (value = com.keliango.samples.osgi.able.Greeter.class)
public class SimpleGreeter implements Greeter {
    /**
     * Simple greeting
     */
    public void sayHello() {
        System.out.println("Hello OSGi World!");
    }
}