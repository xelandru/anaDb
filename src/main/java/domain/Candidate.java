package domain;


public class Candidate {

    private String name;
    private String email;
    private String templateName;

    public String getJobLink() {
        return jobLink;
    }

    private String jobLink;


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getTemplateName() {
        return templateName;
    }

    public Candidate(String name, String email, String templateName, String jobLink) {
        this.name = name;
        this.email = email;
        this.templateName = templateName;
        this.jobLink = jobLink;
    }
}
