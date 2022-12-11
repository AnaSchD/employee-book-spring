package com.skypro.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
        (code = HttpStatus.BAD_REQUEST, reason = "Ошибка валидации, некорректные ФИО")
public class EmployeeValidationException extends Throwable {
    public EmployeeValidationException (String message) {
        super(message);
    }
}
