package hu.pte.hungrush.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "delivery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d"),
    @NamedQuery(name = "Delivery.findById", query = "SELECT d FROM Delivery d WHERE d.id = :id"),
    @NamedQuery(name = "Delivery.findByRestaurantId", query = "SELECT d FROM Delivery d WHERE d.restaurantId = :restaurantId"),
    @NamedQuery(name = "Delivery.findByCourierId", query = "SELECT d FROM Delivery d WHERE d.courierId = :courierId"),
    @NamedQuery(name = "Delivery.findByCustomerId", query = "SELECT d FROM Delivery d WHERE d.customerId = :customerId"),
    @NamedQuery(name = "Delivery.findByStatus", query = "SELECT d FROM Delivery d WHERE d.status = :status")})
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @ManyToOne
    private Restaurant restaurantId;
    @Basic(optional = false)
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    @ManyToOne
    private Courier courierId;
    @Basic(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName="id")
    @ManyToOne
    private Customer customerId;

    @Column(columnDefinition = "ENUM('offline','idle','delivering')")
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    //@Column(name = "status")
    private Courier.Status status;

    public Delivery() {
    }

    public Delivery(Integer id) {
        this.id = id;
    }

    public Delivery(Integer id, Restaurant restaurantId, Courier courierId, Customer customerId, Courier.Status status) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.courierId = courierId;
        this.customerId = customerId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Courier getCourierId() {
        return courierId;
    }

    public void setCourierId(Courier courierId) {
        this.courierId = courierId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Courier.Status getStatus() {
        return status;
    }

    public void setStatus(Courier.Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.pte.hungrush.models.Delivery[ id=" + id + " ]";
    }

}
