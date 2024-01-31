package sol.ReservationSystem.follow;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ReservationSystem.follow.dto.FollowUserDto;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.userActivity.Activity;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@AllArgsConstructor
public class FollowService {
    private UtilMethods utilMethods;
    private FollowRepository followRepository;

    public ResponseDto saveFollow(FollowUserDto followUserDto, User follower) {
        User following = utilMethods.findUser(followUserDto.getEmail());

        Follow result = followRepository.findByFollowerAndFollowing(follower, following);
        if (result != null) {
            return utilMethods.makeFailResponseDto("이미 팔로우하고 있는 유저입니다", following.getName());
        }
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);

        followRepository.save(follow);

        utilMethods.saveActivity(follower, Activity.FOLLOW, following.getId(), follower);

        return utilMethods.makeSuccessResponseDto("Successfully saved", follow.getFollowing().getName());
    }
}
