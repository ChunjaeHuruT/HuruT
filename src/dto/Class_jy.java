package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Class_jy {
    private int classIdx;
    private String className;
    private int teacherId;
    private int price;
    private int studentCnt;
    private double rating;
    private int lectureCnt;
    private int seconds;
    private int difficulty;

    public Class_jy(int classIdx, int lectureCnt, int seconds){
        this.classIdx = classIdx;
        this.lectureCnt = lectureCnt;
        this.seconds = seconds;
    }

    public Class_jy(String className, int teacherId, int price, int difficulty){
        this.className = className;
        this.teacherId = teacherId;
        this.price = price;
        this.difficulty = difficulty;
    }

    public Class_jy(int classIdx, String className, int teacherId, int price, int difficulty){
        this.classIdx = classIdx;
        this.className = className;
        this.teacherId = teacherId;
        this.price = price;
        this.difficulty = difficulty;
    }
}
