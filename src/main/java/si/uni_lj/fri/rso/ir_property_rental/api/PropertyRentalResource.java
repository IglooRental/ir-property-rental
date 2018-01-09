package si.uni_lj.fri.rso.ir_property_rental.api;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Metered;
import si.uni_lj.fri.rso.ir_property_rental.cdi.PropertyRentalDatabase;
import si.uni_lj.fri.rso.ir_property_rental.models.PropertyRental;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
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
    public Response getAllProperties() {
        if (ConfigurationUtil.getInstance().getBoolean("rest-config.endpoint-enabled").orElse(false)) {
            List<PropertyRental> properties = propertyRentalDatabase.getProperties();
            return Response.ok(properties).build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("{\"reason\": \"Endpoint disabled.\"}").build();
        }
    }

    @GET
    @Path("/filtered")
    public Response getPropertiesFiltered() {
        if (ConfigurationUtil.getInstance().getBoolean("rest-config.endpoint-enabled").orElse(false)) {
            List<PropertyRental> customers = propertyRentalDatabase.getPropertiesFilter(uriInfo);
            return Response.status(Response.Status.OK).entity(customers).build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("{\"reason\": \"Endpoint disabled.\"}").build();
        }
    }

    @GET
    @Metered
    @Path("/{propertyRentalId}")
    public Response getPropertyRental(@PathParam("propertyRentalId") String propertyRentalId, @DefaultValue("true") @QueryParam("includeExtended") boolean includeExtended) {
        if (ConfigurationUtil.getInstance().getBoolean("rest-config.endpoint-enabled").orElse(false)) {
            PropertyRental propertyRental = propertyRentalDatabase.getPropertyRental(propertyRentalId, includeExtended);
            return propertyRental != null
                    ? Response.ok(propertyRental).build()
                    : Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("{\"reason\": \"Endpoint disabled.\"}").build();
        }
    }

    @POST
    @Metered
    public Response addNewPropertyRental(PropertyRental property) {
        if (ConfigurationUtil.getInstance().getBoolean("rest-config.endpoint-enabled").orElse(false)) {
            propertyRentalDatabase.createPropertyRental(property);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("{\"reason\": \"Endpoint disabled.\"}").build();
        }
    }

    @DELETE
    @Metered
    @Path("/{propertyRentalId}")
    public Response deletePropertyRental(@PathParam("propertyRentalId") String propertyRentalId) {
        if (ConfigurationUtil.getInstance().getBoolean("rest-config.endpoint-enabled").orElse(false)) {
            propertyRentalDatabase.deletePropertyRental(propertyRentalId);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("{\"reason\": \"Endpoint disabled.\"}").build();
        }
    }
}
