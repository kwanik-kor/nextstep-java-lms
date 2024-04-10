package nextstep.qna.service;

import nextstep.qna.NotFoundException;
import nextstep.qna.domain.answer.AnswerRepository;
import nextstep.qna.domain.question.Question;
import nextstep.qna.domain.question.QuestionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service("qnaService")
public class QnAService {
    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerRepository")
    private AnswerRepository answerRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional
    public void deleteQuestion(NsUser loginUser, long questionId) {
        final LocalDateTime requestDatetime = LocalDateTime.now();

        Question question = questionRepository.findById(questionId).orElseThrow(NotFoundException::new);

        deleteHistoryService.saveAll(question.delete(loginUser, requestDatetime));
    }
}
