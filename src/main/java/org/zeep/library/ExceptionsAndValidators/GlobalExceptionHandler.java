package org.zeep.library.ExceptionsAndValidators;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.zeep.library.ExceptionsAndValidators.Exceptions.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<?> bookNotFoundExcception(BookNotFoundException bookNotFound) {
        return new ResponseEntity<>(bookNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> memberNotFoundException(NotFoundException memberNotFound) {
        return new ResponseEntity<>(memberNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PasswordDoNotMatchException.class)
    public ResponseEntity<?> passswordDoNotMatch(PasswordDoNotMatchException passwordDoNotMatchException) {
        return new ResponseEntity<>(passwordDoNotMatchException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameOrEmailAlreadyExistsException.class)
    public ResponseEntity<?> usernameOrEmailAlreadyExists(UsernameOrEmailAlreadyExistsException usernameOrEmail) {
        return new ResponseEntity<>(usernameOrEmail.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = WrongUsernameOrPasswordException.class)
    public ResponseEntity<?> wrongUsernameOrPassword(WrongUsernameOrPasswordException wrongDetails) {
        return new ResponseEntity<>(wrongDetails.getMessage(), HttpStatus.BAD_REQUEST);
    }
}