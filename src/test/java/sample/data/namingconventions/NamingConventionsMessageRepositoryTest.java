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

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import sample.data.AbstractSpringTest;
import sample.domain.User;

public class NamingConventionsMessageRepositoryTest extends AbstractSpringTest {
    @Autowired
    private UserRepository repsitory;

    @Test
    public void findByEmailAndLastName() {
        List<User> users = repsitory.findByFirstNameAndLastName("Rob","Winch");

        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void findByLastNameStartsWith() {
        // tag::findByLastNameStartsWith[]
        // finds Users with lastName that starts
        // with "W"
        List<User> users = repsitory.findByLastNameStartsWith("W");
        // end::findByLastNameStartsWith[]

        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void findByLastNameLike() {
        // tag::findByLastNameLike[]
        // finds Users with lastName that starts
        // with "W" and ends with "h"
        List<User> users = repsitory.findByLastNameLike("W%h");
        // end::findByLastNameLike[]

        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void findByLastNameIgnoreCase() {
        assertThat(repsitory.findByLastNameIgnoreCase("WINCH").size()).isEqualTo(1);
    }

    @Test
    public void findByFirstNameAndLastNameAllIgnoreCase() {
        assertThat(repsitory.findByFirstNameAndLastNameAllIgnoreCase("rob","WINCH").size()).isEqualTo(1);
    }
}
