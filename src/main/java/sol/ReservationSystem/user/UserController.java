package sol.ReservationSystem.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import sol.ReservationSystem.user.dto.UserModifyDto;
import sol.ReservationSystem.user.dto.UserPassUpdateDto;
import sol.ReservationSystem.user.dto.UserSignUpDto;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UtilMethods utilMethods;

    //회원가입
    @PostMapping("/signUp")
    public ResponseEntity registerUser(@RequestBody UserSignUpDto userSignUpDto){
        userService.saveUser(userSignUpDto);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully saved");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //이름, 설명 업데이트
    @PostMapping("/update")
    public ResponseEntity modifyUseInfo(@RequestBody UserModifyDto userModifyDto, HttpServletRequest request) {
        userService.updateUserInfo(userModifyDto, request);
        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully updated");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //이미지 업데이트
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity imageUpdate(@RequestPart MultipartFile profileImage, HttpServletRequest request) {
        userService.updateProfileImage(profileImage, request);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully updated");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //비밀번호 업데이트
    @PostMapping("/password")
    public ResponseEntity pwUpdate(@RequestBody UserPassUpdateDto userPassUpdateDto, HttpServletRequest request) {
        userService.updatePassword(userPassUpdateDto, request);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully updated");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
