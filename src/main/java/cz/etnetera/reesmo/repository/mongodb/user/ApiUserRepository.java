package cz.etnetera.reesmo.repository.mongodb.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cz.etnetera.reesmo.model.mongodb.user.ApiUser;

/**
 * Api User repository
 */
@Repository
public interface ApiUserRepository extends MongoRepository<ApiUser, String>, ApiUserRepositoryCustom {
	
}