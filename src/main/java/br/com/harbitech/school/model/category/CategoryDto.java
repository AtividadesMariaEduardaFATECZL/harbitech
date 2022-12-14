package br.com.harbitech.school.model.category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private CategoryStatus status;
    private int orderVisualization;
    private String iconPath;
    private String htmlHexColorCode;

        public CategoryDto(Category category) {
        this.name = category.getName();
        this.codeUrl = category.getCodeUrl();
        this.description = category.getDescription();
        this.studyGuide = category.getStudyGuide();
        this.status = category.getStatus();
        this.orderVisualization = category.getOrderVisualization();
        this.iconPath = category.getIconPath();
        this.htmlHexColorCode = category.getHtmlHexColorCode();
    }

    public static List<CategoryDto> convert(List<Category> categories) {
        return categories.stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}
