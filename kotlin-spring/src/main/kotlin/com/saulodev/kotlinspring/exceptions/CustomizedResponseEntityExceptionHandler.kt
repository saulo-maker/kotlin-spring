package com.saulodev.kotlinspring.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice /*É Usado sempre*/
@RestController
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(java.lang.Exception::class)
    fun handlerAllException(ex: Exception, request: WebRequest):
            ResponseEntity<ExceptionResponse> {
            val exceptionResponse = ExceptionResponse(
                Date(),
                ex.message,
                request.getDescription(false)
            )
        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(UnsupportedMathOperationException::class)
    fun handlerBadRequestExceptions(ex: Exception, request: WebRequest):
            ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(
            Date(),
            ex.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST)
    }
}