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
package sample.data.namingconventions;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import sample.domain.User;

@Profile("test")
@Component("namingconventions.UserRepository")
public interface UserRepository
        extends PagingAndSortingRepository<User, Long> {

    // tag::findByFirstNameAndLastName[]
    // Find by two properties
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    // end::findByFirstNameAndLastName[]

    // tag::findByLastNameStartsWith[]
    List<User> findByLastNameStartsWith(String lastName);
    // end::findByLastNameStartsWith[]

    // tag::findByLastNameLike[]
    List<User> findByLastNameLike(String lastName);
    // end::findByLastNameLike[]

    // tag::ignoreCase[]
    // Enabling ignoring case for an individual property
    List<User> findByLastNameIgnoreCase(String lastName);

    // Enabling ignoring case for all suitable properties
    List<User> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);
    // end::ignoreCase[]
}