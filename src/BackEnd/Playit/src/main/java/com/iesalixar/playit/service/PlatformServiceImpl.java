package com.iesalixar.playit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iesalixar.playit.model.Platform;
import com.iesalixar.playit.repository.PlatformRepository;

@Repository
public class PlatformServiceImpl implements PlatformService {

	@Autowired
	PlatformRepository platformRepo;

	@Override
	public List<Platform> getAllPlatforms() {
		List<Platform> platformDB = platformRepo.findAll();

		if (platformDB != null && platformDB.size() > 0) {
			return platformDB;
		}

		return new ArrayList<Platform>();
	}

	@Override
	public Platform addPlatform(Platform platformBD) {
		if (platformBD != null && getPlatformByName(platformBD.getName()) == null) {
			Platform platform = platformRepo.save(platformBD);
			return platform;
		}
		return null;
	}

	@Override
	public Platform getPlatformByName(String name) {
		if (name != null) {
			Platform platform = platformRepo.findByName(name);
			return platform;
		}

		return null;
	}

	@Override
	public Platform deletePlatform(Long id) {
		if (id != null) {

			Platform platform = platformRepo.getById(id);
			platformRepo.delete(platform);
			return platform;
		}

		return null;
	}

	@Override
	public Platform getPlatformByID(Long id) {
		if(id != null) {
			Platform platform = platformRepo.getById(id);
			return platform;
		}
		return null;
	}

	@Override
	public Platform editPlatform(Platform platformBD) {
		if(platformBD!=null && getPlatformByName(platformBD.getName())!=null) {
			Platform platform = platformRepo.save(platformBD);
			return platform;
		}
		return null;
	}

}
