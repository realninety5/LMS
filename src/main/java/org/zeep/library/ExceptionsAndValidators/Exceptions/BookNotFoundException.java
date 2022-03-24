package org.zeep.library.ExceptionsAndValidators.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookNotFoundException extends RuntimeException {
    private String message;
    BookNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
