package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Student_jy {
    private int studentIdx;
    private String studentEmail;
    private String studentNickname;
    private String studentPassword;
    private LocalDateTime studentJoined;
    private int studentPoints;
}
