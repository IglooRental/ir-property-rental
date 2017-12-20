package si.uni_lj.fri.rso.ir_property_rental.cdi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RequestScoped
public class PropertyRentalDatabase {
    // this also MUST be in a bean (see @requestscoped above) for it to work
    @Inject
    @DiscoverService("property-catalogue-service")
    private String basePath;

    private Logger log = LogManager.getLogger(PropertyRentalDatabase.class.getName());

    @Inject
    private EntityManager em;

    private HttpClient httpClient = HttpClientBuilder.create().build();
    private ObjectMapper objectMapper = new ObjectMapper();
}
