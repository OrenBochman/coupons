package coupons.beans;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import kotlin.internal.ContractsDsl;

/**
 * @author Oren Bochman
 *
 */
@Entity(name="User")
@Table(name="user")
@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE LOWER(u.name) = LOWER(?)")
public class User {

	/**
	 * Default constructor.
	 * 
	 */
	public User() {
		super();
	}
	/**
	 * Convenience constructor.
	 * 
	 * @param id
	 * @param name
	 */
	public User(long id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public User(long id, String email, String name, String password, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	public User(String email, String name, String password, Role role) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name = "email", unique = false, nullable = false)
	private String email;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "role", nullable = false)
	@Convert(converter = RoleConverter.class)
	private Role role;
	
	public Role getRole() {
		return role;
	}

	public User setRole(Role role) {
		this.role = role; 
		return this;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		return true;
	}
	
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + "]";
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public long getId() {
		return id;
	}

	public User setId(long id) {
		this.id = id;
		return this;
	}



	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password; 
		return this;
	}
}