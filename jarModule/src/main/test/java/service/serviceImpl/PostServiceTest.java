package service.serviceImpl;

import com.pvt.config.SpringConfig;
import com.pvt.exceptions.LogicException;
import com.pvt.model.Post;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.pvt.services.PostService;
import com.pvt.services.TopicService;
import com.pvt.services.UserService;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class PostServiceTest {

    private final String POST_TEXT = "djkvnvnjnjgkjfbg";
    private final String POST_NAME = "testPost";
    private final String UPDATE_POST_TEXT = "new text";

    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private PostService postService;

    @Test
    @Ignore
    public void getAllTest(){
        assertNotNull(postService.getAll());
    }

    @Test
    @Ignore
    public void addTest() throws LogicException {
        Post post = new Post();
        post.setText(POST_TEXT);
        post.setName(POST_NAME);
        post.setUser(userService.get(4L));
        post.setTopic(topicService.get(7L));
        postService.add(post);
    }

    @Test
    @Ignore
    public void getTest(){
        assertEquals(postService.getByName(POST_NAME).getName(),POST_NAME);
    }

    @Test
    @Ignore
    public void getByNameTest(){
        assertEquals(postService.getByName(POST_NAME).getName(),POST_NAME);
    }

    @Test
    @Ignore
    public void updateTest() throws LogicException {
        Post post = postService.getByName(POST_NAME);
        post.setText(UPDATE_POST_TEXT);
        postService.modify(post);
    }

    @Test
    @Ignore
    public void deleteTest(){
        Post post = postService.getByName(POST_NAME);
        postService.delete(post.getID());
    }

    @Test
    @Ignore
    public void getPostsByUserTopicTest(){
        assertNotNull(postService.getPostsByUserTopic(4L,5L));
        List<Post> posts = postService.getPostsByUserTopic(4L,5L);
        System.out.println(posts.toString());
    }

    @Test
    @Ignore
    public void deleteNegativeTest(){
        assertThrows(InvalidDataAccessApiUsageException.class,()->{
            postService.delete(8754585L);
        });
    }

}
