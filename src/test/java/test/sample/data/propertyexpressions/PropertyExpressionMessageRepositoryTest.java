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
package test.sample.data.propertyexpressions;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import sample.domain.Message;
import test.sample.data.AbstractSpringTest;

public class PropertyExpressionMessageRepositoryTest extends AbstractSpringTest {
    @Autowired
    private MessageRepository repsitory;

    @Test
    public void findByLastName() {
        List<Message> messages = repsitory.findByToLastName("Winch");

        assertThat(messages.size()).isEqualTo(10);
    }
}
