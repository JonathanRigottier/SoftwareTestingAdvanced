package com.sda.softwaretestingadvanced;

import com.sda.softwaretestingadvanced.exceptions.UserValidationException;
import com.sda.softwaretestingadvanced.models.User;
import com.sda.softwaretestingadvanced.models.UserType;
import com.sda.softwaretestingadvanced.utils.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testing exceptions
 *
 * @author Vinod John
 */
public class UserValidatorTest {
    // JUnit5 examples
    @Test
    public void givenUser_whenIsAdminUserCalled_shouldExpectException() {
        try {
            User user = new User();
            user.setFirstName("Jonathan");
            user.setLastName("Rigottier");
            user.setPassword("123456");
            user.setUserType(UserType.STANDARD);

            UserValidator userValidator = new UserValidator();
            userValidator.isAdminUser(user);

        } catch (UserValidationException userValidationException) {
            String expectedMessage = "User validation failed for user: Jonathan Rigottier, Error: User admin check failed";
            Assertions.assertEquals(expectedMessage, userValidationException.getLocalizedMessage());

        }
    }

    @Test
    public void givenUser_whenIsAdminCalledFunctionally_shouldExpectException() {
        User user = new User();
        user.setFirstName("Jonathan");
        user.setLastName("Rigottier");
        user.setPassword("123456");
        user.setUserType(UserType.STANDARD);

        UserValidationException userValidationException = Assertions.assertThrows(UserValidationException.class,
                () -> new UserValidator().isAdminUser(user));

        String expectedMessage = "User validation failed for user: Jonathan Rigottier, Error: User admin check failed";
        Assertions.assertEquals(expectedMessage, userValidationException.getLocalizedMessage());
    }

    //AssertJ examples
    @Test
    public void givenUserWithoutUserType_whenIsAdminUserCalled_shouldExpectNPE() {
        User user = new User();
        user.setFirstName("Jonathan");
        user.setLastName("Rigottier");
        user.setPassword("123456");

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new UserValidator().isAdminUser(user))
                .isExactlyInstanceOf(NullPointerException.class)
                .hasNoCause()
                .hasMessageContaining("null");
    }

    @Test
    public void givenUser_whenIsAdminUserCalled_shouldExpectExceptionAssert() {
        User user = new User();
        user.setFirstName("Jonathan");
        user.setLastName("Rigottier");
        user.setPassword("123456");
        user.setUserType(UserType.STANDARD);

        org.assertj.core.api.Assertions.assertThatExceptionOfType(UserValidationException.class)
                .isThrownBy(() -> new UserValidator().isAdminUser(user))
                .withNoCause()
                .withMessageContaining("User validation failed for user: Jonathan Rigottier, Error: User admin check failed");
    }

    @Test
    public void givenUserWithoutUserType_whenIsAdminCalled_shouldExpectException() {
        User user = new User();
        user.setFirstName("Jonathan");
        user.setLastName("Rigottier");
        user.setPassword("123456");

        Throwable exception = org.assertj.core.api.Assertions.catchThrowable(() -> new UserValidator().isAdminUser(user));
        Assertions.assertEquals("The user type is null for user: Jonathan Rigottier", exception.getLocalizedMessage());
    }

    @Test
    public void givenUser_whenIsAdminUserCalled_shouldExpectUserValidationException() {
        User user = new User();
        user.setFirstName("Jonathan");
        user.setLastName("Rigottier");
        user.setPassword("123456");
        user.setUserType(UserType.STANDARD);

        UserValidationException userValidationException = org.assertj.core.api.Assertions.catchThrowableOfType(() -> new UserValidator().isAdminUser(user), UserValidationException.class);
        String expectedMessage = "User validation failed for user: Jonathan Rigottier, Error: User admin check failed";
        Assertions.assertEquals(expectedMessage, userValidationException.getLocalizedMessage());
    }


    //Junit4 example
    /*
    @Test(expect = UserValidationException.class)
    public void givenUser_whenIsAdminUserCalledFunctionally_shouldExpectException_Junit4() {
        User user = new User();
        user.setFirstName("Jonathan");
        user.setLastName("Rigottier");
        user.setPassword("123456");
        user.setUserType(UserType.STANDARD);

        new UserValidator().isAdminUser(user);

    }
     */
}

