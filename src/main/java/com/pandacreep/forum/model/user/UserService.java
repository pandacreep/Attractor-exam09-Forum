package com.pandacreep.forum.model.user;

import com.pandacreep.forum.exception.ForumUserExistException;
import com.pandacreep.forum.util.Constants;
import com.pandacreep.forum.util.Image;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public User getByEmailOrNull(String email) {
        var userOpt = userRepository.findByEmail(email);
        if(userOpt.isPresent()) {
            return userOpt.get();
        }
        return null;
    }

    public User save(UserDtoRegister form) throws ForumUserExistException {
        if (isEmailExist(form.getEmail())) {
            String message = "Email " + form.getEmail() + " already exist";
            throw new ForumUserExistException(message);
        }
        var file = form.getAvatar();
        String imageString;
        Image image = Image.getImage(file);
        if (image == null) {
            imageString = Constants.NO_AVATAR;
        } else {
            imageString = Base64.getEncoder().encodeToString(image.getImage().getData());
        }
        var user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(form.getEmail())
                .name(form.getName())
                .password(encoder.encode(form.getPassword()))
                .avatar(imageString)
                .countPosts(0)
                .build();
        return  userRepository.save(user);
    }

    private boolean isEmailExist(String email) {
        var user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

}
