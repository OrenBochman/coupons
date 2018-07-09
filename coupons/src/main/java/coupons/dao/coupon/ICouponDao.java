package coupons.dao.coupon;

import java.util.Collection;
import java.util.Date;

import coupons.beans.Coupon;
import coupons.utils.ApplicationException;

public interface ICouponDao {

	/**
	 * create
	 * @param coupon
	 * @throws ApplicationException 
	 */
	public void createCoupon(Coupon coupon) throws ApplicationException;
	/**
	 * get a coupon.
	 * @param id
	 * @return the requested coupon
	 * @throws ApplicationException 
	 */
	public Coupon getCoupon(long id) throws ApplicationException;
	/**
	 * get all coupons.
	 * @return the requested coupon collection
	 * @throws ApplicationException 
	 */
	public Collection<Coupon> getAllCoupon() throws ApplicationException;
	/**
	 * update a coupon.
	 * @param theCoupon
	 * @throws CouponNotAvailableException - if theCoupon is not in the coupon collection
	 */
	public void updateCoupon(Coupon theCoupon) throws ApplicationException;
	/**
	 * delete a coupon.
	 * @param theCoupon 
	 * @throws CouponNotAvailableException - if theCoupon is not in the coupon collection
	 * @throws ApplicationException 
	 */
	public void removeCoupon(Coupon theCoupon) throws  ApplicationException;
	/**
	 * 
	 * @param date
	 * @throws ApplicationException
	 */
	void removeCouponByDate(Date date) throws ApplicationException;


}



