package com.flintzy.socialmedia.auth.dto.facebook;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookTokenResponse {
    private String access_token;
    private String token_type;
    private Long expires_in;
}
