package sol.ReservationSystem.follow;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ReservationSystem.follow.dto.FollowUserDto;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.userActivity.Activity;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@AllArgsConstructor
public class FollowService {
    private UtilMethods utilMethods;
    private FollowRepository followRepository;

    public Follow saveFollow(FollowUserDto followUserDto, HttpServletRequest request) {
        User follower = utilMethods.parseTokenForUser(request);
        User following = utilMethods.findUser(followUserDto.getEmail());

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);

        followRepository.save(follow);

        utilMethods.saveActivity(follower, Activity.FOLLOW, following.getId(), follower);

        return follow;
    }
}
