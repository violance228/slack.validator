package io.exigence.businesscomponents.slack.validator.service;

import io.exigence.businesscomponents.slack.validator.dto.ArchiveChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.ArchiveChannelResponse;
import io.exigence.businesscomponents.slack.validator.dto.CreateChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.CreateChannelResponse;
import io.exigence.businesscomponents.slack.validator.dto.PostMessageRequest;
import io.exigence.businesscomponents.slack.validator.dto.PostMessageResponse;

public interface SlackServiceService {
    CreateChannelResponse createChannel(CreateChannelRequest channelRequest) throws Exception;
    PostMessageResponse postMessage(PostMessageRequest messageRequest) throws Exception;
    ArchiveChannelResponse archiveChannel(ArchiveChannelRequest channelRequest) throws Exception;
}
