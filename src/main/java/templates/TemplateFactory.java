package templates;


import domain.Candidate;

public class TemplateFactory {

    public static Template getTemplate(Candidate candidate) {

        Template template = null;

        switch (candidate.getTemplateName()) {
            case "template_A.txt":
                template = new TemplateA(candidate);
                break;
            case "template_B.txt":
                template = new TemplateB(candidate);
                break;
        }
        return template;
    }
}
