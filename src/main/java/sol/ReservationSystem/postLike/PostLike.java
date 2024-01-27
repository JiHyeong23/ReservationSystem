package sol.ReservationSystem.postLike;

import lombok.Getter;
import sol.ReservationSystem.post.Post;
import sol.ReservationSystem.user.User;

import javax.persistence.*;

@Entity
@Getter
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public void setUser(User user) {
        this.user = user;
    }
    public void setPost(Post post){
        this.post = post;
    }
}
