package sol.ReservationSystem.postLike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ReservationSystem.post.Post;
import sol.ReservationSystem.postLike.dto.PostLikeDto;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.userActivity.Activity;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class PostLikeService {
    private UtilMethods utilMethods;
    private PostLikeRepository postLikeRepository;
    public ResponseDto savePostLike(PostLikeDto postLikeDto, User user) {
        Post post = utilMethods.findPost(postLikeDto.getPostId());

        PostLike postLike = new PostLike();
        postLike.setUser(user);
        postLike.setPost(post);
        postLikeRepository.save(postLike);

        post.updateLikeCount();

        utilMethods.saveActivity(user, Activity.POST_LIKE, postLike.getId(), post.getUser());

        return utilMethods.makeSuccessResponseDto("Successfully saved", postLike.getId());
    }
}
