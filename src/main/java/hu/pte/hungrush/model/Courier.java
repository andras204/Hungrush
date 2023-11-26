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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "courier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courier.findAll", query = "SELECT c FROM Courier c"),
    @NamedQuery(name = "Courier.findById", query = "SELECT c FROM Courier c WHERE c.id = :id"),
    @NamedQuery(name = "Courier.findByName", query = "SELECT c FROM Courier c WHERE c.name = :name"),
    @NamedQuery(name = "Courier.findByMeansOfTransport", query = "SELECT c FROM Courier c WHERE c.meansOfTransport = :meansOfTransport"),
    @NamedQuery(name = "Courier.findByPhoneNumber", query = "SELECT c FROM Courier c WHERE c.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Courier.findByStatus", query = "SELECT c FROM Courier c WHERE c.status = :status")})
public class Courier implements Serializable {
    
        public enum MeansOfTransport {
            
            bicycle,
            car,
            moped;
        }
        
        public enum Status {
            
            offline,
            idle,
            delivering;
        }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    
    @Column(columnDefinition = "ENUM('bicycle','car','moped')")
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    //@Column(name = "means_of_transport")
    private MeansOfTransport meansOfTransport;
    
    
    @Basic(optional = false)
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(columnDefinition = "ENUM('offline','idle','delivering')")
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    //@Column(name = "status")
    private Status status;

    public Courier() {
    }

    public Courier(Integer id) {
        this.id = id;
    }

    public Courier(Integer id, String name, MeansOfTransport meansOfTransport, String phoneNumber, Status status) {
        this.id = id;
        this.name = name;
        this.meansOfTransport = meansOfTransport;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeansOfTransport getMeansOfTransport() {
        return meansOfTransport;
    }

    public void setMeansOfTransport(MeansOfTransport meansOfTransport) {
        this.meansOfTransport = meansOfTransport;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
        if (!(object instanceof Courier)) {
            return false;
        }
        Courier other = (Courier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.pte.hungrush.models.Courier[ id=" + id + " ]";
    }
    
}
