package templates;


import domain.Candidate;

import java.util.HashMap;
import java.util.Map;

public class TemplateA extends Template {


    TemplateA(Candidate candidate) {
        super(candidate);
    }

    @Override
    public String getCustomMessage() {
        //TODO

        return null;
    }

}

