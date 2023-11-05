package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Teacher_jy {
    private int teacherIdx;
    private String teacherEmail;
    private String teacherName;
    private String teacherPassword;
    private LocalDateTime teacherJoined;
    private int teacherPoints;
    private int classCnt;

}
