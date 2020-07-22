package Do;

import org.dreambot.api.methods.skills.Skill;
import woodcutter.MainClass;
import org.dreambot.api.wrappers.widgets.message.Message;
public class AnswareMessage {
    private MainClass mainClass;
    private Factory _factory;
    private String[] Answers;
    private String[] Questions;
    private String[] Greetings;
    public AnswareMessage(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
        Questions = new String[]{"Bot?", "Hi", "Hello", "hey", "Wc lvl?", "Fm lvl?"};
        Answers = new String[]{"No!", "Hi.", "hello!", "yes?", "" + mainClass.getSkills().getRealLevel(Skill.WOODCUTTING), "" + mainClass.getSkills().getRealLevel(Skill.FIREMAKING)};
        Greetings = new String[]{};
    }
    public void Answer(Message message){
        for(int i = 0; i < Questions.length; i++){
            if(message.getMessage().contains(Questions[i])){
                mainClass.getKeyboard().type(Answers[i]);
                break;
            }
        }
    }
}
