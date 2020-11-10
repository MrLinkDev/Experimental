package ru.link.experimental.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseDTO {
    private String name;

    private List<PurchaseQuestionDTO> questions;
}
