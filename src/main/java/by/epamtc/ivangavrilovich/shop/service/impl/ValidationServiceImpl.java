package by.epamtc.ivangavrilovich.shop.service.impl;

import by.epamtc.ivangavrilovich.shop.service.interfaces.ValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        return (phoneNumber != null) && phoneNumber.matches("^\\+375[0-9]{9}$");
    }

    @Override
    public boolean validatePassword(String password) {
        Pattern patternDig = Pattern.compile("\\d");
        Pattern patternLet = Pattern.compile("[a-zA-Z]");
        Pattern patternSpec = Pattern.compile("[^a-zA-Z0-9!@#$%^&*()_+.,;:]");
        Matcher matcher;
        if ((password == null) || ((password.length() < 8) || (password.length() > 50))) {
            return false;
        }

        matcher = patternDig.matcher(password);
        if (!matcher.find()) {
            return false;
        }

        matcher = patternLet.matcher(password);
        if (!matcher.find()) {
            return false;
        }

        matcher = patternSpec.matcher(password);
        return !matcher.find();
    }

    @Override
    public boolean validateEqualPasswords(String firstPassword, String secondPassword) {
        return (firstPassword != null) && firstPassword.equals(secondPassword);
    }

    @Override
    public boolean validateEmptiness(String... toValidate) {
        for (String curr : toValidate) {
            if (curr == null || curr.length() == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean validateInt(String number) {
        return number.matches("^\\d+$");
    }

    @Override
    public boolean validateFloat(String number) {
        return number.matches("^\\d+\\.\\d+$") || number.matches("^\\d+$");
    }

    @Override
    public boolean validateBeltButton(String beltButton) {
        return "yes".equals(beltButton) || "no".equals(beltButton);
    }
}
