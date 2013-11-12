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
package test.sample.data.pagingandsorting;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import sample.domain.User;

@Component("pagingandsorting.UserRepository")
public interface UserRepository
        extends PagingAndSortingRepository<User, Long> {

    // tag::sorting[]
    List<User> findByLastNameStartsWith(String lastName, Sort sort);
    // end::sorting[]

    // tag::paging[]
    Page<User> findByLastNameStartsWith(String lastName, Pageable pageable);
    // end::paging[]
}
