package io.exigence.businesscomponents.slack.validator.service;

import io.exigence.businesscomponents.slack.validator.dto.ArchiveChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.CreateChannelRequest;
import io.exigence.businesscomponents.slack.validator.dto.PostMessageRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.fail;

class SlackServiceServiceImplTest {

    private static final SlackServiceServiceImpl slackServiceService = new SlackServiceServiceImpl(new RestTemplate());

    @BeforeAll
    public static void init() {
        slackServiceService.setToken("");
        slackServiceService.setSlackUrl("https://slack.com/api/");
        slackServiceService.init();
    }

    @Test
    public void testCreateChannelFail() {
        try {
            slackServiceService.createChannel(new CreateChannelRequest("violence22", true));
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPostMessageFail() {
        try {
            slackServiceService.postMessage(new PostMessageRequest("violence22", "sdsaddsa"));
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArchiveChannelFail() {
        try {
            slackServiceService.archiveChannel(new ArchiveChannelRequest("violence22"));
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}