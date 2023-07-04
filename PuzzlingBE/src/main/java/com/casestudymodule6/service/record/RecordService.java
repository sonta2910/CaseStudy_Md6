package com.casestudymodule6.service.record;

import com.casestudymodule6.model.dto.LeaderDTO;
import com.casestudymodule6.model.record.Answer;
import com.casestudymodule6.model.record.Record;
import com.casestudymodule6.model.record.RecordDetail;
import com.casestudymodule6.model.user.User;
import com.casestudymodule6.repository.IRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService implements IRecordService
{
    @Autowired
    private IRecordRepository recordRepository;


    @Override
    public Iterable<Record> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public Optional<Record> findById(Long id) {
        return recordRepository.findById(id);
    }

    @Override
    public Record save(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public void remove(Long id)
    {
       recordRepository.deleteById(id);
    }


    @Override
    public int scoreSumOfUser(Iterable<RecordDetail> recordDetails)
    {
        int scoreOfUser = 0;

        Outer:for (RecordDetail rs: recordDetails)
        {
            for (Answer answer: rs.getAnswers())
            {
                if (!answer.getOption().getStatus().equals(answer.getAnswerStatus()))
                {
                    continue Outer;
                }
            }
            scoreOfUser+=rs.getQuestion().getLevel().getScore();
        }
        return scoreOfUser;
    }

    @Override
    public List<LeaderDTO> findAllRecordByExam(Long examId) {
        return recordRepository.findAllRecordByExam(examId);
    }

    @Override
    public Iterable<Record> findRecordByUser(User user) {
        return recordRepository.findRecordByUser(user);
    }

    @Override
    public List<LeaderDTO> findAllRecordByPermaExam(String permaExamName, Long userId) {
        return recordRepository.findAllRecordByPermaExam(permaExamName, userId);
    }


}
