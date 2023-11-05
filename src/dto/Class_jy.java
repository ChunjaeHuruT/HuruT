package dto;

import lombok.Data;

@Data
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

    public Class_jy(String className, int teacherId, int price, int difficulty){
        this.className = className;
        this.teacherId = teacherId;
        this.price = price;
        this.difficulty = difficulty;
    }
}
