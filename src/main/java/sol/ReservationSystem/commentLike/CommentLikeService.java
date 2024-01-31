package sol.ReservationSystem.commentLike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ReservationSystem.comment.Comment;
import sol.ReservationSystem.comment.CommentRepository;
import sol.ReservationSystem.commentLike.dto.CommentLikeDto;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.userActivity.Activity;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class CommentLikeService {
    private UtilMethods utilMethods;
    private CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    public ResponseDto saveCommentLike(CommentLikeDto commentLikeDto, User user) {
        Comment comment = utilMethods.findComment(commentLikeDto.getCommentId());

        CommentLike commentLike = new CommentLike();
        commentLike.setUser(user);
        commentLike.setComment(comment);
        commentLikeRepository.save(commentLike);

        comment.updateLikeCount();
        commentRepository.save(comment);

        utilMethods.saveActivity(user, Activity.COMMENT_LIKE, commentLike.getId(), comment.getUser());

        return utilMethods.makeSuccessResponseDto("Successfully saved", commentLike.getId());
    }
}
