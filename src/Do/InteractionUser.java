package Do;

import woodcutter.MainClass;

public class InteractionUser{
    private String thought = "";
    private String activity;
    private MainClass mainClass;
    private Factory _factory;
    public InteractionUser(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }

    public void SetThought(String _thought){thought = _thought;}
    public void SetActivity(String _activity){activity = _activity;}

    public String getThought() {
        return thought;
    }

    public String getActivity() {
        return activity;
    }
}
