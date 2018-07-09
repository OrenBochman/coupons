package coupons.dao.company;

import java.sql.SQLException;
import java.util.Collection;

import coupons.beans.Company;
import coupons.beans.Coupon;
import coupons.utils.ApplicationException;

public interface ICompanyDao {

	/**
	 * 
	 * @param company
	 * @throws SQLException
	 * @throws ApplicationException 
	 */
	public void createCompany(Company company) throws ApplicationException;
	/**
	 * 
	 * @param company
	 * @throws ApplicationException 
	 */
	public void  removeCompany(Company company) throws ApplicationException;
	/**
	 * 
	 * @param company
	 * @throws ApplicationException 
	 */
	public void updateCompany(Company company) throws ApplicationException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ApplicationException 
	 */
	public Company getCompany(long id) throws ApplicationException;
	/**
	 * 
	 * @return
	 * @throws ApplicationException 
	 */
	public Collection<Company> getAllCompanies() throws ApplicationException;
	/**
	 * 
	 * @return
	 */
	public Collection<Coupon> getCoupons();
	/**
	 * 
	 * @param compName
	 * @param password
	 * @return
	 */
	public boolean login(String compName,String password);
}



