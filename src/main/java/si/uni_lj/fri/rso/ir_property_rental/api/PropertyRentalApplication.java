package si.uni_lj.fri.rso.ir_property_rental.api;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@RegisterService
@ApplicationPath("v1")
public class PropertyRentalApplication extends Application {
}
