package templates;

import com.sun.xml.internal.bind.v2.TODO;
import domain.Candidate;

import java.util.HashMap;
import java.util.Map;


public class TemplateB extends Template {


    TemplateB(Candidate candidate) {
        super(candidate);
    }

    @Override
    public String getCustomMessage() {
        // TODO
        return null;
    }

}
