package coupons.beans;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 
 */
@Converter
public class CouponTypeConverter implements AttributeConverter<CouponType, String>{
	
	@Override
	public String convertToDatabaseColumn(CouponType attribute) {
		switch (attribute) {
		case RESTURANT:
			return "RESTURANT";
		case ELECTRICITY:
			return "ELECTRICITY";
		case FOOD:
			return "FOOD";
		case HEALTH:
			return "HEALTH";
		case SPORTS:
			return "SPORTS";
		case CAMPING:
			return "CAMPING";
		case TRAVELING:
			return "TRAVELING";
		default:
            throw new IllegalArgumentException("Unknown" + attribute);
		}
	}

	@Override
	public CouponType convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "RESTURANT":
			return CouponType.RESTURANT;
		case "ELECTRICITY":
			return CouponType.ELECTRICITY;
		case "FOOD":
			return CouponType.FOOD ;
		case "HEALTH":
			return  CouponType.HEALTH;
		case "SPORTS":
			return  CouponType.SPORTS;
		case "CAMPING":
			return CouponType.CAMPING ;
		case "TRAVELING":
			return CouponType.TRAVELING ;
		default:
            throw new IllegalArgumentException("Unknown" + dbData);
		}
	}
}