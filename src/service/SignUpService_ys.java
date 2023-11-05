package service;

import dao.StudentDAO;
import dao.TeacherDAO;
import factory.MyBatisMapperFactory;
import org.apache.ibatis.session.SqlSession;
import repository.mapper.StudentMapper_ys;
import repository.mapper.TeacherMapper_ys;


public class SignUpService_ys {

    // 학생 회원가입
    public void studentSignUp(String studentEmail, String studentPassword, String studentNickname) {
        SqlSession sqlSession = MyBatisMapperFactory.getSqlSession();
        StudentMapper_ys mapper = sqlSession.getMapper(StudentMapper_ys.class);
        StudentDAO studentDAO = new StudentDAO(studentEmail, studentPassword, studentNickname);
        mapper.studentSignUp(studentDAO);

        sqlSession.commit();
    }

    // 선생님 회원가입
    public void teacherSignUp(String teacherEmail, String teacherPassword, String teacherName) {
        SqlSession sqlSession = MyBatisMapperFactory.getSqlSession();
        TeacherMapper_ys mapper = sqlSession.getMapper(TeacherMapper_ys.class);
        TeacherDAO teacherDAO = new TeacherDAO(teacherEmail, teacherPassword, teacherName);
        mapper.teacherSignUp(teacherDAO);

        sqlSession.commit();
    }

    public int studentEmailDuplicationCheck(String studentEmail) {
        SqlSession sqlSession = MyBatisMapperFactory.getSqlSession();
        StudentMapper_ys mapper = sqlSession.getMapper(StudentMapper_ys.class);
        int studentEmailDuplicationCheckDAO = mapper.studentEmailDuplicationCheck(studentEmail);

        return studentEmailDuplicationCheckDAO;
    }

    public int teacherEmailDuplicationCheck(String teacherEmail) {
        SqlSession sqlSession = MyBatisMapperFactory.getSqlSession();
        TeacherMapper_ys mapper = sqlSession.getMapper(TeacherMapper_ys.class);
        int teacherEmailDuplicationCheckDAO = mapper.teacherEmailDuplicationCheck(teacherEmail);

        return teacherEmailDuplicationCheckDAO;
    }
}


