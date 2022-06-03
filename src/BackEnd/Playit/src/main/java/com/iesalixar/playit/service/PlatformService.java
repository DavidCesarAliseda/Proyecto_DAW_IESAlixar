package com.iesalixar.playit.service;

import java.util.List;
import java.util.Optional;

import com.iesalixar.playit.model.Platform;

public interface PlatformService {

	public List<Platform> getAllPlatforms();
	public Platform addPlatform(Platform platform);
	public Platform getPlatformByName(String name);
	public Platform deletePlatform(Long id);
	public Platform getPlatformByID(Long id);
	public Platform editPlatform(Platform platform);
	
}
