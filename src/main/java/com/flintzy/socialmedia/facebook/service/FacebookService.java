package com.flintzy.socialmedia.facebook.service;

import java.util.List;

import com.flintzy.socialmedia.facebook.entity.FacebookPage;
import com.flintzy.socialmedia.user.entity.User;

public interface FacebookService {

	public List<FacebookPage> linkFacebookPages(User user);
}
