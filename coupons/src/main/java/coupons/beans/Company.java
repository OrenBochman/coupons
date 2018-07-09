/**
 * 
 */
package coupons.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * company java bean
 * 
 * @author Oren Bochman
 *
 */
@Entity(name="Company")
@Table(name="company")
public class Company implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1116481862304412627L;

	@Column(name="company_name", nullable=false, unique=true)
	private String companyName;

	@Column(name="email", nullable=false, unique=false)
	private String email;

	/**
	 * 
	 */
	public Company() {}

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
		Company other = (Company) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)		
			return false;
		return true;
	}

	////////////////////////// getters and setters /////////////////////////////

	/**
	 * @return the compName
	 */
	public String getCompName() {
		return companyName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	/**
	 * @param compName the compName to set
	 */
	public void setCompName(String compName) {
		this.companyName = compName;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + companyName + ", email=" + email+ "]";
	}


}
