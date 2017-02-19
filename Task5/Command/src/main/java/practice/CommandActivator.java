package practice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import practice.task5.Command;

import java.util.Hashtable;

public class CommandActivator implements BundleActivator {
    private ServiceRegistration registration;
    
    @Override
    public void start(BundleContext context) throws Exception {
        Hashtable<String, String> props = new Hashtable<>();
        props.put("osgi.command.scope", "news");
        props.put("osgi.command.function", "stats");
        registration = context.registerService(Command.class.getName(), new Command(context), props);
    }
    
    @Override
    public void stop(BundleContext context) throws Exception {
        registration.unregister();
    }
}
