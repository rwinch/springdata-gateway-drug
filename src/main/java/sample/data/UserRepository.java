package sample.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

import sample.domain.User;

// tag::dataRestSummary[]
@RestResource (path = "users", rel = "users")
// tag::summary[]
public interface UserRepository extends PagingAndSortingRepository<User, Long>, QueryDslPredicateExecutor<User> {
// end::dataRestSummary[]

    Page<User> findByLastName(String lastName, Pageable pageable);
// end::summary[]

// tag::findByFirstNameAndLastName[]
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
// end::findByFirstNameAndLastName[]
}
