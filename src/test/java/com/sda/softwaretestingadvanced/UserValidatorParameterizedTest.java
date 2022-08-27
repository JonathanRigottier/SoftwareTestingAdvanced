package com.sda.softwaretestingadvanced;

import com.sda.softwaretestingadvanced.utils.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.fail;

/**
 * Parameterized tests
 *
 * @author Vinod John
 */
public class UserValidatorParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings = {"Jonathan123456", "Rigottier123456", "Avengers2022", "sqdsffgrqdgrssrg"})
    public void givenUserPassword_whenEncodePasswordCalled_shouldReturnEncodedPassword(String password) {
        String expectedValue = password.substring(0, password.length() /2) + "#sda_java#" + password.substring(password.length() /2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @MethodSource("getPasswords")
    public void givenUserPasswords_whenEncodePasswordCalled_shouldReturnEncodedPasswords(String password) {
        String expectedValue = password.substring(0, password.length() /2) + "#sda_java#" + password.substring(password.length() /2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @ArgumentsSource(Parameters.class)
    public void givenUserPasswordsFromProvider_whenEncodePasswordCalled_shouldReturnEncodedPassword(String password) {
        String expectedValue = password.substring(0, password.length() /2) + "#sda_java#" + password.substring(password.length() /2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @CsvSource({"Jonathan123456, Jonatha#sda_java#n123456","Rigottier123456, Rigotti#sda_java#er123456"})
    public void givenUserPasswordsFromCsv_whenEncodePasswordCalled_shouldReturnEncodePassword(String password, String encodedPassword) {
        Assertions.assertEquals(encodedPassword, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataSource.csv")
    public void givenUserPasswordFromCsvFile_whenEncodePasswordCalled_shouldReturnEncodePassword(String password, String encodedPassword) {
        Assertions.assertEquals(encodedPassword, new UserValidator().encodePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Jonathan123456", "Rigottier123456", "Avengers2022", "sqdsffgrqdgrssrg"})
    public void givenUserPasswordConverter_whenEncodePasswordCalled_shouldReturnEncodedPassword(@ConvertWith(PasswordConverter.class) String password) {
        String expectedValue = password.substring(0, password.length() /2) + "#sda_java#" + password.substring(password.length() /2);
        Assertions.assertEquals(expectedValue, new UserValidator().encodePassword(password));
    }

    //For method source
    static Stream<Arguments> getPasswords() {
        return Stream.of(Arguments.of("Jonathan123456"), Arguments.of("dsfqfrdgqfhgtshghtgghf"));
    }


    //For argument source
    static class Parameters implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(Arguments.of("Jonathan123456"), Arguments.of("dsfqfrdgqfhgtshghtgghf"));
        }
    }

    //For Argument converter
    static class PasswordConverter implements ArgumentConverter {

        @Override
        public Object convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {
            if(o instanceof String) {
                return ((String) o).replaceAll("12345","67890");
            }

            fail("Cannot replace string");
            return null;
        }
    }

}
