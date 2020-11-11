package ru.link.experimental.DTO;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class PurchaseQuestionDTO {
    @NonNull private String name;
    @NonNull private String content;

    private PurchaseAnswerDTO answer;
}
