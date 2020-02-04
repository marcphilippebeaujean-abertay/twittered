package com.socialmediaraiser.twitter.dto.others;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class RateLimitStatusDTO {

    @JsonProperty("rate_limit_context")
    private RateLimitContextDTO rateLimitContext;
    private Map<String, JsonNode> resources;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class RateLimitContextDTO{
        @JsonProperty("access_token")
        private String accessToken;
    }
}
