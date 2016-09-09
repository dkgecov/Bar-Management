package bar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "findByPriceAndName", query = "SELECT i FROM Item i WHERE i.itemName = :name AND i.price = :price"),
		@NamedQuery(name = "getAllItems", query = "SELECT i FROM Item i")})
public class Item implements Serializable {

	private static final long serialVersionUID = 4654489222889729922L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String itemName;

	private String price;

	private String type;

	private String description;

	public Item() {
	}

	public Item(String itemName, String price, String type, String description) {
		super();
		this.itemName = itemName;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (itemName != null && !itemName.trim().isEmpty())
			result += "name: " + itemName;
		if (id != null)
			result += ", id: " + id;
		if (itemName != null && !itemName.trim().isEmpty())
			result += "name: " + itemName;
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