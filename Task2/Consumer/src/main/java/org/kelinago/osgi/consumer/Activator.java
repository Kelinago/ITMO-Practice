package org.kelinago.osgi.consumer;

import org.kelinago.osgi.provider.able.Provider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Activator c
 */
public class Activator implements BundleActivator {
    private Consumer consumer;
    
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference reference = bundleContext.getServiceReference(Provider.class.getName());
        consumer = new Consumer((Provider) bundleContext.getService(reference));
    }
    
    public void stop(BundleContext bundleContext) throws Exception {
    }
}
