package br.com.fiap.javabet.user;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        if(userRepository.findByEmail(user.getEmail()).isEmpty()){
            userRepository.save(user);
        }
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        var principal = super.loadUser(userRequest);
        String email = principal.getAttribute("email");
        return userRepository.findByEmail(email).orElseGet(User::new);
    }

}
