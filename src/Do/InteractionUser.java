package Do;
public class InteractionUser{
    private String thought = "";
    private String activity;
    public InteractionUser() {

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
