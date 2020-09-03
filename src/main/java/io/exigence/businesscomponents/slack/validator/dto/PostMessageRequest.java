package io.exigence.businesscomponents.slack.validator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMessageRequest {
    @Pattern(regexp = "[a-z||0-9||_||-]{3,80}")
    private String channel;
    private String text;
}
