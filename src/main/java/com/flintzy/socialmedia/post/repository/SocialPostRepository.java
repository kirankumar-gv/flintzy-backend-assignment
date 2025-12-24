package com.flintzy.socialmedia.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flintzy.socialmedia.post.entity.SocialPost;

public interface SocialPostRepository extends JpaRepository<SocialPost, Long> {

}
