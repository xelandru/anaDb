package templates;


import domain.Candidate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public abstract class Template {

    Candidate candidate;
    final String TEMPLATE_FOLDER = "templates";

    Template(Candidate candidate) {

    }

    public abstract String processTemplate();

    String  getTemplateContent() throws IOException {

        Path path = Paths.get(TEMPLATE_FOLDER, candidate.getTemplate());
        return new String(Files.readAllBytes(path));
    }
}
