package coupons.beans;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * customer java bean.
 * @author Oren Bochman
 *
 */
@Entity(name="Customer")
@Table(name="customer")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6726112849756329533L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	

	@Column(name="customer_name")
	private String customerName;
	
	/**
	 * @return the custName
	 */
	public String getCustName() {
		return customerName;
	}
	/**
	 * @param custName the custName to set
	 */
	public void setCustName(final String custName) {
		this.customerName = custName;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (id != other.id)
			return false;
		
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [custName=" + customerName + ", id=" + id + "]";
	}

	/**
	 * Serialization example
	 * @param args
	 */
	public static void main(String[] args) {
		String filename = "time.ser";
		Customer c = new Customer();
		c.setCustName("Oren Bochman");
		c.setId(123l);


		// save the object to file
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(c);

			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// read the object from file
		// save the object to file
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			c = (Customer) in.readObject();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(c);
	}
}

