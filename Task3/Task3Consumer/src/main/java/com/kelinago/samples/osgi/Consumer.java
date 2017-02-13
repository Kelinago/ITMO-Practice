package com.kelinago.samples.osgi;

import com.keliango.samples.osgi.able.Greeter;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

/**
 * Greeter service consumer
 */
@Component (name = "Consumer Component", immediate = true)
@Service (value = Consumer.class)
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
