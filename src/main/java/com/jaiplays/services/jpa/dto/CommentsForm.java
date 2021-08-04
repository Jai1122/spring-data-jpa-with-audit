package com.jaiplays.services.jpa.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@ToString
public class CommentsForm {
    private String postName;
    private String comment;
}
