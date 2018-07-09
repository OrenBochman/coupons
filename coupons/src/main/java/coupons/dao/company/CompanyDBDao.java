package coupons.dao.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import coupons.ExceptionType;
import coupons.beans.Company;
import coupons.beans.Coupon;
import coupons.utils.ApplicationException;
import coupons.utils.ConnectionPool;

public class CompanyDBDao implements ICompanyDao  {

	private ConnectionPool pool;
	private Collection<Company> companies;
	private Collection<Coupon> coupons;

	/**
	 * default constructor
	 * @throws SQLException
	 */
	CompanyDBDao() throws ApplicationException, SQLException {
		this.pool = ConnectionPool.getInstance();
		this.setCompanies(getAllCompanies());
		this.coupons  =new ArrayList<>(); 
	}

	@Override 
	public Collection<Coupon> getCoupons(){
		return this.coupons;
	};

	public Collection<Company> getCompanies() {
		return companies;
	}

	/**
	 * 
	 * @param companies
	 */
	public void setCompanies(Collection<Company> companies) {
		this.companies = companies;
	}

	/**
	 * 
	 */
	@Override 
	public boolean login(String compName, String password) {
		return compName.equals("admin")&&
				password.equals("1234");
	}

	enum    CRUD {CREATE,READ_BY_ID,UPDATE,DELETE,READ_ALL};

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
			sql = "INSERT INTO company (COMP_NAME, EMAIL) VALUES (?, ?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, ((Company) params[0]).getCompName());
			ps.setString(2, ((Company) params[0]).getEmail());
			break;
		case DELETE:
			sql = "DELETE FROM company WHERE COMP_NAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ((Company) params[0]).getCompName());
			break;
		case READ_BY_ID:
			sql = "SELECT * FROM company WHERE ID = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, (Long)params[0]);
			break;
		case READ_ALL:
			sql = "SELECT * FROM company  ";
			ps = con.prepareStatement(sql);
			break;
		case UPDATE:
			sql = "UPDATE company SET (EMAIL=?,COMP_NAME=?) WHERE id=?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, ((Company) params[0]).getCompName());
			ps.setString(2, ((Company) params[0]).getEmail());
			ps.setLong(  3, ((Company) params[0]).getId());
			break;
		}
		return ps;	
	}

	/////////////////////////// CRUD ///////////////////////////////////////////

	public static Company createCompanyDetails(ResultSet result) throws SQLException {
		Company company = new Company();
		company.setCompName(result.getString("CompName"));
		company.setEmail(result.getString("email"));
		company.setId(result.getLong("Id"));
		return company;
	}
	
	@Override 
	public Collection<Company> getAllCompanies() throws ApplicationException{
		Collection<Company> companies=new ArrayList<Company>();
		try(Connection connection=pool.getConnection();	
			PreparedStatement ps = statementBuilder(CRUD.READ_BY_ID, connection);
			ResultSet result = ps.executeQuery();	) {
			while (result.next()){
				companies.add(createCompanyDetails(result));
			}
		} catch (SQLException e) {
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not get companies ");
		}
		return companies;
	}

	public Company getCompany(long id) throws ApplicationException {
		Company company=null;
		try(Connection connection=pool.getConnection();	
				PreparedStatement ps = statementBuilder(CRUD.READ_BY_ID, connection, id);
				ResultSet result = ps.executeQuery();	) {
			while (result.next()){
				company = createCompanyDetails(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not get company " + id);
		}
		return company;
	}

	@Override
	public void createCompany(Company company) throws  ApplicationException {
		try(Connection connection=pool.getConnection();	
				PreparedStatement ps = statementBuilder(CRUD.CREATE, connection, company)) {
			ps.executeUpdate(); //executing the command	
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not create company " + company.toString());
		}
	}

	@Override
	public void removeCompany(Company company) throws ApplicationException {
		try(Connection connection=pool.getConnection();	
				PreparedStatement ps = statementBuilder(CRUD.DELETE, connection, company)) {
			ps.executeUpdate(); //executing the command
		} catch (Exception e) { 
			//we could not delete the company so we should notify our caller 
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not delete company: " + company.toString());
		}		
	}

	@Override
	public void updateCompany(Company company) throws ApplicationException {

		try(Connection connection=pool.getConnection();	//get a connection from the pool
				PreparedStatement ps = statementBuilder(CRUD.UPDATE, connection, company) ){
			ps.executeUpdate();
		} catch (Exception e) { //handle or notifies a level above.
			//if we could not update company we should notify caller 
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not update company " + company.toString());
		}
	}
}


