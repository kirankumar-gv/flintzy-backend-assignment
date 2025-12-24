package com.flintzy.socialmedia.post.serviceImpl;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flintzy.socialmedia.facebook.entity.FacebookPage;
import com.flintzy.socialmedia.facebook.exception.FacebookPageNotLinkedException;
import com.flintzy.socialmedia.facebook.repository.FacebookPageRepository;
import com.flintzy.socialmedia.post.dto.PublishPostRequest;
import com.flintzy.socialmedia.post.entity.SocialPost;
import com.flintzy.socialmedia.post.repository.SocialPostRepository;
import com.flintzy.socialmedia.post.service.PostService;
import com.flintzy.socialmedia.user.entity.User;

@Service
public class PostServiceImpl implements PostService{

	private final FacebookPageRepository facebookPageRepository;
	private final SocialPostRepository postRepository;
	private final RestTemplate restTemplate = new RestTemplate();

	public PostServiceImpl(FacebookPageRepository facebookPageRepository, SocialPostRepository postRepository) {
		this.facebookPageRepository = facebookPageRepository;
		this.postRepository = postRepository;
	}

	public SocialPost publishPost(User user, PublishPostRequest requst) {

		FacebookPage page = facebookPageRepository.findByPageIdAndUser_Id(requst.getPageId(), user.getId())
				.orElseThrow(() -> new FacebookPageNotLinkedException("Facebook Page not linked to this user"));

		String url = "https://graph.facebook.com/" + page.getPageId() + "/feed";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> body = Map.of("message", requst.getMessage(), "access_token", page.getPageAccessToken());

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

		ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, entity, Map.class);

		String faceBookPostId = responseEntity.getBody().get("id").toString();

		SocialPost post = new SocialPost();
		post.setPageID(page.getPageId());
		post.setContent(requst.getMessage());
		post.setFacebookPostId(faceBookPostId);
		post.setStatus("PUBLISH SUCESS");
		post.setCreatedAt(LocalDateTime.now());
		post.setUser(user);

		return postRepository.save(post);
	}

}
