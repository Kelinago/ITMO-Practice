package org.kelinago.osgi.provider;

import org.kelinago.osgi.provider.able.Provider;
import org.kelinago.osgi.provider.impl.ProviderImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * ProviderImpl service activator
 *
 * @author Maxim Pyshnyy
 * @version 1.0
 */
public class ServiceActivator implements BundleActivator {
    /**
     * Service registration reference
     */
    private ServiceRegistration registration;
    
    /**
     * Service registration
     *
     * @param bundleContext
     * @throws Exception
     */
    public void start(BundleContext bundleContext) throws Exception {
        registration = bundleContext.registerService(Provider.class.getName(), new ProviderImpl(), null);
    }
    
    /**
     * Unregister service
     *
     * @param bundleContext
     * @throws Exception
     */
    public void stop(BundleContext bundleContext) throws Exception {
        registration.unregister();
    }
}
