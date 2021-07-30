package com.friday.jpaauthentication;

import com.friday.jpaauthentication.entity.DefaultMyUserDetails;
import com.friday.jpaauthentication.entity.User;
import com.friday.jpaauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultMyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(userName);
        }
        // convert user to type MyUserDetails
        return new DefaultMyUserDetails(user.get());
    }

}
