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

        //TODO
        return null;
    }

    public abstract String getCustomMessage();

    void replace(StringBuilder builder, String from, String to) {
        int index = builder.indexOf(from);
        builder.replace(index, index + from.length(), to);
    }

}
