package si.uni_lj.fri.rso.ir_property_rental.api;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Metered;
import si.uni_lj.fri.rso.ir_property_rental.cdi.PropertyRentalDatabase;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("propertyrentals")
@Log
public class PropertyRentalResource {
    @Inject
    private PropertyRentalDatabase propertyRentalDatabase;

    @Context
    protected UriInfo uriInfo;

    private Logger log = Logger.getLogger(PropertyRentalResource.class.getName());

    @GET
    @Metered
    public Response dummy() {
        if (ConfigurationUtil.getInstance().getBoolean("rest-config.endpoint-enabled").orElse(false)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("{\"reason\": \"Endpoint disabled.\"}").build();
        }
    }
}
