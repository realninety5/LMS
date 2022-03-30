package org.zeep.library.ExceptionsAndValidators.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsernameOrEmailAlreadyExistsException extends RuntimeException {
    private String message;
    public UsernameOrEmailAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
