package br.com.harbitech.school.model.subcategory;

import br.com.harbitech.school.model.category.Category;
import br.com.harbitech.school.model.course.Course;

import java.util.ArrayList;
import java.util.List;

import static br.com.harbitech.school.model.validation.ValidationUtil.*;

public class SubCategory implements Comparable<SubCategory>{

    private Long id;
    private String name;
    private String codeUrl;
    private String description;
    private String studyGuide;
    private SubCategoryStatus status;
    private int orderVisualization;
    private Category category;
    private List<Course> courses = new ArrayList<>();

    public SubCategory(String name, String codeUrl, Category category){
        validateNonBlankText(name, "O nome da sub-categoria não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código da URL da sub-categoria não pode estar em branco.");
        validateNonNullClass(category, "A sub-categoria deve ter uma categoria associada.");
        validateUrl(codeUrl, "O código da url da sub-categoria está incorreto (só aceita letras minúsculas e hífen): " + codeUrl) ;

        this.name = name;
        this.codeUrl = codeUrl;
        this.category = category;
        this.status = SubCategoryStatus.INACTIVE;
        this.orderVisualization = -1;
    }

    public SubCategory(String name, String codeUrl, int orderVisualization, String description, String studyGuide,
                       SubCategoryStatus status, Category category) {

        this(name,codeUrl,category);
        this.name = name;
        this.codeUrl = codeUrl;
        this.description = description;
        this.studyGuide = studyGuide;
        this.status = status;
        this.orderVisualization = orderVisualization;
        this.category = category;
        this.category.addSubcategory(this);
    }

    public SubCategory(Long id,String name, String codeUrl, int orderVisualization, String description, String studyGuide,
                       SubCategoryStatus status, Category category){
        this(name, codeUrl, orderVisualization, description, studyGuide, status, category);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getOrderVisualization() {
        return orderVisualization;
    }


    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public SubCategoryStatus getStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", status=" + status +
                ", orderVisualization=" + orderVisualization +
                ", category=" + category +
                '}';
    }

    @Override
    public int compareTo(SubCategory otherSubCategory) {
        if (otherSubCategory.getOrderVisualization() < this.orderVisualization) {
            return otherSubCategory.getOrderVisualization();
        }
        return this.orderVisualization;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
