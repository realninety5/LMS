package org.zeep.library.ExceptionsAndValidators.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsernameOrEmailAlreadyExists extends RuntimeException {
    private String message;
    UsernameOrEmailAlreadyExists(String message) {
        super(message);
        this.message = message;
    }
}
