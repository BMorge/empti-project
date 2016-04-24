import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.jdbc.core.RowMapper;



import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.*;

/**
 * Created by Dexp on 30.03.2015.
 */
public class UserDAO implements UserDaoApi {
    @Autowired
    private DataSource dataSource;

//@PostConstruct
//public void initDB(){
//    User usert =   new User("admin0", "0", "ROLE_USER");
//    createUserDAO(usert);
//}

    @PostConstruct
    public  void InitDB(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("CREATE TABLE USERS (USERNAME VARCHAR(10) NOT NULL,PASSWORD VARCHAR(30) NOT NULL,      ENABLED SMALLINT DEFAULT '1',        PRIMARY KEY (USERNAME));");
        jdbcTemplate.update("CREATE TABLE AUTHORITIES (USERNAME VARCHAR(10) NOT NULL, AUTHORITY VARCHAR(30) NOT NULL,  FOREIGN KEY (USERNAME) REFERENCES USERS(USERNAME));");
        User usert =   new User("admin0", "0", "ROLE_USER, ROLE_ADMIN");
        createUserDAO(usert);
    }
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void createUserDAO(User user) {
        String queryUser = "insert into USERS (USERNAME, PASSWORD) values (?,?)";
        String queryAUT = "insert into AUTHORITIES (USERNAME, AUTHORITY) values (?,?)";
        Collection<? extends GrantedAuthority> authorities;
        authorities=user.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        String Z = new String();
        while (iterator.hasNext()){
            GrantedAuthority authority = iterator.next();
            Z=Z+","+authority.getAuthority();
        }
         JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(queryUser, user.getUsername(), user.getPassword());
        jdbcTemplate.update(queryAUT, user.getUsername(), Z);
        }
    @Override
    public User getU(String username){
        String queryUser = "select USERS.PASSWORD from USERS where USERS.USERNAME=?";
        String queryAUT = "select AUTHORITIES.AUTHORITY from AUTHORITIES where AUTHORITIES.USERNAME=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        User user = new User("","","");
        List<String> pass;
        List<String> aut;
        pass = jdbcTemplate.queryForList(queryUser, String.class,username);
        aut=jdbcTemplate.queryForList(queryAUT, String.class,username);
        user.setUsername(username);
        Integer j =pass.size();
        Integer jj=aut.size();
        if (pass.isEmpty())
        {
            return null;
        }
        if (j ==1)
        {
        user.setPassword(pass.get(0));

            if (aut.isEmpty())
            {
                throw new RuntimeException("Not found auth, wtf");
            }
            if (jj==1)
            {
            user.setRoles(aut.get(0));
            }
            else
            {
                throw new RuntimeException("Load more auth then one");
            }
        }
        else
        {
            throw new RuntimeException("Load more passwords then one");

        }

        return user;

    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
