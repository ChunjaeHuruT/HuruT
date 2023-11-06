package service;

import dto.Class_jy;
import dto.Teacher_jy;
import main.HuruTMain;
import org.apache.ibatis.session.SqlSession;
import repository.mapper.ClassMapper_jy;

import java.util.ArrayList;

public class ClassService_jy {
    // DAO
    private SqlSession sqlSession;
    private ClassMapper_jy mapper;
    // 로그인 된 선생님 객체 인스턴스
    Teacher_jy teacherJy;

    // 생성자
    public ClassService_jy(){
        this.sqlSession = factory.MyBatisMapperFactory.getSqlSession();
        mapper = sqlSession.getMapper(ClassMapper_jy.class);
        teacherJy = HuruTMain.getTeacherJy();
    }

    // 수업 등록
    public void insertClass (String className, int price, int difficulty) {
        // 수업명, 선생님 인덱스, 가격, 난이도 입력
        Class_jy aClassJy = new Class_jy(className, teacherJy.getTeacherIdx(), price, difficulty);
        mapper.insertClass(aClassJy);
        sqlSession.commit();
    }

    // 담당 수업 모두 조회
    public ArrayList<Class_jy> getClasses() {
        ArrayList<Class_jy> classesList = mapper.getClasses(teacherJy.getTeacherIdx());

        return classesList;
    }

    // 담당 수업 하나 조회
    // classIdx -> class 객체
    public Class_jy getClass(int classIdx) {
        // classIdx를 인덱스로 갖는 수업 한 개
        Class_jy aClassJy = mapper.getClass(classIdx);

        return aClassJy;
    }

    // 수업 수정
    public void updateClass (int classIdx, String className, int price, int difficulty) {
        // 수업명, 선생님 인덱스, 가격, 난이도 수정
        Class_jy aClassJy = new Class_jy(classIdx, className, teacherJy.getTeacherIdx(), price, difficulty);
        mapper.updateClass(aClassJy);
        sqlSession.commit();
    }

    // 수업 수정: 학습 개수, 학습 총 시간
    // 학습이 새로 등록되었을 때, 수업이 수정된다.
    public void updateClassByLesson(int classIdx, int lectureCnt, int seconds){
        Class_jy aClassJy = new Class_jy(classIdx, lectureCnt, seconds);
        mapper.updateClassByLesson(aClassJy);
        sqlSession.commit();
    }

    // 수업 삭제
    public void deleteClass (int classIdx){
        mapper.deleteClass(classIdx);
        sqlSession.commit();
    }
}
