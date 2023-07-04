package com.casestudymodule6.controller;

import com.casestudymodule6.model.dto.LeaderDTO;
import com.casestudymodule6.model.question.Exam;
import com.casestudymodule6.model.record.PermaExam;
import com.casestudymodule6.model.record.Record;
import com.casestudymodule6.model.user.Account;
import com.casestudymodule6.model.user.User;
import com.casestudymodule6.repository.IPermaExamRepository;
import com.casestudymodule6.service.exam.IExamService;
import com.casestudymodule6.service.record.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/puzzling/record")
public class RecordController
{

    @Autowired
    private IExamService examService;

    @Autowired
    private IRecordService recordService;

    @PostMapping("/createExamResult/{account}")
    public ResponseEntity<Record> getExamResult(@RequestBody Record record,@PathVariable Account account)
    {
        record.setUser(account.getUser());
        LocalDateTime current=LocalDateTime.now();
        int scoreSumOfExam = examService.scoreSumOfExam(record);
        int scoreSumOfUser = recordService.scoreSumOfUser(record.getRecordDetail());
        record.setExamPoint(scoreSumOfExam);
        record.setUserPoint(scoreSumOfUser);
        record.setTime(current);
        recordService.save(record);
        return new ResponseEntity<>(record,HttpStatus.OK);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<Record> infoRecord(@PathVariable("recordId") Long recordId)
    {
        Optional<Record> optionalRecord = recordService.findById(recordId);
        return new ResponseEntity<>(optionalRecord.get(),HttpStatus.OK);
    }

    @GetMapping("/leaderboard/{examId}")
    public ResponseEntity<List<LeaderDTO>> leaderboard(@PathVariable("examId") PermaExam exam)
    {
            List<LeaderDTO> leaderDTO = recordService.findAllRecordByPermaExam(exam.getName(),exam.getUser().getId());
            return new ResponseEntity<>(leaderDTO,HttpStatus.OK);
    }
    @GetMapping("/leaderboard/owner")
    public ResponseEntity<List<LeaderDTO>> leaderboard(@RequestParam("name") String examName, @RequestParam("account") Account account)
    {
        List<LeaderDTO> leaderDTO = recordService.findAllRecordByPermaExam(examName, account.getUser().getId());
        return new ResponseEntity<>(leaderDTO,HttpStatus.OK);
    }



    @GetMapping("/findRecordByUser/{account}")
    public ResponseEntity<List<Record>> findRecordByUser(@PathVariable("account") Account account)
    {
        List<Record> records = (List<Record>)recordService.findRecordByUser(account.getUser());

        if (records.size() == 0)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(records,HttpStatus.OK);
        }
    }


}
