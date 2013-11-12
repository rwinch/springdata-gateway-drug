package sample.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import sample.domain.User;

// tag::dataRestSummary[]
@RestResource (path = "users", rel = "users")
// tag::summary[]
public interface UserRepository extends PagingAndSortingRepository<User, Long>, QueryDslPredicateExecutor<User> {
// end::dataRestSummary[]

    Page<User> findByLastName(@Param("lastName") String lastName, Pageable pageable);
// end::summary[]

// tag::findByFirstNameAndLastName[]
    List<User> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
// end::findByFirstNameAndLastName[]
}
