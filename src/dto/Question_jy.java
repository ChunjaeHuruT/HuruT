package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Question_jy {
    private int questionIdx;
    private String title;
    private String contents;
    private int studentId;
    private int classId;
    private LocalDateTime questionDate;
}
