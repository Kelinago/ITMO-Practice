package org.kelinago.osgi.consumer;

import org.kelinago.osgi.provider.able.Provider;

/**
 * Consumer class emulates consuming Provider service
 *
 * @author Maxim Pyshnyy
 * @version 1.0
 */
public class Consumer {
    /**
     * Reference to a registered Provider service
     */
    private final Provider provider;
    
    /**
     * Constructor with reference initialization
     *
     * @param pr
     */
    public Consumer(Provider pr) {
        provider = pr;
        provider.sayHello();
    }
}
