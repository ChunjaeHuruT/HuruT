package service;

import dto.Lesson_jy;
import org.apache.ibatis.session.SqlSession;
import repository.mapper.LessonMapper_jy;

import java.util.ArrayList;
import java.util.Objects;

public class LessonService_jy {
    // DAO
    SqlSession sqlSession;
    LessonMapper_jy mapper;


    // 생성자
    public LessonService_jy(){
        this.sqlSession = factory.MyBatisMapperFactory.getSqlSession();
        mapper = sqlSession.getMapper(LessonMapper_jy.class);
    }

    // 학습 생성
    public void insertLesson (String lessonName, int classId, int lessonsSeconds){
        Lesson_jy lessonJy = new Lesson_jy(lessonName, classId, lessonsSeconds);
        mapper.insertLesson(lessonJy);
        sqlSession.commit();
    }

    // 학습 조회
    // classIdx 수업의 학습 전체 리턴
    public ArrayList<Lesson_jy> getLessons (int classId) {
        ArrayList<Lesson_jy> lessonsList = mapper.getLessons(classId);

        return lessonsList;
    }

    // 학습 한 개 리턴
    public Lesson_jy getLesson (int lessonIdx) {
        Lesson_jy lesson = mapper.getLesson(lessonIdx);

        return lesson;
    }

    // 학습 삭제
    public void deleteLesson(int lessonIdx){
        mapper.deleteLesson(lessonIdx);
        sqlSession.commit();
    }

    // 학습의 수업 idx 조회
    // lessonIdx를 가진 classIdx 리턴
    public int getClassIdx(int lessonIdx){
        Integer classIdx = mapper.getClassIdx(lessonIdx);

        if(Objects.isNull(classIdx)){ // lessonIdx인 학습lesson을 가진 수업class가 존재하지 않는다.
            return -1;
        }else{
            return classIdx.intValue(); // lessonIdx인 학습lesson을 가진 수업class의 classIdx 리턴
        }
    }
}
