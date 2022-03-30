package org.zeep.library.ExceptionsAndValidators.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordDoNotMatchException extends RuntimeException {
    private String message;
    public PasswordDoNotMatchException(String message) {
        super(message);
        this.message = message;
    }
}
