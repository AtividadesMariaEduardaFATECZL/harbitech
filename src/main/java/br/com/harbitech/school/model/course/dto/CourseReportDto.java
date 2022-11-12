package br.com.harbitech.school.model.course.dto;

import br.com.harbitech.school.model.subcategory.dto.SubcategoryDto;

public class CourseReportDto {

    private Long id;
    private String name;
    private int completionTimeInHours;
    private SubcategoryDto subcategoryDto;

    public CourseReportDto(Long id, String name, int completionTimeInHours, SubcategoryDto subcategoryDto) {
        this.id = id;
        this.name = name;
        this.completionTimeInHours = completionTimeInHours;
        this.subcategoryDto = subcategoryDto;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCompletionTimeInHours() {
        return completionTimeInHours;
    }

    public SubcategoryDto getSubcategoryDto() {
        return subcategoryDto;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", completionTimeInHours=" + completionTimeInHours +
                ", subcategoryDto=" + subcategoryDto +
                '}';
    }
}
