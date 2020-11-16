package ru.link.experimental.DTO;

import lombok.*;

@Data
public class SupplyDTO {

    @NonNull
    private long cost;

    @NonNull
    private String tStack;

    @NonNull
    private String structure;

    @NonNull
    private String comment;
}
