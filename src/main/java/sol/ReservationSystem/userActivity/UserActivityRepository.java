package sol.ReservationSystem.userActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sol.ReservationSystem.user.User;

import java.util.List;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findTop10ByUserInOrderByCreatedAtDesc(List<User> followings);
}
