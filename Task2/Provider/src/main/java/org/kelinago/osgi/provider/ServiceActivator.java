package org.kelinago.osgi.provider;

import org.kelinago.osgi.provider.able.Provider;
import org.kelinago.osgi.provider.impl.ProviderImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Activator
 */
public class ServiceActivator implements BundleActivator {
    public void start(BundleContext bundleContext) throws Exception {
        bundleContext.registerService(Provider.class.getName(), new ProviderImpl(), null);
    }
    
    public void stop(BundleContext bundleContext) throws Exception {
        
    }
}
