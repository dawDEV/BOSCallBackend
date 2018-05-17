package de.dorianweidler.boscallserver.repositories;

import org.springframework.data.repository.CrudRepository;

import de.dorianweidler.boscallserver.dto.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByIdAndApiKey(long id, String apiKey);
	public User findByToken(String apiKey);
	
}
