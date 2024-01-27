package sol.ReservationSystem.commentLike;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.ReservationSystem.commentLike.dto.CommentLikeDto;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/commentLikes")
@AllArgsConstructor
public class CommentLikeController {
    private UtilMethods utilMethods;
    private CommentLikeService commentLikeService;
    @PostMapping
    public ResponseEntity commentLike(@RequestBody CommentLikeDto commentLikeDto, HttpServletRequest request) {
        CommentLike commentLike = commentLikeService.saveCommentLike(commentLikeDto, request);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully saved", commentLike.getId());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
