package sol.ReservationSystem.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sol.ReservationSystem.user.dto.UserModifyDto;
import sol.ReservationSystem.user.dto.UserPassUpdateDto;
import sol.ReservationSystem.user.dto.UserSignUpDto;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;
import sol.ReservationSystem.validation.ValidationSequence;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UtilMethods utilMethods;

    //회원가입
    @PostMapping("/signUp")
    public ResponseEntity registerUser(@Validated(ValidationSequence.class) @RequestBody UserSignUpDto userSignUpDto){
        ResponseDto responseDto = userService.saveUser(userSignUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //이름, 설명 업데이트
    @PostMapping("/update")
    public ResponseEntity modifyUseInfo(@RequestBody UserModifyDto userModifyDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        ResponseDto responseDto = userService.updateUserInfo(userModifyDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //이미지 업데이트
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity imageUpdate(@RequestPart MultipartFile profileImage, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        ResponseDto responseDto = userService.updateProfileImage(profileImage, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //비밀번호 업데이트
    @PostMapping("/password")
    public ResponseEntity pwUpdate(@RequestBody UserPassUpdateDto userPassUpdateDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        ResponseDto responseDto = userService.updatePassword(userPassUpdateDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

//    @GetMapping("/new")
//    public ResponseEntity getNews
}
