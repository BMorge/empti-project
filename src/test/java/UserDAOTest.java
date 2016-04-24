import junit.framework.TestCase;
import com.sun.org.apache.xpath.internal.operations.Equals;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UserDAOTest extends TestCase {

    @Test
    public void testgetuser() throws Exception {
        final User user1 = new User("uuu", "hhh", "jjj");
        final UserDAO userDAO = new UserDAO();
        userDAO.createUserDAO(user1);
        assertEquals(user1, userDAO.getU("uuu"));


    }
}