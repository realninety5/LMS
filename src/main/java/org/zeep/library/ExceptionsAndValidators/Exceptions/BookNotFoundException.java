package org.zeep.library.ExceptionsAndValidators.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Getter
public class BookNotFoundException extends RuntimeException {
    private String message;
    public BookNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
