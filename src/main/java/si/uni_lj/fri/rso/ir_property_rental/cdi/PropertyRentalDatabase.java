package si.uni_lj.fri.rso.ir_property_rental.cdi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import si.uni_lj.fri.rso.ir_property_rental.models.PropertyRental;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
public class PropertyRentalDatabase {
    private Logger log = LogManager.getLogger(PropertyRentalDatabase.class.getName());

    @Inject
    private EntityManager em;

    private HttpClient httpClient = HttpClientBuilder.create().build();
    private ObjectMapper objectMapper = new ObjectMapper();


    public List<PropertyRental> getProperties() {
        TypedQuery<PropertyRental> query = em.createNamedQuery("PropertyRental.getAll", PropertyRental.class);
        return query.getResultList();
    }

    public List<PropertyRental> getPropertiesFilter(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();
        return JPAUtils.queryEntities(em, PropertyRental.class, queryParameters);
    }

    public PropertyRental getPropertyRental(String propertyRentalId, boolean includeExtended) {
        PropertyRental propertyRental = em.find(PropertyRental.class, propertyRentalId);
        if (propertyRental == null) {
            throw new NotFoundException();
        }
        if (includeExtended) {
            // nothing here yet
        }
        return propertyRental;
    }

    public PropertyRental createPropertyRental(PropertyRental propertyRental) {
        try {
            beginTx();
            em.persist(propertyRental);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return propertyRental;
    }

    public PropertyRental putPropertyRental(String propertyRentalId, PropertyRental propertyRental) {
        PropertyRental p = em.find(PropertyRental.class, propertyRentalId);
        if (p == null) {
            return null;
        }
        try {
            beginTx();
            propertyRental.setId(p.getId());
            propertyRental = em.merge(propertyRental);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return propertyRental;
    }

    public boolean deletePropertyRental(String propertyRentalId) {
        PropertyRental p = em.find(PropertyRental.class, propertyRentalId);
        if (p != null) {
            try {
                beginTx();
                em.remove(p);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else {
            return false;
        }
        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
