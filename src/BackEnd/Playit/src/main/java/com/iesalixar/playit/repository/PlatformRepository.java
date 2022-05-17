package com.iesalixar.playit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesalixar.playit.model.Platform;
@Repository
public interface PlatformRepository  extends JpaRepository<Platform, Long>{
	public Platform findByName(String name);
}
