package coupons.dao.coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import coupons.ExceptionType;
import coupons.beans.Coupon;
import coupons.beans.CouponType;
import coupons.utils.ApplicationException;
import coupons.utils.ConnectionPool;

public class CouponDBDao implements ICouponDao {

	enum    CRUD {CREATE,READ_BY_ID,UPDATE,DELETE,READ_ALL, DELETE_BY_DATE};
	private ConnectionPool pool;
	private Collection<Coupon> coupons;
	
	public CouponDBDao() throws ApplicationException, SQLException{
		this.pool=ConnectionPool.getInstance();
		this.coupons=new ArrayList<Coupon>();
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
			sql = "INSERT INTO coupons (TITLE,START_DATA,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE) VALUES (?,?,?,?,?,?,?,?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, ((Coupon) params[0]).getTitle());
			ps.setDate(  2, ((Coupon) params[0]).getStartDate());
			ps.setDate(  3, ((Coupon) params[0]).getEndDate());
			ps.setInt(   4, ((Coupon) params[0]).getAmount());
			ps.setString(5, ((Coupon) params[0]).getTitle());
			ps.setString(6, ((Coupon) params[0]).getMessage());
			ps.setDouble(7, ((Coupon) params[0]).getPrice());
			ps.setString(8, ((Coupon) params[0]).getImage());
			break;
		case DELETE:
			sql = "DELETE FROM coupons WHERE ID=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, (long) params[0]);
			break;
		case DELETE_BY_DATE:
			sql = "DELETE FROM coupons WHERE DATE<?";
			ps = con.prepareStatement(sql);
			ps.setDate(1,(java.sql.Date) params[0]);
			break;			
		case READ_BY_ID:
			sql = "SELECT * FROM coupons WHERE ID=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, (long) params[0]);
			break;
		case READ_ALL:
			sql = "SELECT * FROM coupons  ";
			ps = con.prepareStatement(sql);
			break;
		case UPDATE:
			sql = "UPDATE coupons SET (TITLE=?,START_DATA=?,END_DATE=?,AMOUNT=?,TYPE=?,MESSAGE=?,PRICE=?,IMAGE=?) WHERE ID=?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, ((Coupon) params[0]).getTitle());
			ps.setDate(  2, ((Coupon) params[0]).getStartDate());
			ps.setDate(  3, ((Coupon) params[0]).getEndDate());
			ps.setInt(   4, ((Coupon) params[0]).getAmount());
			ps.setString(5, ((Coupon) params[0]).getTitle());
			ps.setString(6, ((Coupon) params[0]).getMessage());
			ps.setDouble(7, ((Coupon) params[0]).getPrice());
			ps.setString(8, ((Coupon) params[0]).getImage());
			ps.setLong(9, ((Coupon) params[0]).getId());
			break;
		}
		return ps;	
	}

	//////////////////////////// CRUD /////////////////////////////////////
	@Override
	public void createCoupon(Coupon coupon) throws ApplicationException {
		try(Connection connection=this.pool.getConnection();	
				PreparedStatement ps = statementBuilder(CRUD.CREATE, connection, coupon)) {
			ps.executeUpdate(); //executing the command	
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not create coupon " + coupon.toString());
		}
	}

	@Override
	public Coupon getCoupon(final long id) throws ApplicationException {
		Coupon coupon=null;
		try(Connection connection=pool.getConnection();	
				PreparedStatement ps = statementBuilder(CRUD.READ_BY_ID, connection, id);
				ResultSet result = ps.executeQuery() ) {
			while (result.next()){
				coupon = new Coupon();
				coupon.setId(result.getLong("Id"));
				coupon.setTitle(result.getString("TITLE"));
				coupon.setStartDate(result.getDate("START_DATE"));
				coupon.setEndDate(result.getDate("END_DATE"));
				coupon.setAmount(result.getInt("AMOUNT"));
				coupon.setCouponType(CouponType.valueOf(result.getString("TYPE")));
				coupon.setMessage(result.getString("MESSAGE"));
				coupon.setPrice(result.getDouble("PRICE"));
				coupon.setImage(result.getString("IMAGE"));
			}
		} catch (SQLException e) {
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not get company " + id);
		}
		return coupon;
	}

	@Override
	public Collection<Coupon> getAllCoupon() throws ApplicationException {
		Collection<Coupon> coupons=new ArrayList<>();
		try(Connection connection=pool.getConnection();	
				PreparedStatement ps = statementBuilder(CRUD.READ_ALL, connection);
				ResultSet result = ps.executeQuery() ) {
			while (result.next()){
				Coupon coupon = new Coupon();
				coupon.setId(result.getLong("Id"));
				coupon.setTitle(result.getString("TITLE"));
				coupon.setStartDate(result.getDate("START_DATE"));
				coupon.setEndDate(result.getDate("END_DATE"));
				coupon.setAmount(result.getInt("AMOUNT"));
				coupon.setCouponType(CouponType.valueOf(result.getString("TYPE")));
				coupon.setMessage(result.getString("MESSAGE"));
				coupon.setPrice(result.getDouble("PRICE"));
				coupon.setImage(result.getString("IMAGE"));
				coupons.add(coupon);
			}
		} catch (SQLException e) {
			throw new ApplicationException(ExceptionType.SQL, e, "could not get coupns ");
		}
		return coupons;	}

	@Override
	public void updateCoupon(final Coupon coupon) throws ApplicationException {
		try(Connection connection=pool.getConnection();	//get a connection from the pool
				PreparedStatement ps = statementBuilder(CRUD.UPDATE, connection, coupon) ){
			ps.executeUpdate();
		} catch (Exception e) { //handle or notifies a level above.
			//if we could not update company we should notify caller 
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not update coupon " + coupon.toString());
		}
	}

	@Override
	public void removeCoupon(final Coupon coupon) throws ApplicationException {
		try(Connection connection=pool.getConnection();	
				PreparedStatement ps = statementBuilder(CRUD.DELETE, connection, coupon.getId())) {
			ps.executeUpdate(); //executing the command
		} catch (Exception e) { 
			//we could not delete the coupon so we should notify our caller 
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not delete coupon: " + coupon.toString());
		}
	}
	
	@Override
	public void removeCouponByDate(final Date date) throws ApplicationException {
		try(Connection connection=pool.getConnection();	
				PreparedStatement ps = statementBuilder(CRUD.DELETE_BY_DATE, connection, date)) {
			ps.executeUpdate(); //executing the command
		} catch (Exception e) { 
			//we could not delete the coupon so we should notify our caller 
			throw new ApplicationException(ExceptionType.SQL, e, 
					"could not delete coupon by date: " + date.toString());
		}
	}

	/**
	 * remove old coupons by date
	 * @param todayDate
	 * @throws ApplicationException
	 */
	public static void removeOldCoupons(Date todayDate) throws ApplicationException {
		//TODO: add static support for this:
		//removeCouponByDate(todayDate);

	}

	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(final Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

}
