package bar.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
@Table(name = "ORDERS")
@NamedQueries({
        @NamedQuery(name = "findByStatusAndExecutor", query = "SELECT o FROM Order o WHERE o.executor = :executor AND o.status = :status"),
        @NamedQuery(name = "getAllOrders", query = "SELECT o FROM Order o")})
public class Order implements Serializable {

	private static final long serialVersionUID = 735934458877201921L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User executor;

    @Enumerated(EnumType.STRING)
    private String status;
    
    @Temporal(TemporalType.DATE)
    private Date dateOfOrder;

    @ManyToMany
    private List<Item> ItemsInOrder = new ArrayList<>();
    
    public Order() {
    }

    public Order(String status, Date dateOfOrder) {
        super();
        this.status = status;
        this.dateOfOrder = dateOfOrder;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public User getexecutor() {
        return executor;
    }

    public void setexecutor(User executor) {
        this.executor = executor;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public Date getdateOfOrder() {
        return dateOfOrder;
    }

    public void setdateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (id != null)
            result += ", id: " + id;
        if (executor != null)
            result += "executor: " + executor.getUserName();
        if (status != null && !status.trim().isEmpty())
            result += ", status: " + status;
        if (dateOfOrder != null)
            result += ", dateOfOrder: " + dateOfOrder.toString();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Order)) {
            return false;
        }
        Order other = (Order) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}