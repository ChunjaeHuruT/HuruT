package repository.mapper;

import dto.Class_jy;

import java.util.ArrayList;

public interface ClassMapper_jy {
    public void insertClass (Class_jy aClassJy);
    public ArrayList<Class_jy> getClasses(int teacherId);
    public Class_jy getClass (int classIdx);
    public void updateClass (Class_jy aClassJy);
    public void deleteClass (int classIdx);
    public void updateClassByLesson(Class_jy aClassJy);
}
