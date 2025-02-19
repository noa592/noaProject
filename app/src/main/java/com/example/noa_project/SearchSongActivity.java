package com.example.noa_project;

import static com.example.noa_project.MainActivity.fbModule;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchSongActivity extends AppCompatActivity implements View.OnClickListener {
    public Playlist Arrplaylist;
    private WebView webView;
    private Button playlist;
    private YouTubeApi youTubeApi;
private  EditText searchEditText;
    private int color ;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_song);


        searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);
        webView = findViewById(R.id.webView);
        Arrplaylist = new Playlist(); // אתחול משתנה הפלייליסט

        playlist=findViewById(R.id.PlayList);

        playlist.setOnClickListener(this);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        youTubeApi = retrofit.create(YouTubeApi.class);

        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString();
            if (!query.isEmpty()) {
                searchYouTube(query);
            } else {
                Toast.makeText(this, "Enter a song name", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateBackgroundColor() {

        View view = findViewById(R.id.loging);

        view.setBackgroundColor(getResources().getColor(android.R.color.black));
        color=sharedPreferences.getInt("color", Color.WHITE);

    }



    private void searchYouTube(String query) {
        String apiKey = "AIzaSyDgdNk-_oFP_Mlb354bTPWqmpL0GBcavHU";  // הכניסי כאן את מפתח ה-API שלך
        youTubeApi.searchVideos("snippet", query, "video", apiKey, 1)
                .enqueue(new Callback<YouTubeResponse>() {
                    @Override
                    public void onResponse(Call<YouTubeResponse> call, Response<YouTubeResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().items != null && !response.body().items.isEmpty()) {
                                String videoId = response.body().items.get(0).id.videoId;
                                String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
                                loadVideoInWebView(videoUrl);


                            } else {
                                Toast.makeText(SearchSongActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SearchSongActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<YouTubeResponse> call, Throwable t) {
                        Toast.makeText(SearchSongActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadVideoInWebView(String videoUrl) {

        webView.getSettings().setJavaScriptEnabled(true); // אפשר JavaScript עבור הסרטונים
        webView.getSettings().setDomStorageEnabled(true); // מאפשר אחסון DOM אם צריך
        webView.loadUrl(videoUrl); // טען את ה-URL
    }


    @Override
    public void onClick(View view) {
        if (view==playlist)

        {
            Arrplaylist.add(searchEditText.getText().toString());


        }
    }
}
