package sol.ReservationSystem.commentLike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ReservationSystem.comment.Comment;
import sol.ReservationSystem.comment.CommentRepository;
import sol.ReservationSystem.commentLike.dto.CommentLikeDto;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class CommentLikeService {
    private UtilMethods utilMethods;
    private CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    public CommentLike saveCommentLike(CommentLikeDto commentLikeDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        Comment comment = utilMethods.findComment(commentLikeDto.getCommentId());

        CommentLike commentLike = new CommentLike();
        commentLike.setUser(user);
        commentLike.setComment(comment);
        commentLikeRepository.save(commentLike);

        comment.updateLikeCount();
        commentRepository.save(comment);

        return commentLike;
    }
}
