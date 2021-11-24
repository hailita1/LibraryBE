package com.example.demo.service.topic;

import com.example.demo.model.Topic;
import com.example.demo.repository.ITopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService implements ITopicService {
    @Autowired
    private ITopicRepository topicRepository;

    @Override
    public Iterable<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Optional<Topic> findById(Long id) {
        return topicRepository.findById(id);
    }

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public void remove(Long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public void deleteList(List<Long> model) {
        if (model != null) {
            for (int i = 0; i < model.size(); i++) {
                topicRepository.deleteById(model.get(i));
            }
        }
    }
}
