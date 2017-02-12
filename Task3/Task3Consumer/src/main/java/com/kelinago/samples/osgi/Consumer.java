package com.kelinago.samples.osgi;

import com.keliango.samples.osgi.able.IGreetable;
import org.apache.felix.scr.annotations.*;

@Component(name = "Consumer Component", immediate = true)
@Service(value = Consumer.class)
public class Consumer {
    @Reference(cardinality = ReferenceCardinality.OPTIONAL_UNARY, policy = ReferencePolicy.DYNAMIC)
    private volatile IGreetable greeter;

    @Activate
    public void activate(){
        this.greeter.sayHello();
    }
}
