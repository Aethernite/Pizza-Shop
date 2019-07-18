package PizzaShop.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context){
        return (validatePassword(password));
    }
    private boolean validatePassword(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
