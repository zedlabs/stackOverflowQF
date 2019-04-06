package tk.zedlabs.stack;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("https://stackoverflow.com/oauth")
                .appendQueryParameter("type", "1")
                .appendQueryParameter("sort", "relevance");
        String myUrl = builder.build().toString();
    }
}
