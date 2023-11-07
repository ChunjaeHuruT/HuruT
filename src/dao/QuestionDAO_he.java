package dao;

import lombok.Getter;

import java.time.LocalDateTime;

// 기존의 QuestionDAO에 @Getter만 추가함.
@Getter
public class QuestionDAO_he {

    private int questionIdx;
    private String title;
    private String contents;
    private int studentId;
    private int classId;
    private LocalDateTime questionDate;
}
