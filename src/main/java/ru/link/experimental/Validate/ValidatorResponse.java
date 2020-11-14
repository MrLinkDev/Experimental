package ru.link.experimental.Validate;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ValidatorResponse {

    @NonNull ValidatorStatus validatorStatus;

    @NonNull HttpStatus httpStatus;

    @NonNull String message;

}
