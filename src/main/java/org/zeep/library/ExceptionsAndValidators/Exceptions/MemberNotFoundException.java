package org.zeep.library.ExceptionsAndValidators.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberNotFoundException extends RuntimeException {
    private String message;
    public MemberNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
