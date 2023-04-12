package com.rdhawane.recipemanagement.exceptions;

import org.springframework.http.HttpStatus;

public interface ICustomException {
    HttpStatus getStatus();
}