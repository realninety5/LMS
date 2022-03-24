package org.zeep.library.ExceptionsAndValidators.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WrongUsernameOrPasswordException extends RuntimeException{
        private String message;
    WrongUsernameOrPasswordException(String message) {
            super(message);
            this.message = message;
        }
}
