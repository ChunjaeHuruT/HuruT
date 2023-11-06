package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.ArrayList;
import dto.Class_jy;
import repository.mapper.ClassMapper_jy;

public class ClassDAO_jy {
/*    private int classIdx;
    private String className;
    private int teacherId;
    private int price;
    private int studentCnt;
    private double rating;
    private int lectureCnt;
    private int seconds;
    private int difficulty;*/

    // DB 연결
    SqlSession session;
    ClassMapper_jy mapper;

    // 생성자
    public ClassDAO_jy(){
        this.session = factory.MyBatisMapperFactory.getSqlSession();
        mapper = session.getMapper(ClassMapper_jy.class);
    }

    // 수업 생성
    public void insertClass(String className, int teacherId, int price, int difficulty) {
        // 수업명, 선생님 인덱스, 가격, 난이도 입력
        Class_jy aClassJy = new Class_jy(className, teacherId, price, difficulty);
        mapper.insertClass(aClassJy);
        session.commit();
    }

    // 수업 조회
    // all: 담당하는 수업 전부 조회
    public ArrayList<Class_jy> getClasses(int teacherIdx) {
        // teacherId를 인덱스로 갖는 선생님의 수업 전부
        ArrayList<Class_jy> classesList = mapper.getClasses(teacherIdx);

        return classesList;
    }
    // single: 수업 한 개 조회
    public Class_jy getClass (int classIdx){
        // classIdx를 인덱스로 갖는 수업 한 개
        Class_jy aClassJy = mapper.getClass(classIdx);

        return aClassJy;
    }

    // 수업 수정
    public void updateClass(int classIdx, String className, int price, int difficulty, int teacherId){
        // 수업명, 선생님 인덱스, 가격, 난이도 수정
        Class_jy aClassJy = new Class_jy(classIdx, className, teacherId, price, difficulty);
        mapper.updateClass(aClassJy);
        session.commit();
    }

    // 수업 수정: 학습 개수, 학습 총 시간
    // 학습이 새로 등록되었을 때, 수업이 수정된다.
    public void updateClassByLesson(int classIdx, int lectureCnt, int seconds){
        Class_jy aClassJy = new Class_jy(classIdx, lectureCnt, seconds);
        mapper.updateClassByLesson(aClassJy);
        session.commit();
    }

    // 수업 삭제
    public void deleteClass(int classIdx){
        mapper.deleteClass(classIdx);
        session.commit();
    }
}
