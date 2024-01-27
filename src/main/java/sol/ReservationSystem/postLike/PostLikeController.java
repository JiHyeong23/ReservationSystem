package sol.ReservationSystem.postLike;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.ReservationSystem.postLike.dto.PostLikeDto;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/postLikes")
@AllArgsConstructor
public class PostLikeController {
    private UtilMethods utilMethods;
    private PostLikeService postLikeService;

    @PostMapping
    public ResponseEntity likePost(@RequestBody PostLikeDto postLikeDto, HttpServletRequest request) {
        PostLike postLike = postLikeService.savePostLike(postLikeDto, request);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully saved", postLike.getId());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
