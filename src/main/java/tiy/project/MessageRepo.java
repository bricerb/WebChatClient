package tiy.project;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brice on 9/23/16.
 */

public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findByUser(User user);
    List<Message> findAllByOrderById();
}