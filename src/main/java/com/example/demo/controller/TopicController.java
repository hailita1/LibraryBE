package com.example.demo.controller;

import com.example.demo.model.Topic;
import com.example.demo.service.topic.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private ITopicService topicService;

    @GetMapping
    public ResponseEntity<Iterable<Topic>> getAllTopic() {
        return new ResponseEntity<>(topicService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Topic> createNewTopic(@RequestBody Topic topic) {
        return new ResponseEntity<>(topicService.save(topic), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable Long id) {
        Optional<Topic> topicOptional = topicService.findById(id);
        return topicOptional.map(topic -> new ResponseEntity<>(topic, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
        Optional<Topic> TopicOptional = topicService.findById(id);
        return TopicOptional.map(topic1 -> {
            topic1.setId(topic.getId());
            topic1.setName(topic.getName());
            return new ResponseEntity<>(topicService.save(topic1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable Long id) {
        Optional<Topic> topicOptional = topicService.findById(id);
        return topicOptional.map(topic -> {
            topicService.remove(id);
            return new ResponseEntity<>(topic, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
