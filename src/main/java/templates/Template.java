package templates;


import domain.Candidate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Template {

    Candidate candidate;
    private final String templateFolder = "/home/adascalu/Desktop/ana/src/main/resources/templates/";

    Template(Candidate candidate) {
        this.candidate = candidate;
    }

    String getTemplateText() {

        String text;
        try {
            text = new String(Files.readAllBytes(Paths.get(templateFolder + candidate.getTemplateName())));
        } catch (IOException e) {
            throw new RuntimeException("Could not find template for candidate " + candidate.getName());
        }
        return text;
    }

    public abstract String getCustomMessage();

    void replace(StringBuilder builder, String from, String to) {
        int index = builder.indexOf(from);
        builder.replace(index, index + from.length(), to);
    }

}
