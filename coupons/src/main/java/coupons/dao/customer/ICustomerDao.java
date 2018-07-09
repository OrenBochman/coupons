package coupons.dao.customer;

import java.util.Collection;
import coupons.beans.Customer;
import coupons.utils.ApplicationException;

public interface ICustomerDao {

	public void createCustomer(Customer customer) throws ApplicationException;

	public void removeCustomer(Customer customer) throws ApplicationException;

	public void updateCustomer(Customer customer) throws ApplicationException;

	public void deleteCustomer(long id) throws ApplicationException;

	public Collection<Customer> getAllCustomers() throws ApplicationException;

	public Customer getCustomer(long id);

	Customer findByName(String name);

	public boolean login(String custName, String password) throws ApplicationException;;
	
	// public Collection<Coupon> getCoupons()throws ApplicationException;

}
