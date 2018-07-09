package coupons.dao;

import coupons.beans.Coupon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CouponRepository extends PagingAndSortingRepository<Coupon,Long> {
	 
}