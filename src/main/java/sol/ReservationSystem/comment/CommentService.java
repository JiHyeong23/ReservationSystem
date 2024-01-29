package sol.ReservationSystem.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ReservationSystem.comment.dto.CommentCreationDto;
import sol.ReservationSystem.post.Post;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.userActivity.Activity;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class CommentService {
    private UtilMethods utilMethods;
    private CommentRepository commentRepository;

    public Comment saveComment(CommentCreationDto commentCreationDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        Post post = utilMethods.findPost(commentCreationDto.getPostId());
        Comment comment = Comment.builder().body(commentCreationDto.getBody()).post(post).user(user).build();
        commentRepository.save(comment);

        utilMethods.saveActivity(user, Activity.COMMENT, comment.getId(), comment.getPost().getUser());

        return comment;
    }
}
