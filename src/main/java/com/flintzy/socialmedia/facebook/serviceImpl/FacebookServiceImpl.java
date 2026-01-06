package com.flintzy.socialmedia.facebook.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flintzy.socialmedia.facebook.entity.FacebookPage;
import com.flintzy.socialmedia.facebook.repository.FacebookPageRepository;
import com.flintzy.socialmedia.facebook.service.FacebookService;
import com.flintzy.socialmedia.user.entity.User;

@Service
public class FacebookServiceImpl implements FacebookService{

	private final FacebookPageRepository pageRepository;
	private final RestTemplate restTemplate = new RestTemplate();

	public FacebookServiceImpl(FacebookPageRepository pageRepository) {
		this.pageRepository = pageRepository;
	}

	public List<FacebookPage> linkFacebookPages(User user) {
		
		String userAccessToken = user.getFacebookAccessToken();
		
		if(userAccessToken == null) {
			throw new RuntimeException("Please login to you facebook to get a valid access token");
		}

		String url = "https://graph.facebook.com/me/accounts?access_token=" + userAccessToken;

		Map<?, ?> response = restTemplate.getForObject(url, Map.class);

		List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

		List<FacebookPage> savedPages = new ArrayList<>();

		for (Map<String, Object> pageData : data) {

			String pageId = (String) pageData.get("id");

			FacebookPage page = pageRepository.findByPageIdAndUser_Id(pageId, user.getId()).orElseGet(() -> {
				FacebookPage newPage = new FacebookPage();
				newPage.setPageId(pageId);
				newPage.setUser(user);
				return newPage;
			});

			page.setPageName((String) pageData.get("name"));
			page.setPageAccessToken((String) pageData.get("access_token"));

			savedPages.add(pageRepository.save(page));

		}

		return savedPages;
	}
}
