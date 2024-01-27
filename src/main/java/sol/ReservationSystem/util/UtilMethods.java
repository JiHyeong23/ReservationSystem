package sol.ReservationSystem.util;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import sol.ReservationSystem.comment.Comment;
import sol.ReservationSystem.comment.CommentRepository;
import sol.ReservationSystem.post.Post;
import sol.ReservationSystem.post.PostRepository;
import sol.ReservationSystem.security.JwtHelper;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.user.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Component
@AllArgsConstructor
public class UtilMethods {
    private JwtHelper jwtHelper;
    private UserRepository userRepository;
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public User parseTokenForUser(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            String email = jwtHelper.getEmailFromJwtToken(token);
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "토큰이 없습니다");
        }
    }

    public ResponseDto makeSuccessResponseDto(String message) {
        return ResponseDto.builder()
                .result(Result.SUCCESS).message(message)
                .build();
    }

    public <T>ResponseDto makeSuccessResponseDto(String message, T response) {
        return ResponseDto.builder()
                .result(Result.SUCCESS).message(message).response(response)
                .build();
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId).get();
    }
    public User findUser(String email) {return userRepository.findByEmail(email);}
    public User findUser(Long userId) {return userRepository.findById(userId).get();}
    public Comment findComment(Long commentId) {return commentRepository.findById(commentId).get();}

}
