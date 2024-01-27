package sol.ReservationSystem.follow;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.ReservationSystem.follow.dto.FollowUserDto;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/follows")
@AllArgsConstructor
public class FollowController {
    private FollowService followService;
    private UtilMethods utilMethods;
    @PostMapping
    public ResponseEntity followUser(@RequestBody FollowUserDto followUserDto, HttpServletRequest request) {
        Follow follow = followService.saveFollow(followUserDto, request);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully saved", follow.getFollowing().getName());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}