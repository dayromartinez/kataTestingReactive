package co.com.sofka.springsideclient.spring.Repository;

import co.com.sofka.springsideclient.spring.Entity.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
    Mono<Person> findByName(String name);
}
