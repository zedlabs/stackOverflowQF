package tk.zedlabs.stack;

public class Question {

    private String mTitle;
    private String mLink;

    public Question(String title, String link){

        mTitle = title;
        mLink = link;
    }

    public String getQuestionTitle(){
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }
}
