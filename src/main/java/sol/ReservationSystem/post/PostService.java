package sol.ReservationSystem.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ReservationSystem.post.dto.PostCreationDto;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class PostService {
    private UtilMethods utilMethods;
    private PostRepository postRepository;
    private PostMapper postMapper;

    public Post savePost(PostCreationDto postCreationDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        Post post = postMapper.creationDtoToPost(postCreationDto);
        post.setUser(user);
        postRepository.save(post);

        return post;
    }
}
