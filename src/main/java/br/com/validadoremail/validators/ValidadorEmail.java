package br.com.validadoremail.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("br.com.validadoremail.validators.ValidadorEmail")
public class ValidadorEmail implements Validator {

    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
        if (t == null) {
            return;
        }

        String email = (String) t;
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean validation = matcher.matches();
        
        if (!validation) {
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_FATAL,
                    "E-mail inválido!",
                    null);
            throw new ValidatorException(msg);
        } else {
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "E-mail válido!",
                    null);
            throw new ValidatorException(msg);
        }
    }

}
