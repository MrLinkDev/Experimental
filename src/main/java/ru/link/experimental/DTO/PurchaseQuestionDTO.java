package ru.link.experimental.DTO;

import lombok.Data;

@Data
public class PurchaseQuestionDTO {
    private String name;
    private String content;

    private PurchaseAnswerDTO answer;
}
