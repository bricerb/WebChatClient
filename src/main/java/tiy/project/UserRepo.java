package tiy.project;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brice on 9/23/16.
 */

//public interface UserRepo extends CrudRepository<User, Integer> {
//    List<User> findAllByOrderById();
//}

public interface UserRepo extends CrudRepository<User, Integer> {
    User findFirstByName(String name);
}