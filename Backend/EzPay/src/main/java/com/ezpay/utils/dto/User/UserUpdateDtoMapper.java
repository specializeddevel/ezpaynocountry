package com.ezpay.utils.dto.User;

import com.ezpay.model.entity.User;

public class UserUpdateDtoMapper {
    public UserUpdateDto apply(User user) {
        return new UserUpdateDto(
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getDni(),
                user.getBirthDate(),
                user.getGender().toString()
        );
    }
}
