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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import sample.data.MessageRepository;
import sample.data.UserRepository;
import sample.domain.Message;
import sample.domain.User;

public class StandaloneMessageRepositoryTest {
    private MessageRepository messageRepository;

    private UserRepository userRepository;

    private EntityManager em;

    @Before
    public void setup() {
        // tag::setup[]
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager =
            emf.createEntityManager();
        RepositoryFactorySupport factory =
            new JpaRepositoryFactory(entityManager);

        MessageRepository messageRepository =
            factory.getRepository(MessageRepository.class);
        // end::setup[]

        UserRepository userRepository =
            factory.getRepository(UserRepository.class);

        this.em = entityManager;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @After
    public void cleanup() {
        if(em != null) {
            em.close();
        }
    }


    /**
     * Of course we could use Managed transactions in our test if we were using
     * something like Spring or <a href="http://arquillian.org/">Aquillian</a>. However,
     * this demonstrates that is possible to use Spring Data without a container.
     */
    @Test
    public void standaloneUsage() {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            User to = new User();
            to.setEmail("email@example.com");

            to = userRepository.save(to);

            Message m = new Message();
            m.setText("Message");
            m.setTo(to);

            messageRepository.save(m);

            messageRepository.flush();

            tx.commit();
        }
        catch (RuntimeException e) {
            if ( tx != null && tx.isActive() ) tx.rollback();
            throw e;
        }

        assertThat(messageRepository.findAll().size()).isEqualTo(1);
    }
}
