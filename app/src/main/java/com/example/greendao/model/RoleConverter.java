package com.example.greendao.model;

import org.greenrobot.greendao.converter.PropertyConverter;

public class RoleConverter implements PropertyConverter<Role,Integer> {
    @Override
    public Role convertToEntityProperty(Integer databaseValue) {
        for (Role role:Role.values()){
            if (role.getI()==databaseValue)
                return role;
        }
        return null;
    }

    @Override
    public Integer convertToDatabaseValue(Role entityProperty) {
        return entityProperty.getI();
    }
}
