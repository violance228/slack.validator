package io.exigence.businesscomponents.slack.validator.service;

import io.exigence.businesscomponents.slack.validator.dto.ArchiveChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.CreateChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.CreateChannelResponse;
import io.exigence.businesscomponents.slack.validator.dto.PostMessageRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.fail;

class SlackServiceServiceImplTest {

    private final SlackServiceServiceImpl slackServiceService = new SlackServiceServiceImpl(new RestTemplate());
    private CreateChannelResponse createChannelResponse;

    @BeforeEach
    public void init() {
        slackServiceService.setToken("xoxb-1342125020274-1348339878372-kNmInWiTik2J8fIq1EF31us6");
        slackServiceService.setSlackUrl("https://slack.com/api/");
        slackServiceService.init();
        try {
            createChannelResponse = slackServiceService.createChannel(new CreateChannelRequest(String.valueOf(System.nanoTime()), true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAllMethodPassedAndPassedUntil2000ms() {
        long start = System.currentTimeMillis();
        try {
            CreateChannelResponse channel = slackServiceService.createChannel(new CreateChannelRequest(String.valueOf(System.currentTimeMillis()), true));
            slackServiceService.postMessage(new PostMessageRequest(channel.getChannel().getId(), "sadsa"));
            slackServiceService.archiveChannel(new ArchiveChannelRequest(channel.getChannel().getId()));
        } catch (Exception e) {
            fail();
        }
        Assertions.assertTrue((System.currentTimeMillis() - start) < 2000L);
    }

    @Test
    public void testAllMethodsFail() {
        try {
            slackServiceService.setToken("sdad");
            slackServiceService.init();
            CreateChannelResponse channel = slackServiceService.createChannel(new CreateChannelRequest("violence22", true));
            slackServiceService.postMessage(new PostMessageRequest(channel.getChannel().getId(), "sadsa"));
            slackServiceService.archiveChannel(new ArchiveChannelRequest(channel.getChannel().getId()));
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSomeMethodsAreFail() {
        try {
            slackServiceService.createChannel(new CreateChannelRequest("violence22", true));
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            slackServiceService.postMessage(new PostMessageRequest(createChannelResponse.getChannel().getId(), "sadsa"));
            slackServiceService.archiveChannel(new ArchiveChannelRequest(createChannelResponse.getChannel().getId()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }


    }
}