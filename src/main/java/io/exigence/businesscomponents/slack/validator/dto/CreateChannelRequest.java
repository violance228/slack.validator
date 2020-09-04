package io.exigence.businesscomponents.slack.validator.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateChannelRequest {
    @Valid
    @Pattern(regexp = "[a-z||0-9||_||-]{3,80}")
    private String name;
    @JsonProperty("is_private")
    private Boolean isPrivate;
}
