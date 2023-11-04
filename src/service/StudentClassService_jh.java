package service;

import factory.MyBatisMapperFactory;
import org.apache.ibatis.session.SqlSession;
import repository.mapper.ClassMapper_jh;
import repository.mapper.LessonMapper_jh;
import repository.mapper.StudentClassMapper_jh;
import repository.mapper.StudentLessonMapper_jh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentClassService_jh {

    public Map<Integer, Map<String, Object>> printListForStudentMainMenuMyClass(int studentIdx) {
        SqlSession sqlSession = MyBatisMapperFactory.getSqlSession();

        // 학생이 수강하고 있는 모든 Class의 PK를 Integer 리스트로 받아오기
        StudentClassMapper_jh studentClassMapper_jh = sqlSession.getMapper(StudentClassMapper_jh.class);
        List<Integer> allMyClassIdxList = studentClassMapper_jh.getAllMyClass(studentIdx);

        Map<Integer, Map<String, Object>> resultMap = new HashMap<>();
        // allMyClassIdxList.forEach(e -> resultMap.put(e, new HashMap<>()));

        // 각 수업 당 총 시간
        ClassMapper_jh classMapper_jh = sqlSession.getMapper(ClassMapper_jh.class);
        List<Map<Integer, Integer>> totalTimePerClassList = classMapper_jh.getTotalTimePerClass(allMyClassIdxList);
        Map<Integer, Integer> totalTimePerClassMap = new HashMap<>();
        for (Map<Integer, Integer> integerIntegerMap : totalTimePerClassList) {
            int classIdx = integerIntegerMap.get("class_idx");
            int seconds = integerIntegerMap.get("seconds");
            totalTimePerClassMap.put(classIdx, seconds);
        }

        // 각 수업에 대한 학습 아이디 갖고오기
        Map<Integer, List<Integer>> lessonIdxListPerClassMap = new HashMap<>();
        LessonMapper_jh lessonMapper_jh = sqlSession.getMapper(LessonMapper_jh.class);
        for (int classIdx : allMyClassIdxList) {
            List<Integer> lessonIdxList = lessonMapper_jh.getAllLessonIdxByClassIdx(classIdx);
            lessonIdxListPerClassMap.put(classIdx, lessonIdxList);
        }

        // 각 수업별로 학생이 수강한 총 시간 구하기
        Set<Integer> classIdxSet = lessonIdxListPerClassMap.keySet();
        Map<Integer, Integer> timeSpentByClassMap = new HashMap<>();
        StudentLessonMapper_jh studentLessonMapper_jh = sqlSession.getMapper(StudentLessonMapper_jh.class);
        for (int classIdx : classIdxSet) {
            Map<String, Object> param = new HashMap<>();
            param.put("studentIdx", studentIdx);
            param.put("lessonIdList", lessonIdxListPerClassMap.get(classIdx));
            int timeSpent = studentLessonMapper_jh.timeSpentPerClassByStudentIdx(param);
            timeSpentByClassMap.put(classIdx, timeSpent);
        }

        // 각 강의에 대한 선생님 가져오기
        List<Map<String, Object>> teacherNameByClassList = classMapper_jh.getTeacherNamePerClass(allMyClassIdxList);
        Map<Integer, String> teacherNamePerClassMap = new HashMap<>();
        for (Map<String, Object> nameByClass : teacherNameByClassList) {
            int classIdx = (Integer) nameByClass.get("class_idx");
            String teacherName = (String) nameByClass.get("teacher_name");
            teacherNamePerClassMap.put(classIdx, teacherName);
        }
        System.out.println();

        // 각 강의에 대한 수업제목 가져오기
        List<Map<String, Object>> classNamePerClassIdxList = classMapper_jh.getClassName(allMyClassIdxList);
        Map<Integer, String> classNamePerClassIdxMap = new HashMap<>();
        for (Map<String, Object> temp : classNamePerClassIdxList) {
            int classIdx = (Integer) temp.get("class_idx");
            String className = (String) temp.get("class_name");
            classNamePerClassIdxMap.put(classIdx, className);
        }
        for (int classIdx : allMyClassIdxList) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("className", classNamePerClassIdxMap.get(classIdx));
            tempMap.put("teacherName", teacherNamePerClassMap.get(classIdx));
            tempMap.put("status", ((((double) timeSpentByClassMap.get(classIdx)) / totalTimePerClassMap.get(classIdx)) * 100));
            resultMap.put(classIdx, tempMap);
        }
        return resultMap;
    }
}
