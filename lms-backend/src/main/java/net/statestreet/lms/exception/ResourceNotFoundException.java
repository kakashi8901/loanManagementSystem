package net.statestreet.lms.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {

        super(resourceName + " not Found with " + fieldName + " = " + fieldValue);

        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
        this.fieldName = fieldName;

    }
}
