import com.sun.org.apache.xpath.internal.operations.Equals;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.*;

public class UserTest {

    private final User user = new User("ggg", "hhh", "yyy");

    @Test
    public void canGetUsername() throws Exception {
        assertEquals("ggg", user.getUsername());
    }

    @Test
    public void canGetAuthority() throws Exception {
        final Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1,authorities.size());
        final Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        final GrantedAuthority authority = iterator.next();
        assertEquals("yyy",authority.getAuthority());
    }

    @Test
    public void canGetAuthorityTWO() throws Exception {
        final User user1 = new User("uuu", "hhh", "jjj,ggg");
        final Collection<? extends GrantedAuthority> authorities = user1.getAuthorities();
        assertEquals(2,authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("jjj")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ggg")));
    }


    @Test
    public void canGetErrorAuthority() throws Exception {
        final User user1 = new User("uuu", "hhh", ", ,,,     ,,jjj,,  ,,ggg,,,,    ,,,, ,,,,");
        final Collection<? extends GrantedAuthority> authorities = user1.getAuthorities();
        assertEquals(2,authorities.size());

    }
}