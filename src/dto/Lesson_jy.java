package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Lesson_jy {
    private int lessonIdx;
    private String lessonName;
    private int classId;
    private int lessonsSeconds;

    public Lesson_jy(String lessonName, int lessonsSeconds){
        this.lessonName = lessonName;
        this.lessonsSeconds = lessonsSeconds;
    }

    public Lesson_jy(String lessonName, int classId, int lessonsSeconds){
        this.lessonName = lessonName;
        this.classId = classId;
        this.lessonsSeconds = lessonsSeconds;
    }
}
