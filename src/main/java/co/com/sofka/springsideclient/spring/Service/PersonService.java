package co.com.sofka.springsideclient.spring.Service;

import co.com.sofka.springsideclient.spring.Entity.Person;
import co.com.sofka.springsideclient.spring.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public Flux<Person> listAll() {
        return repository.findAll();
    }

    public Mono<Void> insert(Person person) {
        return validateBeforeInsert.apply(repository, person)
                .switchIfEmpty(Mono.defer(() -> repository.save(person)))
                .then();
    }

    private final BiFunction<PersonRepository, Person, Mono<Person>> validateBeforeInsert
            = (repo, person) -> repo.findByName(person.getName());
}
