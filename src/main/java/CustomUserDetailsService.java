


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    UserDaoApi user;
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        if(user.getU(username)==null)
        {
            throw new UsernameNotFoundException("User not found.srsly");
        }else
        {
            return user.getU(username);
        }


        //return users.get( username );
        }


    //private HashMap<String, User> users;


    public CustomUserDetailsService() {
//        User user1 =   new User("admin0", "0", "ROLE_USER");
//
//
//        UserDaoApi userdao = new UserDAO();
//          userdao.createUserDAO(user1);
////        users = new HashMap<String, User>();
////        users.put("admin0", new User("admin0", "0", "ROLE_USER"));
//        users.put("admin1", new User("admin1", "1", "ROLE_USER, ROLE_ADMIN"));
//       for (int i=2; i<=99;i++){
//            String NM = "admin"+i;
//            String PS = Integer.toString(i);
//            users.put(NM, new User(NM, PS, "ROLE_USER"));
//        }
//

    }


}