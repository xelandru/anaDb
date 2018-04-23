package app;


import domain.Candidate;
import templates.Template;
import templates.TemplateA;
import templates.TemplateFactory;

public class App {


    public static void main(String[] args) {
        Candidate  c1 = new Candidate("Gigel", "gigel@gmail.com", "template_A.txt",  "www.job.com");
        Candidate  c2 = new Candidate("Gigel", "gigel@gmail.com", "template_B.txt",  "www.job.com");


        String msg1 = TemplateFactory.getTemplate(c1).getCustomMessage();
        String msg2 = TemplateFactory.getTemplate(c2).getCustomMessage();

        System.out.println(msg1);
        System.out.println(msg2);

    }

}
