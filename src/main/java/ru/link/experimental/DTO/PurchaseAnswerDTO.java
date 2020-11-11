package ru.link.experimental.DTO;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class PurchaseAnswerDTO {
    @NonNull private String content;
    @NonNull private boolean publicity;
}
