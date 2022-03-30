package org.zeep.library.ExceptionsAndValidators.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WrongUsernameOrPasswordException extends RuntimeException{
        private String message;
    public WrongUsernameOrPasswordException(String message) {
            super(message);
            this.message = message;
        }
}
