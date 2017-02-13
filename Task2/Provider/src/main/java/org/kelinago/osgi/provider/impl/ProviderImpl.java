package org.kelinago.osgi.provider.impl;

import org.kelinago.osgi.provider.able.Provider;

/**
 * Simple Provider implementation
 *
 * @author Maxim Pyshnyy
 * @version 1.0
 */
public class ProviderImpl implements Provider {
    /**
     * Simple greeting
     */
    public void sayHello() {
        System.out.println("Hello OSGi World!");
    }
}
