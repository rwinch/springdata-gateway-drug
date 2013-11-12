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
package sample.data.basic;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import sample.data.AbstractSpringTest;
import sample.domain.User;

public class BasicUserRepositoryTest extends AbstractSpringTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void findOne() {
        // tag::findOne[]
        User user =
            repository.findOne(0L);
        // end::findOne[]

        assertThat(user.getId()).isEqualTo(0L);
    }

    @Test
    public void save() {
        // tag::save[]
        User toSave = new User();
        toSave.setEmail("tosave@example.com");
        toSave.setFirstName("Two");
        toSave.setLastName("Save");

        // create the user
        User saved = repository.save(toSave);

        // fix the firstName
        saved.setFirstName("To");

        // update the user
        saved = repository.save(saved);
        // end::save[]

        assertThat(saved.getFirstName()).isEqualTo("To");
    }
}
