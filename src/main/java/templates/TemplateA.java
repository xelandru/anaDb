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

        StringBuilder text = new StringBuilder(getTemplateText());

        Map<String, String> toReplace = new HashMap<>();

        toReplace.put("{name}", candidate.getName());
        toReplace.put("{jobLink}", candidate.getJobLink());

        for (String key : toReplace.keySet())
            replace(text, key, toReplace.get(key));

        return text.toString();
    }

}

