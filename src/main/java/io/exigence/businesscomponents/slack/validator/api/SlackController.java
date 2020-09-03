package io.exigence.businesscomponents.slack.validator.api;

import io.exigence.businesscomponents.slack.validator.dto.ArchiveChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.CreateChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.PostMessageRequest;
import io.exigence.businesscomponents.slack.validator.service.SlackServiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class SlackController {

    private final SlackServiceService slackServiceService;

    @GetMapping(value = "/start")
    public ResponseEntity<String> checkSlackApiKey() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            slackServiceService.createChannel(new CreateChannelRequest("violence22", true));
        } catch (Exception e) {
            stringBuilder.append("failed create channel").append(e.getMessage()).append(";-----");
            log.error(e.getMessage());
        }
        try {
            slackServiceService.postMessage(new PostMessageRequest("violence22", "hello"));
        } catch (Exception e) {
            stringBuilder.append("failed post message").append(e.getMessage()).append(";-----");
            log.error(e.getMessage());
        }
        try {
            slackServiceService.archiveChannel(new ArchiveChannelRequest("violence22"));
        } catch (Exception e) {
            stringBuilder.append("failed archive channel").append(e.getMessage());
            log.error(e.getMessage());
        }
        return ResponseEntity.status(stringBuilder.length() == 0 ? 200 : 500)
                .body(stringBuilder.toString());
    }
}
