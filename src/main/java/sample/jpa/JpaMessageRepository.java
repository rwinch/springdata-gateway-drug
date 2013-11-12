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
package sample.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sample.domain.Message;
import sample.domain.User;

// tag::findAll[]
@Repository
@Transactional(readOnly = true)
public class JpaMessageRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Message> findAll() {
        TypedQuery<Message> query =
            em.createQuery("select m from Message m", Message.class);
        return query.getResultList();
    }
    // end::findAll[]

    // tag::save[]
    @Transactional
    public Message save(Message account) {
        if (account.getId() == null) {
            em.persist(account);
            return account;
        } else {
            return em.merge(account);
        }
    }
    // end::save[]

    // tag::findByTo[]
    public List<Message> findByTo(User user) {

        TypedQuery<Message> query = em.createQuery(
                "select m from Message m where m.to = ?1", Message.class);
        query.setParameter(1, user);

        return query.getResultList();
    }
    // end::findByTo[]

    // tag::findAllPaged[]
    public List<Message> findAll(int page, int pageSize) {
        TypedQuery<Message> query =
            em.createQuery("select m from Message m", Message.class);
        int firstResult = page * pageSize;
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
    // end::findAllPaged[]

    // tag::findAllPagedRequestSignature[]
    public PagedResponse<Message> findAll(PagedRequest pagedRequest) {
    // end::findAllPagedRequestSignature[]

        // tag::findAllPagedRequestMessages[]
        int page = pagedRequest.getPageNumber();
        int pageSize = pagedRequest.getPageSize();
        TypedQuery<Message> query =
                em.createQuery("select m from Message m", Message.class);
        int firstResult = page * pageSize;
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);
        List<Message> messages = query.getResultList();

        // ...
        // end::findAllPagedRequestMessages[]

        // tag::findAllPagedRequestTotal[]
        // ...
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery =
            builder.createQuery(Long.class);
        Root<Message> from = countQuery.from(Message.class);
        Expression<Long> count = builder.count(from);
        countQuery.select(count);
        Long total =
            em.createQuery(countQuery).getSingleResult();

        return new PagedResponse<Message>(messages, pagedRequest, total);
    }
    // end::findAllPagedRequestTotal[]
}
