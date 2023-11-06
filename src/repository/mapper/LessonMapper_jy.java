package repository.mapper;

import dto.Lesson_jy;

import java.util.ArrayList;

public interface LessonMapper_jy {
    public void insertLesson (Lesson_jy lessonJy);
    public ArrayList<Lesson_jy> getLessons (int classIdx);
    public Lesson_jy getLesson (int lessonIdx);
    public void updateLesson (Lesson_jy lessonJy);
    public void deleteLesson (int lessonIdx);
    public Integer getClassIdx(int lessonIdx);
}
