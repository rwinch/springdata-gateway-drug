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

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import sample.domain.User;
import test.sample.data.AbstractSpringTest;

public class PagingAndSortingUserRepositoryTest extends AbstractSpringTest {
    @Autowired
    private UserRepository repository;

    @Test
    @SuppressWarnings("unused")
    public void findByLastNameStartsWithPageable() {
        // tag::findByLastNameStartsWithPageable[]
        PageRequest pageable =
            new PageRequest(1, 10, Direction.ASC, "firstName");
        Page<User> users =
            repository.findByLastNameStartsWith("W", pageable);

        List<User> result = users.getContent();
        long totalMatches = users.getTotalElements();

        // ... Page has many other methods on it ...
        // end::findByLastNameStartsWithPageable[]

        assertThat(users.getTotalElements()).isEqualTo(2);
    }

    @Test
    public void findByLastNameStartsWithSort() {
        // tag::findByLastNameStartsWithSort[]
        Sort sort =
            new Sort(Direction.ASC, "firstName");
        List<User> users =
            repository.findByLastNameStartsWith("W", sort);

        long totalMatches = users.size();

        // ... use users ...
        // end::findByLastNameStartsWithSort[]

        assertThat(totalMatches).isEqualTo(2);
    }
}
