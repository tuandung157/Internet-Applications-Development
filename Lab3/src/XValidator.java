import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@javax.faces.validator.FacesValidator("XValidator")
public class XValidator implements javax.faces.validator.Validator
{
    public XValidator() {}

    public void validate(FacesContext context, javax.faces.component.UIComponent component, Object value) throws ValidatorException
    {
        if (value.getClass().getName().toString() == "java.lang.String") {
            FacesMessage msg = new FacesMessage("String");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        float arg = new Float(value.toString()).floatValue();
        if ((arg < -5.0F) || (arg > 5.0F) || (value.toString().length() - value.toString().indexOf('.') > 2)) {
            FacesMessage msg = new FacesMessage("must be one of the values [-5;5] with delta 0.1");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}