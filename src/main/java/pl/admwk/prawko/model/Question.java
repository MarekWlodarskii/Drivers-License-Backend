package pl.admwk.prawko.model;

import java.util.Map;

public class Question {
    private String questionText;
    private Map<Character, String> answers;
    private Character correctAnswer;
    private String fileName;
    private boolean isYesNo;
    private String fileType;
    private String basicOrSpecial;
    private String points;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Map<Character, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Character, String> answers) {
        this.answers = answers;
    }

    public Character getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Character correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public boolean isYesNo() {
        return isYesNo;
    }

    public void setYesNo(boolean yesNo) {
        this.isYesNo = yesNo;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getBasicOrSpecial() {
        return basicOrSpecial;
    }

    public void setBasicOrSpecial(String basicOrSpecial) {
        this.basicOrSpecial = basicOrSpecial;
    }

    public static final class QuestionBuilder {
        private String questionText;
        private Map<Character, String> answers;
        private Character correctAnswer;
        private String fileName;
        private boolean isYesNo;
        private String fileType;
        private String basicOrSpecial;
        private String points;

        private QuestionBuilder() {
        }

        public static QuestionBuilder aQuestion() {
            return new QuestionBuilder();
        }

        public QuestionBuilder withQuestionText(String questionText) {
            this.questionText = questionText;
            return this;
        }

        public QuestionBuilder withAnswers(Map<Character, String> answers) {
            this.answers = answers;
            return this;
        }

        public QuestionBuilder withCorrectAnswer(Character correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        public QuestionBuilder withFileName(String fileName) {
            this.fileName = fileName;
            this.fileType = getFileType(fileName);
            return this;
        }

        public QuestionBuilder withIsYesNo(boolean isYesNo) {
            this.isYesNo = isYesNo;
            return this;
        }

        public QuestionBuilder withBasicOrSpecial(String basicOrSpecial) {
            this.basicOrSpecial= basicOrSpecial;
            return this;
        }

        public QuestionBuilder withPoints(String points) {
            this.points= points;
            return this;
        }

        public Question build() {
            Question question = new Question();
            question.setQuestionText(questionText);
            question.setAnswers(answers);
            question.setCorrectAnswer(correctAnswer);
            question.setFileName(fileName);
            question.isYesNo = this.isYesNo;
            question.setYesNo(isYesNo);
            question.setFileType(fileType);
            question.setPoints(points);
            question.setBasicOrSpecial(basicOrSpecial);
            return question;
        }

        private String getFileType(String fileName) {
            return fileName.contains(".mp4") ? "VID" : "IMG";
        }
    }
}
