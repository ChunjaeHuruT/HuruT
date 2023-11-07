package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class Question_jy {
    private int questionIdx;
    private String title;
    private String contents;
    private int studentId;
    private int classId;
    private LocalDateTime questionDate;

    public String getQuestionDateString(){
        return questionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
