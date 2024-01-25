package sol.ReservationSystem.email;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sol.ReservationSystem.util.UtilMethods;

import javax.validation.Valid;

@RequestMapping("/mail")
@RestController
@AllArgsConstructor
public class EmailController {
    private EmailService emailService;
    private UtilMethods utilMethods;
    @PostMapping("/valid")
    public ResponseEntity sendMail(@RequestBody @Valid EmailDto emailDto) {
        emailService.sendMail(emailDto);
        utilMethods.makeSuccessResponseDto("Successfully send");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}