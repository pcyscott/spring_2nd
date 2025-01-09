package com.mysite.lect.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public SiteUser create(String username, String email, String password){
        SiteUser user = new SiteUser();
        user.setUserId(username);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        this.userRepository.save(user);
        return user;
    }
}
