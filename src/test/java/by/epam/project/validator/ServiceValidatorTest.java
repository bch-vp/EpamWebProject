package by.epam.project.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ServiceValidatorTest {
    @DataProvider(name = "isLoginCorrectData")
    public Object[][] isLoginCorrectData() {
        return new Object[][]{
                {null, false},
                {"ilya_user", true},
                {"123456789012345", true},
                {"341aefAFG", true},
                {"f", false},
                {"F", false},
                {"", false}
        };
    }

    @Test(dataProvider = "isLoginCorrectData")
    public void testIsLoginCorrect(String data, boolean expected) {
        boolean actual = ServiceValidator.isLoginCorrect(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isPasswordCorrectData")
    public Object[][] isPasswordCorrectData() {
        return new Object[][]{
                {null, false},
                {"Aa123", true},
                {"123456789012345678Aa", true},
                {"123456789012345678Aa2", false},
                {"faegga", false},
                {"2eag", false},
                {"1345235", false},
                {"253sgs", false},
                {"12Aa", false},
                {"", false}
        };
    }

    @Test(dataProvider = "isPasswordCorrectData")
    public void testIsPasswordCorrect(String data, boolean expected) {
        boolean actual = ServiceValidator.isPasswordCorrect(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isEmailCorrectData")
    public Object[][] isEmailCorrectData() {
        return new Object[][]{
                {null, false},
                {"illia@gm.co", true},
                {"i@2f.ca", true},
                {"123456789012345678Aa2", false},
                {"faegga", false},
                {"2eag", false},
                {"1345235", false},
                {"253sgs@fa", false},
                {"12Aa.co", false},
                {"", false}
        };
    }

    @Test(dataProvider = "isEmailCorrectData")
    public void testIsEmailCorrect(String data, boolean expected) {
        boolean actual = ServiceValidator.isEmailCorrect(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isFirstNameCorrectData")
    public Object[][] isFirstNameCorrectData() {
        return new Object[][]{
                {null, false},
                {"Agge", true},
                {"aegfg", true},
                {"asdfghjklrtyuio", true},
                {"asdfghjklrtyuiog", false},
                {"fae352gga", false},
                {"ge4", false},
                {"1345235", false},
                {"253sgs@fa", false},
                {"12Aa.co", false},
                {"", false}
        };
    }

    @Test(dataProvider = "isFirstNameCorrectData")
    public void testIsFirstNameCorrect(String data, boolean expected) {
        boolean actual = ServiceValidator.isFirstNameCorrect(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isPhoneCorrectData")
    public Object[][] isPhoneCorrectData() {
        return new Object[][]{
                {null, false},
                {"+375(29)345-54-54", true},
                {"+375(33)345-54-54", true},
                {"+375(29)345-54-5", false},
                {"asdfghjklrtyuiog", false},
                {"fae352gga", false},
                {"gea", false},
                {"1345235", false},
                {"253sgs@fa", false},
                {"12Aa.co", false},
                {"", false}
        };
    }

    @Test(dataProvider = "isPhoneCorrectData")
    public void testIsPhoneCorrect(String data, boolean expected) {
        boolean actual = ServiceValidator.isPhoneCorrect(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isUniqueCodeCorrectData")
    public Object[][] isUniqueCodeCorrectData() {
        return new Object[][]{
                {null, false},
                {"235653", true},
                {"446", false},
                {"aef4", false},
                {"3464346", false},
                {"", false}
        };
    }

    @Test(dataProvider = "isUniqueCodeCorrectData")
    public void testIsUniqueCodeCorrect(String data, boolean expected) {
        boolean actual = ServiceValidator.isUniqueCodeCorrect(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isIdCorrectData")
    public Object[][] isIdCorrectData() {
        return new Object[][]{
                {null, false},
                {"235653", true},
                {"446", true},
                {"aef4", false},
                {"", false}
        };
    }

    @Test(dataProvider = "isIdCorrectData")
    public void testIsIdCorrect(String data, boolean expected) {
        boolean actual = ServiceValidator.isIdCorrect(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isPriceCorrectData")
    public Object[][] isPriceCorrectData() {
        return new Object[][]{
                {null, false},
                {"0.01", true},
                {"446", true},
                {"0.1", false},
                {"-2", false},
                {"", false}
        };
    }

    @Test(dataProvider = "isPriceCorrectData")
    public void testIsPriceCorrect(String data, boolean expected) {
        boolean actual = ServiceValidator.isPriceCorrect(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isAddressCorrectData")
    public Object[][] isAddressCorrectData() {
        return new Object[][]{
                {null, false},
                {"vul .vorovskogo 25/89", true},
                {"aa", false},
        };
    }

    @Test(dataProvider = "isAddressCorrectData")
    public void testIsAddressCorrect(String data, boolean expected) {
        boolean actual = ServiceValidator.isAddressCorrect(data);
        assertEquals(actual, expected);
    }
}