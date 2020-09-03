package io.exigence.businesscomponents.slack.validator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateChannelResponse {
    public boolean ok;
    public Channel channel;
    public String error;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Channel {
        public String id;

        @Pattern(regexp = "[a-z||0-9||_||-]{3,80}")
        public String name;

        @JsonProperty("is_channel")
        public boolean isChannel;

        public long created;

        public String creator;

        @JsonProperty("is_archived")
        public boolean isArchived;

        @JsonProperty("is_general")
        public boolean isGeneral;
    }
}
