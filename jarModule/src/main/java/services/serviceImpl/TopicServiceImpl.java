package services.serviceImpl;

import dao.TopicDAO;
import model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.BaseService;
import services.TopicService;

import java.util.List;

@Service
public class TopicServiceImpl extends BaseService<Topic> implements TopicService {

    @Autowired
    private TopicDAO topicDAOService;

    @Override
    public List<Topic> getAll() {

        return topicDAOService.getAll();
    }

    @Override
    public Topic getByName(String name) {

        return topicDAOService.getByName(name);
    }
}
