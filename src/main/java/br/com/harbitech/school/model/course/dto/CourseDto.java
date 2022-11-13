package br.com.harbitech.school.model.course.dto;

public class CourseDto {
    private String name;
    private String codeUrl;

    public CourseDto(String name, String codeUrl) {
        this.name = name;
        this.codeUrl = codeUrl;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "name='" + name + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }
}
