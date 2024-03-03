package com.mysite.bootmysql.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.bootmysql.answer.AnswerForm;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionService questionService;

	@GetMapping("/list")
	public String list(Model model) {
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
		return "question_list";
	}

	//등록 페이지 열기
	@GetMapping("/create")
	public String questionCreate() {
		return "question_form";
	}
	//등록 페이지에서 입력한 내용을 받아서 처리 즉 DB 넣기
	@PostMapping("/create")
	public String questionCreate(@RequestParam(value = "subject") String subject,
			@RequestParam(value = "content") String content) {
		this.questionService.create(subject, content);
		return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
	}
	
	@GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id,
    		AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

}
