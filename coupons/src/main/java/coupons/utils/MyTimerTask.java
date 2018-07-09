package coupons.utils;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import coupons.dao.coupon.CouponDBDao;

public class MyTimerTask extends TimerTask {

	@Override
    public void run() {
		//TODO: log maintenance task start
		long now = Calendar.getInstance().getTimeInMillis();
		Date todayDate = new Date(now);
		try {
			CouponDBDao.removeOldCoupons(todayDate);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
    }
    
    
}