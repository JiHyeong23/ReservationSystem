package sol.ReservationSystem.comment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.ReservationSystem.comment.dto.CommentCreationDto;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    private UtilMethods utilMethods;
    private CommentService commentService;
    @PostMapping
    public ResponseEntity createComment(@RequestBody CommentCreationDto commentCreationDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        ResponseDto responseDto = commentService.saveComment(commentCreationDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
