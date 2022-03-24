package org.zeep.library.ExceptionsAndValidators.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordDoNotMatch extends RuntimeException {
    private String message;
    PasswordDoNotMatch(String message) {
        super(message);
        this.message = message;
    }
}
