package com.capitole.inditex.price.infrastructure.inbound.advice;
import com.capitole.inditex.domain.Error;
import com.capitole.inditex.price.domain.model.PriceError;
import com.capitole.inditex.price.domain.model.PriceException;
import com.capitole.inditex.price.infrastructure.inbound.PriceController;
import com.capitole.inditex.price.infrastructure.inbound.mapper.PriceErrorMapper;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = PriceController.class)
@Hidden
@AllArgsConstructor
public class PriceControllerAdvice extends ResponseEntityExceptionHandler {
    private final PriceErrorMapper priceErrorMapper;

    @ExceptionHandler(PriceException.class)
    ResponseEntity<Error> priceException(final PriceException e) {
        return new ResponseEntity<>(priceErrorMapper.toDTO(PriceError.builder()
                .errorCode(e.getError().getErrorCode())
                .errorMessage(e.getError().getErrorMessage())
                .build()),
                HttpStatus.OK
        );
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Error> priceGeneralError(final Exception e) {
        return new ResponseEntity<>(
                priceErrorMapper.toDTO(PriceError.builder()
                        .errorCode(PriceException.gateway().getError().getErrorCode())
                        .errorMessage(PriceException.gateway().getError().getErrorMessage())
                        .build()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
