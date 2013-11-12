/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.sample.data;

import static org.fest.assertions.Assertions.assertThat;
import static sample.data.UserPredicates.firstNameLike;
import static sample.data.UserPredicates.lastNameLike;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import sample.domain.User;

import com.mysema.query.types.Predicate;

public class UserRepositoryTest extends AbstractSpringTest {

    @Test
    public void findByLastName() {
        // tag::findByLastName[]
        PageRequest pageable = new PageRequest(1, 10);
        Page<User> users =
            userRepository.findByLastName("Winch", pageable);
        // end::findByLastName[]

        assertThat(users.getTotalPages()).isEqualTo(1);
    }

    @Test
    public void firstNameLikePredicate() {
        // tag::firstNameLikePredicate[]
        Predicate predicate =
            firstNameLike("R");
        Page<User> users =
            userRepository.findAll(predicate, page);
        // end::firstNameLikePredicate[]

        assertThat(users.getTotalElements()).isEqualTo(2);
    }

    @Test
    public void firstNameLikeAndLastNameLikePredicate() {
        // tag::firstNameLikeAndLastNameLikePredicate[]
        Predicate predicate =
            firstNameLike("R").and(lastNameLike("W"));
        Page<User> users =
            userRepository.findAll(predicate, page);
        // end::firstNameLikeAndLastNameLikePredicate[]

        assertThat(users.getTotalElements()).isEqualTo(1);
    }

    @Test
    public void firstNameLikeOrLastNameLikePredicate() {
        // tag::firstNameLikeOrLastNameLikePredicate[]
        Predicate predicate =
            firstNameLike("R").or(lastNameLike("W"));
        Page<User> users =
            userRepository.findAll(predicate, page);
        // end::firstNameLikeOrLastNameLikePredicate[]

        assertThat(users.getTotalElements()).isEqualTo(3);
    }
}
