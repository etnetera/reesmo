package cz.etnetera.reesmo.repository.mongodb.notifier;

import cz.etnetera.reesmo.notifier.Notifier2;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifierRepository extends MongoRepository<Notifier2, String>, NotifierRepositoryCustom {
}
