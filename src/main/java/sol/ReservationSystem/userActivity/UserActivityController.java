package sol.ReservationSystem.userActivity;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.ReservationSystem.util.ResponseDto;
import sol.ReservationSystem.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class UserActivityController {
    private UtilMethods utilMethods;
    private UserActivityService userActivityService;

    @GetMapping
    public ResponseEntity getNews(HttpServletRequest request) {
        List<UserNewsDto> news = userActivityService.getNews(request);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully loads", news);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
