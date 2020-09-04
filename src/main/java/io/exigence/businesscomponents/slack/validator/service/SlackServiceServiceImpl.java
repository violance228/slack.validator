package io.exigence.businesscomponents.slack.validator.service;

import io.exigence.businesscomponents.slack.validator.dto.ArchiveChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.ArchiveChannelResponse;
import io.exigence.businesscomponents.slack.validator.dto.CreateChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.CreateChannelResponse;
import io.exigence.businesscomponents.slack.validator.dto.PostMessageRequest;
import io.exigence.businesscomponents.slack.validator.dto.PostMessageResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;

import static io.exigence.businesscomponents.slack.validator.util.Utils.toJson;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpMethod.POST;

@Service
public class SlackServiceServiceImpl implements SlackServiceService {

    private final RestTemplate restTemplate;
    @Value("${token}")
    @Setter private String token;
    @Value("${slackUrl}")
    @Setter private String slackUrl;
    private LinkedMultiValueMap<String, String> headers;

    public SlackServiceServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Retryable(value = { Exception.class }, maxAttempts = 2, backoff = @Backoff(delay = 450))
    public CreateChannelResponse createChannel(CreateChannelRequest channelRequest) throws Exception {
        ResponseEntity<CreateChannelResponse> responseEntity =
                restTemplate.exchange(slackUrl + "conversations.create", POST, new HttpEntity<>(toJson(channelRequest), headers), CreateChannelResponse.class, new HashMap<>());

        if (responseEntity.getStatusCodeValue() != 200 || !requireNonNull(responseEntity.getBody()).isOk()) {
            throw new Exception(requireNonNull(responseEntity.getBody()).getError());
        }

        return responseEntity.getBody();
    }

    @Override
    @Retryable(value = { Exception.class }, maxAttempts = 2, backoff = @Backoff(delay = 450))
    public PostMessageResponse postMessage(PostMessageRequest messageRequest) throws Exception {
        ResponseEntity<PostMessageResponse> responseEntity =
                restTemplate.exchange(slackUrl + "chat.postMessage", POST, new HttpEntity<>(toJson(messageRequest), headers), PostMessageResponse.class, new HashMap<>());

        if (responseEntity.getStatusCodeValue() != 200 || !requireNonNull(responseEntity.getBody()).isOk())
            throw new Exception(requireNonNull(responseEntity.getBody()).getError());

        return responseEntity.getBody();
    }

    @Override
    @Retryable(value = { Exception.class }, maxAttempts = 2, backoff = @Backoff(delay = 450))
    public ArchiveChannelResponse archiveChannel(ArchiveChannelRequest channelRequest) throws Exception {
        ResponseEntity<ArchiveChannelResponse> responseEntity =
                restTemplate.exchange(slackUrl + "conversations.archive", POST, new HttpEntity<>(toJson(channelRequest), headers), ArchiveChannelResponse.class, new HashMap<>());

        if (responseEntity.getStatusCodeValue() != 200 || !requireNonNull(responseEntity.getBody()).isOk())
            throw new Exception(requireNonNull(responseEntity.getBody()).getError());

        return responseEntity.getBody();
    }

    @PostConstruct
    public void init() {
        this.headers = new LinkedMultiValueMap<>() {{
            add("content-type", "application/json; charset=utf-8");
            add("Authorization", "Bearer " + token);
        }};
    }
}
