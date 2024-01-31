package sol.ReservationSystem.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ReservationSystem.post.dto.PostCreationDto;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.userActivity.Activity;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class PostService {
    private UtilMethods utilMethods;
    private PostRepository postRepository;
    private PostMapper postMapper;

    public ResponseDto savePost(PostCreationDto postCreationDto, User user) {
        Post post = postMapper.creationDtoToPost(postCreationDto);
        post.setUser(user);
        postRepository.save(post);

        utilMethods.saveActivity(user, Activity.POST, post.getId(), user);

        return utilMethods.makeSuccessResponseDto("Successfully saved", post.getTitle());
    }
}
