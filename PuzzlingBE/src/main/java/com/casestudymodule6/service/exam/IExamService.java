package com.casestudymodule6.service.exam;

import com.casestudymodule6.model.question.Category;
import com.casestudymodule6.model.question.Exam;
import com.casestudymodule6.model.record.PermaExam;
import com.casestudymodule6.model.record.Record;
import com.casestudymodule6.model.user.User;
import com.casestudymodule6.service.IGeneralService;

import java.util.Optional;

public interface IExamService extends IGeneralService<Exam>
{
    Iterable<Exam> findExamsByUser(User user);

    Iterable<Exam> findExamsByCategoryAndUser(Category category, User user);

    Iterable<Exam> findExamsByCategory(Category category);

    int scoreSumOfExam(Record record);
    Optional<Exam> findExamByUserAndName(User user, String name);



    Exam findRandomExam();


}
