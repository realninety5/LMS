package org.zeep.library.ExceptionsAndValidators.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundException extends RuntimeException {
    private String message;
    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
