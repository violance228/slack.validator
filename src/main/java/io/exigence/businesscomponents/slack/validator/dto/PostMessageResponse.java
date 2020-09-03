package io.exigence.businesscomponents.slack.validator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMessageResponse {
    public boolean ok;
    public String channel;
    public String ts;
    public Message message;
    public String error;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        public String text;
        public String username;
        @JsonProperty("bot_id")
        public String botId;
        public String type;
        public String subtype;
    }
}
