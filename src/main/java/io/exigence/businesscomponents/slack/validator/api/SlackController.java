package io.exigence.businesscomponents.slack.validator.api;

import io.exigence.businesscomponents.slack.validator.api.exceptions.SlackException;
import io.exigence.businesscomponents.slack.validator.dto.ArchiveChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.CreateChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.CreateChannelResponse;
import io.exigence.businesscomponents.slack.validator.dto.PostMessageRequest;
import io.exigence.businesscomponents.slack.validator.service.SlackServiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class SlackController {

    private final SlackServiceService slackServiceService;

    @PostMapping(value = "/start/{text}")
    public ResponseEntity<String> checkSlackApiKey(@PathVariable String text, @RequestBody CreateChannelRequest channelRequest) {
        StringBuilder stringBuilder = new StringBuilder();
        CreateChannelResponse channelResponse = new CreateChannelResponse();
        try {
            channelResponse = slackServiceService.createChannel(channelRequest);
        } catch (SlackException e) {
            stringBuilder.append("failed create channel").append(e.getMessage()).append(";-----");
            log.error(e.getMessage());
        }
        try {
            slackServiceService.postMessage(new PostMessageRequest(channelResponse.getChannel().getId(), text));
        } catch (SlackException e) {
            stringBuilder.append("failed post message").append(e.getMessage()).append(";-----");
            log.error(e.getMessage());
        }
        try {
            slackServiceService.archiveChannel(new ArchiveChannelRequest(channelResponse.getChannel().getId()));
        } catch (SlackException e) {
            stringBuilder.append("failed archive channel").append(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.status(stringBuilder.length() == 0 ? 500 : 200)
                .body(stringBuilder.toString());
    }
}
