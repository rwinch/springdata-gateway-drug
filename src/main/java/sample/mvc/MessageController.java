package sample.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.data.MessageRepository;
import sample.domain.Message;

@Controller
@RequestMapping("/mvc")
public class MessageController {
    private MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RequestMapping
    public ResponseEntity<Iterable<Message>> list() {
        Iterable<Message> messages = messageRepository.findAll();
        return new ResponseEntity<Iterable<Message>>(messages, HttpStatus.OK);
    }

    @RequestMapping("{id}")
    public ResponseEntity<Message> view(@PathVariable("id") Message message) {
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }


    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Message> save(@Valid Message message, BindingResult result) {
        messageRepository.save(message);
        return new ResponseEntity<Message>(message, HttpStatus.CREATED);
    }
}
