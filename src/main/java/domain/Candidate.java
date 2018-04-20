package domain;


public class Candidate {

    String name;
    String email;
    String template;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Candidate(String name, String email, String template) {
        this.name = name;
        this.email = email;
        this.template = template;
    }
}
