package sol.ReservationSystem.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ValidationGroups.EmailGroup.class, ValidationGroups.PatternCheckGroup.class, })
public interface ValidationSequence {
}
