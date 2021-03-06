package cz.etnetera.reesmo.repository.mongodb.user;

import cz.etnetera.reesmo.model.mongodb.user.ManualUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Manual User repository custom method implementation
 */
public class ManualUserRepositoryImpl implements ManualUserRepositoryCustom {

	@Autowired
    private MongoOperations mongoTemplate;
	
	@Override
	public boolean hasAnyAdmin() {
		return mongoTemplate.count(Query.query(Criteria.where("superadmin").is(true)), ManualUser.class) > 0;
	}

	@Override
	public ManualUser findOneByEmail(String email) {
		return mongoTemplate.findOne(Query.query(Criteria.where("email").is(email)), ManualUser.class);
	}
	
}
