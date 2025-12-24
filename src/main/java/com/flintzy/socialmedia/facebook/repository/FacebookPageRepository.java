package com.flintzy.socialmedia.facebook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flintzy.socialmedia.facebook.entity.FacebookPage;

public interface FacebookPageRepository extends JpaRepository<FacebookPage, Long> {

	Optional<FacebookPage> findByPageIdAndUser_Id(String pageId, Long userId);
}
