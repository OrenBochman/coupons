package coupons.beans;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role attribute) {
        switch (attribute) {
            case Admin:
                return 0;
            case Client :
                return 1;
            case Company:
                return 2;
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        switch (dbData) {
            case 0:
                return Role.Admin;
            case 1:
                return Role.Client;
            case 2:
                return Role.Company;
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
}