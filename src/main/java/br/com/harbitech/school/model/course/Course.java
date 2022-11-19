package br.com.harbitech.school.model.course;

import br.com.harbitech.school.model.subcategory.SubCategory;

import static br.com.harbitech.school.model.validation.ValidationUtil.*;

public class Course {

    private Long id;
    private String name;
    private String codeUrl;
    private int completionTimeInHours;
    private CourseVisibility visibility;
    private String targetAudience;
    private String instructor;
    private String description;
    private String developedSkills;
    private SubCategory subCategory;

    @Deprecated
    public Course(){

    };

    public Course(String name, String codeUrl, int completionTimeInHours, String instructor, SubCategory subCategory){
        validateNonBlankText(name, "O nome do curso não pode estar em branco.");
        validateNonBlankText(codeUrl, "O código do curso não pode estar em branco.");
        validateNonBlankText(instructor, "O nome do instrutor não pode estar em branco");
        validateUrl(codeUrl, "O código da url do curso está incorreto (só aceita letras minúsculas e hífen): " + codeUrl);
        validateInterval(completionTimeInHours,1,20,"O tempo estimado deve estar " +
                "entre 1 hora até 20 horas.");
        validateNonNullClass(subCategory, "A curso deve ter uma sub-categoria associada.");
        this.name = name;
        this.codeUrl = codeUrl;
        this.completionTimeInHours = completionTimeInHours;
        this.instructor = instructor;
        this.visibility = CourseVisibility.PRIVATE;
        this.subCategory = subCategory;
    }

    public Course(String name, String codeUrl, int completionTimeInHours, CourseVisibility visibility,
                  String targetAudience, String instructor, String description, String developedSkills,
                  SubCategory subCategory) {
        this(name, codeUrl, completionTimeInHours, instructor,subCategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.instructor = instructor;
        this.description = description;
        this.developedSkills = developedSkills;
        this.subCategory.addCourse(this);
    }


    public Course(Long id, String name, String codeUrl, int completionTimeInHours, String targetAudience, String instructor,
                  String description, String developedSkills, CourseVisibility visibility) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.completionTimeInHours = completionTimeInHours;
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.instructor = instructor;
        this.description = description;
        this.developedSkills = developedSkills;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public CourseVisibility getVisibility() {
        return visibility;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDescription() {
        return description;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public String getName() {
        return name;
    }

    public int getCompletionTimeInHours() {
        return completionTimeInHours;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public void setCompletionTimeInHours(int completionTimeInHours) {
        this.completionTimeInHours = completionTimeInHours;
    }

    public void setVisibility(CourseVisibility visibility) {
        this.visibility = visibility;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", completionTimeInHours=" + completionTimeInHours +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", instructor='" + instructor + '\'' +
                ", description='" + description + '\'' +
                ", developedSkills='" + developedSkills + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }
}
