package sol.ReservationSystem.post;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.ReservationSystem.post.dto.PostCreationDto;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private UtilMethods utilMethods;
    private PostService postService;
    @PostMapping
    public ResponseEntity createPost(@RequestBody PostCreationDto postCreationDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        ResponseDto responseDto = postService.savePost(postCreationDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
