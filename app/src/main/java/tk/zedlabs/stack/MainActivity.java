package tk.zedlabs.stack;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderCallbacks<List<Question>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String BASE_URL =
            "https://api.stackexchange.com/2.2/questions?pagesize=10&order=desc&sort=activity&tagged=android&site=stackoverflow";


    private static final int LOADER_ID = 1;

    private QuestionAdapter mAdapter;

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView questionListView =findViewById(R.id.list);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        questionListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new QuestionAdapter(this, new ArrayList<Question>());
        questionListView.setAdapter(mAdapter);

        questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Question currentQuestion = mAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentQuestion.getLink());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();


            loaderManager.initLoader(LOADER_ID, null, this);
        } else {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<Question>> onCreateLoader(int i, Bundle bundle) {

        return new QuestionLoader(this, BASE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Question>> loader, List<Question> earthquakes) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.no_questions);


        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);

        }
    }

    @Override
    public void onLoaderReset(Loader<List<Question>> loader) {
        mAdapter.clear();
    }
}