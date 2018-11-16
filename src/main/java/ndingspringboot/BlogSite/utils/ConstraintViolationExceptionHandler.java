package ndingspringboot.BlogSite.utils;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to handle all the bean check error or exception
 */
public class ConstraintViolationExceptionHandler {

    public static String getMessage(ConstraintViolationException e) {
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList.toArray(), ";");
        return messages;
    }
}
