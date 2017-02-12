package com.keliango.samples.osgi;

import com.keliango.samples.osgi.able.IGreetable;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

@Component(name = "Greeter")
@Service(value = IGreetable.class)
public class Greeter implements IGreetable {
    public void sayHello() {
        System.out.println("Hello OSGi World!");
    }
}
