package com.flintzy.socialmedia.post.service;

import com.flintzy.socialmedia.post.dto.PublishPostRequest;
import com.flintzy.socialmedia.post.entity.SocialPost;
import com.flintzy.socialmedia.user.entity.User;

public interface PostService {

	public SocialPost publishPost(User user, PublishPostRequest requst);
}
