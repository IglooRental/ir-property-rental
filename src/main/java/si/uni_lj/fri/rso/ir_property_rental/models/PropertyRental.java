package si.uni_lj.fri.rso.ir_property_rental.models;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;

@Entity(name = "propertyrentals")
@NamedQueries(value = {
        @NamedQuery(name = "PropertyRental.getAll", query = "SELECT p FROM propertyrentals p")
})
@UuidGenerator(name = "idGenerator")
public class PropertyRental {
    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "property_id")
    private String propertyId;

    @Column(name = "renter_id")
    private String renterId;

    private String date;

    private Integer duration;

    public PropertyRental(String id, String propertyId, String renterId, String date, Integer duration) {
        this.id = id;
        this.propertyId = propertyId;
        this.renterId = renterId;
        this.date = date;
        this.duration = duration;
    }

    public PropertyRental() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getRenterId() {
        return renterId;
    }

    public void setRenterId(String renterId) {
        this.renterId = renterId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
