package ru.link.experimental.DTO;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * Модель возврата для ошибки
 */
@Data
@RequiredArgsConstructor
public class ErrorDTO {

    @NonNull
    private HttpStatus httpStatus;

    @NonNull
    private String message;
}
