package sol.ReservationSystem.user;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import sol.ReservationSystem.user.dto.UserModifyDto;
import sol.ReservationSystem.user.dto.UserPassUpdateDto;
import sol.ReservationSystem.user.dto.UserSignUpDto;
import sol.ReservationSystem.util.FileUploader;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UtilMethods utilMethods;
    private FileUploader fileUploader;
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(username);
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), true, true, true, true, new ArrayList<>());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다");
        }
    }

    public void saveUser(UserSignUpDto userSignUpDto) {
        userSignUpDto.setPassword(encoder.encode(userSignUpDto.getPassword()));
        User user = userMapper.UserSignUpDtoToUser(userSignUpDto);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public void updateUserInfo(UserModifyDto userModifyDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenFotUser(request);
        if (user.getDescription() == null) {
            user.setDescription("");
        }
        user.updateUser(userModifyDto.getName(), userModifyDto.getDescription());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public void updateProfileImage(MultipartFile profileImage, HttpServletRequest request) {
        User user = utilMethods.parseTokenFotUser(request);
        String beforeImage = user.getProfileImage();
        String imagePath = fileUploader.saveImage(profileImage, beforeImage);
        user.setProfileImage(imagePath);
        userRepository.save(user);
    }

    public void updatePassword(UserPassUpdateDto userPassUpdateDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenFotUser(request);
        if (encoder.matches(userPassUpdateDto.getPassword(), user.getPassword())) {
            user.setNewPassword(encoder.encode(userPassUpdateDto.getNewPassword()));
            userRepository.save(user);
        } //예외처리
    }

    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}
