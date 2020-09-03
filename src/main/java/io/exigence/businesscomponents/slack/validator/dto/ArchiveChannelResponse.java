package io.exigence.businesscomponents.slack.validator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveChannelResponse {
    private boolean ok;
    public String error;
}
