package pl.admwk.prawko.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.admwk.prawko.PrawkoApplication;
import pl.admwk.prawko.model.Question;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.*;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class QuestionController {

    @GetMapping("/question")
    public String question(Model model) {
        Random rand = new Random();
        Question randomQuestion = PrawkoApplication.questions.get(rand.nextInt(PrawkoApplication.questions.size()));
        model.addAttribute("questionText", randomQuestion.getQuestionText());
        model.addAttribute("answerA", randomQuestion.getAnswers().get('A'));
        model.addAttribute("answerB", randomQuestion.getAnswers().get('B'));
        model.addAttribute("answerC", randomQuestion.getAnswers().get('C'));
        model.addAttribute("isYesNo", randomQuestion.isYesNo());
        model.addAttribute("correctAnswer", randomQuestion.getCorrectAnswer());
        model.addAttribute("file", randomQuestion.getFileName());
        model.addAttribute("fileType", getFileType(randomQuestion.getFileName()));
        return "redirect:/";
    }

    @GetMapping("/getQuestion")
    public ResponseEntity<List<Question>> getQuestion() {

        Random rand = new Random();
        List<Question> questions = new ArrayList<>();
        Set<Integer> usedIndexes = new HashSet<>();

        int basic1 = 0; // 4
        int basic2 = 0; // 6
        int basic3 = 0; // 10
        int special1 = 0; // 2
        int special2 = 0; // 4
        int special3 = 0; // 6
        int randomIndex;

        while (basic1 < 4) {
            randomIndex = rand.nextInt(PrawkoApplication.questionsBasic1.size());
            if (!usedIndexes.contains(randomIndex)) {
                usedIndexes.add(randomIndex);
                questions.add(PrawkoApplication.questionsBasic1.get(randomIndex));
                basic1++;
            }
        }
        usedIndexes = new HashSet<>();
        while (basic2 < 6) {
            randomIndex = rand.nextInt(PrawkoApplication.questionsBasic2.size());
            if (!usedIndexes.contains(randomIndex)) {
                usedIndexes.add(randomIndex);
                questions.add(PrawkoApplication.questionsBasic2.get(randomIndex));
                basic2++;
            }
        }
        usedIndexes = new HashSet<>();
        while (basic3 < 10) {
            randomIndex = rand.nextInt(PrawkoApplication.questionsBasic3.size());
            if (!usedIndexes.contains(randomIndex)) {
                usedIndexes.add(randomIndex);
                questions.add(PrawkoApplication.questionsBasic3.get(randomIndex));
                basic3++;
            }
        }
        usedIndexes = new HashSet<>();
        while (special1 < 2) {
            randomIndex = rand.nextInt(PrawkoApplication.questionsSpecial1.size());
            if (!usedIndexes.contains(randomIndex)) {
                usedIndexes.add(randomIndex);
                questions.add(PrawkoApplication.questionsSpecial1.get(randomIndex));
                special1++;
            }
        }
        usedIndexes = new HashSet<>();
        while (special2 < 4) {
            randomIndex = rand.nextInt(PrawkoApplication.questionsSpecial2.size());
            if (!usedIndexes.contains(randomIndex)) {
                usedIndexes.add(randomIndex);
                questions.add(PrawkoApplication.questionsSpecial2.get(randomIndex));
                special2++;
            }
        }
        usedIndexes = new HashSet<>();
        while (special3 < 6) {
            randomIndex = rand.nextInt(PrawkoApplication.questionsSpecial3.size());
            if (!usedIndexes.contains(randomIndex)) {
                usedIndexes.add(randomIndex);
                questions.add(PrawkoApplication.questionsSpecial3.get(randomIndex));
                special3++;
            }
        }

        Question randomQuestion = PrawkoApplication.questions.get(rand.nextInt(PrawkoApplication.questions.size()));

        if (randomQuestion != null) {
            return ResponseEntity.ok(questions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getOneQuestion")
    public ResponseEntity<Question> getOneQuestion(@RequestParam String type) {
        Random rand = new Random();
        Question randomQuestion = new Question();

        if (type.equals("b1")) {
            randomQuestion = PrawkoApplication.questionsBasic1.get(rand.nextInt(PrawkoApplication.questionsBasic1.size()));
        }
        if (type.equals("b2")) {
            randomQuestion = PrawkoApplication.questionsBasic2.get(rand.nextInt(PrawkoApplication.questionsBasic2.size()));
        }
        if (type.equals("b3")) {
            randomQuestion = PrawkoApplication.questionsBasic3.get(rand.nextInt(PrawkoApplication.questionsBasic3.size()));
        }
        if (type.equals("s3")) {
            randomQuestion = PrawkoApplication.questionsSpecial3.get(rand.nextInt(PrawkoApplication.questionsSpecial3.size()));
        }
        if (type.equals("all")) {
            randomQuestion = PrawkoApplication.questions.get(rand.nextInt(PrawkoApplication.questions.size()));
        }

        if (randomQuestion != null) {
            return ResponseEntity.ok(randomQuestion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getMedia")
    public ResponseEntity<String> getMedia(@RequestParam String mediaName) {
        try {


            byte[] fileContent = Files.readAllBytes(Path.of(String.format("src/main/resources/static/%s", mediaName)));
            String base64File = Base64.getEncoder().encodeToString(fileContent);
            return ResponseEntity.ok(base64File);


        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String getFileType(String fileName) {
        return fileName.contains(".mp4") ? "VID" : "IMG";
    }


}
