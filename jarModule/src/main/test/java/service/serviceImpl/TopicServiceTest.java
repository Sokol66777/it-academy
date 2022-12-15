package service.serviceImpl;

import com.pvt.exceptions.LogicException;
import com.pvt.model.Topic;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.pvt.services.TopicService;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-hibernate.xml")
public class TopicServiceTest {

    private final String TOPIC_NAME = "testTopic";
    private final String NEGATIVE_TOPIC_NAME = "topic1";
    @Autowired
    private TopicService topicService;

    @Test
    @Ignore
    public void getAllTest(){
        assertNotNull(topicService.getAll());
    }


    @Test
    @Ignore
    public void addTest() throws LogicException {
        Topic topic = new Topic();
        topic.setName(TOPIC_NAME);
        topicService.add(topic);
    }

    @Test
    @Ignore
    public void getByNameTest(){
        assertEquals(topicService.getByName(TOPIC_NAME).getName(),TOPIC_NAME);
    }

    @Test
    @Ignore
    public void deleteTest(){
        topicService.delete(topicService.getByName(TOPIC_NAME).getID());
    }

    @Test
    @Ignore
    public void getTest(){
        assertEquals(topicService.get(7).getID(),7);
    }


    @Test
    @Ignore
    public void addNegativeTest() throws LogicException {
        Topic topic = new Topic();
        topic.setName(NEGATIVE_TOPIC_NAME);
        assertThrows(LogicException.class,()->{
            topicService.add(topic);
        });
    }

    @Test
    @Ignore
    public void deleteNegativeTest(){
        assertThrows(InvalidDataAccessApiUsageException.class,()->{
            topicService.delete(8754585);
        });
    }
}
