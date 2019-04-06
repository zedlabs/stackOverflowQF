package tk.zedlabs.stack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


public class QuestionAdapter extends ArrayAdapter<Question> {


    public QuestionAdapter(Context context, List<Question> earthquakes) {
        super(context, 0, earthquakes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Question currentQuestion = getItem(position);
        TextView tv = listItemView.findViewById(R.id.question_textView);
        tv.setText(currentQuestion.getQuestionTitle());

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return listItemView;
    }
}