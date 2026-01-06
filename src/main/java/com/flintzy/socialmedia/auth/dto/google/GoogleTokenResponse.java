package com.flintzy.socialmedia.auth.dto.google;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleTokenResponse {
    private String access_token;
    private String id_token;
    private String token_type;
    private Long expires_in;
}

