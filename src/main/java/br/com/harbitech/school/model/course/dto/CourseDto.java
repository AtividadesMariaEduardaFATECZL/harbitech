package br.com.harbitech.school.model.course.dto;

public class CourseDto {

    private Long id;
    private String name;
    private String codeUrl;

    public CourseDto(Long id, String name, String codeUrl) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }
}
