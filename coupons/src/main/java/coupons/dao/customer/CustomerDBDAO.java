package coupons.dao.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import coupons.ExceptionType;
import coupons.beans.Customer;
import coupons.utils.ApplicationException;
import coupons.utils.ConnectionPool;

public class CustomerDBDAO implements ICustomerDao {

	enum    CRUD {CREATE,READ_BY_ID,UPDATE,DELETE,READ_ALL};
	private ConnectionPool pool;
	private Collection<Customer> customers;
	
	public CustomerDBDAO() throws ApplicationException, SQLException{
		this.pool=ConnectionPool.getInstance();
		this.customers=new ArrayList<Customer>();
		//setCoupons(getAllCoupon());
	}
	
	/**
	 * 
	 * @param crud - command type
	 * @param con - a connection
	 * @param params - map of parameter names to values
	 * @return a PreparedStatement
	 * @throws SQLException
	 */
	public PreparedStatement statementBuilder(CRUD crud,Connection con,Object ... params) 
			throws SQLException {

		String sql;
		PreparedStatement ps = null;
		switch(crud) {

		case CREATE:
			sql = "INSERT INTO Customer (CUST_NAME) VALUES (?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, ((Customer) params[0]).getCustName());
			break;
		case DELETE:
			sql = "DELETE FROM Customer WHERE CUST_NAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ((Customer) params[0]).getCustName());
			break;
		case READ_BY_ID:
			sql = "SELECT * FROM Customer WHERE ID = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, (Long)params[0]);
			break;
		case READ_ALL:
			sql = "SELECT * FROM Customer  ";
			ps = con.prepareStatement(sql);
			break;
		case UPDATE:
			sql = "UPDATE company SET (CUST_NAME=?) WHERE id=?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, ((Customer) params[0]).getCustName());
			ps.setLong(  2, ((Customer) params[0]).getId());
			break;
		}
		return ps;	
	}

	/////////////////////////// CRUD ///////////////////////////////////////////

	@Override
	public void createCustomer(Customer customer) throws ApplicationException {
		try(Connection connection=pool.getConnection();	
				PreparedStatement ps = statementBuilder(CRUD.CREATE, connection, customer)) {
			ps.executeUpdate(); //executing the command	
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not create customer " + customer.toString());
		}		
	}

	@Override
	public void removeCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getCustomer(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Customer> getAllCustomers() {
		return this.customers;
	}

	@Override
	public boolean login(String custName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
