package sol.ReservationSystem.userActivity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ReservationSystem.user.User;
import sol.ReservationSystem.user.dto.UserNewsDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserActivityService {
    private UtilMethods utilMethods;
    private UserActivityRepository userActivityRepository;

    public List<UserNewsDto> getNews(HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        List<User> following = utilMethods.getFollowing(user);
        List<UserActivity> activities = userActivityRepository.findTop10ByUserInOrderByCreatedAtDesc(following);
        List<UserNewsDto> newsFeed = new ArrayList<>();
        for (UserActivity activity : activities) {
            if (activity.getContentedBy() == user) {
                newsFeed.add(new UserNewsDto(
                        activity.getUser().getName(), activity.getActivity().toString(), activity.getActivityId(), activity.getCreatedAt(),
                        user.getName(), "My")
                );
            } else {
                newsFeed.add(new UserNewsDto(
                        activity.getUser().getName(), activity.getActivity().toString(), activity.getActivityId(), activity.getCreatedAt(),
                        activity.getContentedBy().getName(), "Follower")
                );
            }
        }
        return newsFeed;
    }
}
