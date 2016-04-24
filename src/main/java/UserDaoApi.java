import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dexp on 27.04.2015.
 */
public interface UserDaoApi {
    void createUserDAO(User user);

    User getU(String username);
}
