package sol.ReservationSystem.commentLike;

import lombok.Getter;
import sol.ReservationSystem.comment.Comment;
import sol.ReservationSystem.user.User;

import javax.persistence.*;

@Entity
@Getter
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public void setUser(User user) {
        this.user = user;
    }
    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
