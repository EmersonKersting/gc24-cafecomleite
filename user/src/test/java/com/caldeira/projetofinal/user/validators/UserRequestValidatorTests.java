package com.caldeira.projetofinal.user.validators;

import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import static org.junit.jupiter.api.Assertions.*;

public class UserRequestValidatorTests {

    private final UserRequestValidator validator = new UserRequestValidator();

    @Test
    void shouldNotThrowExceptionForValidUserRequest() {
        UserRequestModel validModel = new UserRequestModel();
        validModel.setFirstName("João");
        validModel.setLastName("Victor");

        assertDoesNotThrow(() -> validator.validate(validModel));
    }

    @Test
    void shouldThrowExceptionWhenFirstNameIsNull() {
        UserRequestModel invalidModel = new UserRequestModel();
        invalidModel.setFirstName(null);
        invalidModel.setLastName("Victor");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(invalidModel));
        assertEquals("First name cannot be null or less than 3 characters long", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenFirstNameIsTooShort() {
        UserRequestModel invalidModel = new UserRequestModel();
        invalidModel.setFirstName("Jo");
        invalidModel.setLastName("Victor");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(invalidModel));
        assertEquals("First name cannot be null or less than 3 characters long", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsNull() {
        UserRequestModel invalidModel = new UserRequestModel();
        invalidModel.setFirstName("João");
        invalidModel.setLastName(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(invalidModel));
        assertEquals("Last name cannot be null or less than 3 characters long", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsTooShort() {
        UserRequestModel invalidModel = new UserRequestModel();
        invalidModel.setFirstName("João");
        invalidModel.setLastName("Vi");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(invalidModel));
        assertEquals("Last name cannot be null or less than 3 characters long", exception.getMessage());
    }

}
