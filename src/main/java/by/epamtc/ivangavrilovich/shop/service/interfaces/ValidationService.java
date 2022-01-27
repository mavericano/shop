package by.epamtc.ivangavrilovich.shop.service.interfaces;

public interface ValidationService {
    boolean validatePhoneNumber(String phoneNumber);

    boolean validatePassword(String password);

    boolean validateEqualPasswords(String firstPassword, String secondPassword);

    boolean validateEmptiness(String... toValidate);

    boolean validateInt(String number);

    boolean validateFloat(String number);

    boolean validateBeltButton(String beltButton);
}
