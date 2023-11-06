package dao;

import dto.Class_jy;
import dto.Lesson_jy;
import org.apache.ibatis.session.SqlSession;
import repository.mapper.ClassMapper_jy;
import repository.mapper.LessonMapper_jy;

import java.util.ArrayList;

public class LessonDAO_jy {
    /*
    private int lessonIdx;
    private String lessonName;
    private int classId;
    private int lessonsSecond;
    */

    // DB 연결
    SqlSession session;
    LessonMapper_jy mapper;

    // 생성자
    public LessonDAO_jy(){
        this.session = factory.MyBatisMapperFactory.getSqlSession();
        mapper = session.getMapper(LessonMapper_jy.class);
    }

    // 학습 생성
    public void insertLesson (String lessonName, int classId, int lessonsSeconds) {
        Lesson_jy lessonJy = new Lesson_jy(lessonName, classId, lessonsSeconds);
        mapper.insertLesson(lessonJy);
        session.commit();
    }

    // 학습 조회
    // all: 특정 수업의 모든 학습 조회
    public ArrayList<Lesson_jy> getLessons (int classIdx) {
        ArrayList<Lesson_jy> lessonsList = mapper.getLessons(classIdx);

        return lessonsList;
    }
    // single: 학습 한 개 조회
    public Lesson_jy getLesson (int lessonIdx){
        // lessonIdx를 인덱스로 갖는 햑습 한 개
        Lesson_jy lessonJy = mapper.getLesson(lessonIdx);

        return lessonJy;
    }

    // 학습 수정
    public void updateLesson (String lessonName, int lessonsSeconds){
        // 학습 이름, 학습 시간 변경
        Lesson_jy lessonJy = new Lesson_jy(lessonName, lessonsSeconds);
        mapper.updateLesson(lessonJy);
        session.commit();
    }

    // 학습 삭제
    public void deleteLesson (int lessonIdx){
        mapper.deleteLesson(lessonIdx);
        session.commit();
    }
}
