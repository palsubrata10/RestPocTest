package com.poc.RestPOC.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.RestPOC.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
