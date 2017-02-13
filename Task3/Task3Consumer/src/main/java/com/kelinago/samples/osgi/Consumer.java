package com.kelinago.samples.osgi;

import com.keliango.samples.osgi.able.Greeter;
import org.apache.felix.scr.annotations.*;

/**
 * Greeter service consumer
 */
@Component (name = "Consumer Component")
public class Consumer {
    /**
     * Greeter service reference
     */
    @Reference
    private Greeter greeter;
    
    /**
     * Runs when consumer start
     */
    @Activate
    public void activate() {
        this.greeter.sayHello();
    }
}
