package com.example.auth;

//import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

@Repository
public class OAuthDao {

   // @Autowired
   // private JdbcTemplate jdbcTemplate;

   public UserEntity getUserDetails(String username) {

      Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
      grantedAuthoritiesList.add(grantedAuthority);

      //1, 'tutorialspoint@gmail.com','$2a$08$fL7u5xcvsZl78su29x1ti.dxI.9rYO8t0q5wk2ROJ.1cdR53bmaVG');
      UserEntity user = new UserEntity();
      user.setUsername("tutorialspoint@gmail.com");
      user.setPassword("$2a$08$fL7u5xcvsZl78su29x1ti.dxI.9rYO8t0q5wk2ROJ.1cdR53bmaV");
      user.setGrantedAuthoritiesList(grantedAuthoritiesList);
      return user;

      // String userSQLQuery = "SELECT * FROM USERS WHERE USERNAME=?";

      // List<UserEntity> list = jdbcTemplate.query(userSQLQuery, new String[] {
      // username },
      // (ResultSet rs, int rowNum) -> {

      // UserEntity user = new UserEntity();
      // user.setUsername(username);
      // user.setPassword(rs.getString("PASSWORD"));
      // return user;
      // });

      // if (list.size() > 0) {
      // GrantedAuthority grantedAuthority = new
      // SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
      // grantedAuthoritiesList.add(grantedAuthority);
      // list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
      // return list.get(0);
      // }
      // return null;
   }
}