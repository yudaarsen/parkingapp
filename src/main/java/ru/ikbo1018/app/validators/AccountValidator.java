package ru.ikbo1018.app.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.ikbo1018.app.models.account.Account;
import org.apache.commons.validator.routines.EmailValidator;

@Component
public class AccountValidator implements Validator {

    private static final String PATTERN_PASSWORD = "^[a-zA-Z0-9-_@!,]{6,64}$";
    private static final int LENGTH_FIRSTNAME = 50;
    private static final int LENGTH_LASTNAME = 50;
    private static final int LENGTH_MIDNAME = 50;
    private static final int LENGTH_EMAIL = 255;
    private static final String PATTERN_CONTAINS_NUMBER = ".*\\d.*";

    public boolean supports(Class<?> aClass) {
        return Account.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        Account account = (Account) o;
        String firstName = account.getFirstName().trim();
        String lastName = account.getLastName().trim();
        String midName = account.getMidName().trim();
        String email = account.getEmail();
        String password = account.getPassword();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty");
        if(firstName.contains(" ") || firstName.length() > LENGTH_FIRSTNAME || firstName.matches(PATTERN_CONTAINS_NUMBER))
            errors.rejectValue("firstName", "firstName.format");
        if(lastName.contains(" ") || lastName.length() > LENGTH_LASTNAME || lastName.matches(PATTERN_CONTAINS_NUMBER))
            errors.rejectValue("lastName", "lastName.format");
        if(midName.length() > 0 && (midName.contains(" ") || midName.length() > LENGTH_MIDNAME
                || midName.matches(PATTERN_CONTAINS_NUMBER)))
            errors.rejectValue("midName", "midName.format");
        if(!EmailValidator.getInstance().isValid(email) || email.length() > LENGTH_EMAIL)
            errors.rejectValue("email", "email.format");
        if(password == null || !password.matches(PATTERN_PASSWORD))
            errors.rejectValue("password", "password.format");
    }
}
