package sample.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import sample.domain.Message;

@RestResource (path = "messages", rel = "messages")
// tag::classdef[]
public interface MessageRepository
      extends JpaRepository<Message, Long> {

    Page<Message> findByToId(@Param("to") Long to, Pageable pageable);

}
// end::classdef[]
