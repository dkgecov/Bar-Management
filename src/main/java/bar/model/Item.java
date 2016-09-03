package bar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@XmlRootElement
@Table(name = "ITEMS")
@NamedQueries({
        @NamedQuery(name = "findByPriceAndName", query = "SELECT b FROM Item b WHERE b.name = :name AND b.price = :price"),
        @NamedQuery(name = "getAllItems", query = "SELECT b FROM Item b")})
public class Item implements Serializable {

    private static final long serialVersionUID = -2929008106626811914L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String price;

    private String type;
    
    private String description;

    public Item() {
    }

    public Item(String name, String price, String type, String description) {
        super();
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (id != null)
            result += ", id: " + id;
        if (name != null && !name.trim().isEmpty())
            result += "name: " + name;
        if (price != null && !price.trim().isEmpty())
            result += ", price: " + price;
        if (type != null && !type.trim().isEmpty())
            result += ", type: " + type;
        result += ", description: " + description;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Item)) {
            return false;
        }
        Item other = (Item) obj;
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