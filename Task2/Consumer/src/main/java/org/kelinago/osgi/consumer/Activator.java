package org.kelinago.osgi.consumer;

import org.kelinago.osgi.provider.able.Provider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Consumer service activator
 *
 * @author Maxim Pyshnyy
 * @version 1.0
 */
public class Activator implements BundleActivator {
    /**
     * Consumer instance
     */
    private Consumer consumer;
    
    /**
     * Initialization of a consumer instance
     *
     * @param bundleContext
     * @throws Exception
     */
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference reference = bundleContext.getServiceReference(Provider.class.getName());
        consumer = new Consumer((Provider) bundleContext.getService(reference));
    }
    
    /**
     * Stopping a bundle
     *
     * @param bundleContext
     * @throws Exception
     */
    public void stop(BundleContext bundleContext) throws Exception {
    }
}
