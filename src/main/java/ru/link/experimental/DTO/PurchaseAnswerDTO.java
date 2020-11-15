package ru.link.experimental.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseAnswerDTO {
    private String content;
    @NonNull private boolean publicity;
}
