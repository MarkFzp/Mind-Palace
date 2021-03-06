package com.example.jingyue.mindpalace;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.language.v1.CloudNaturalLanguage;
import com.google.api.services.language.v1.CloudNaturalLanguageRequestInitializer;
import com.google.api.services.language.v1.model.AnalyzeEntitiesRequest;
import com.google.api.services.language.v1.model.AnalyzeEntitiesResponse;
import com.google.api.services.language.v1.model.Document;

import java.io.IOException;

public class GGLanguage extends AsyncTask<Object, Object, Object> {
    private static final String API_KEY = "AIzaSyBD-58JXajoizqSzCVVyYkfsmEvxvfbChQ";
    private String textToBeAnalyzed = "";
    private AnalyzeEntitiesResponse response = null;
    public GGLanguage(String s) {
        textToBeAnalyzed = s;
    }

    @Override
    protected AnalyzeEntitiesResponse doInBackground(Object... params) {

        // following DDC's code
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        CloudNaturalLanguage.Builder builder = new CloudNaturalLanguage.Builder(httpTransport, jsonFactory, null);
        builder.setCloudNaturalLanguageRequestInitializer(new CloudNaturalLanguageRequestInitializer(API_KEY));
        CloudNaturalLanguage naturalLanguageAPI = builder.build();
        AnalyzeEntitiesRequest analyzeentityRequest = new AnalyzeEntitiesRequest();

        // the parameters that are passed in
                //SentimentScoreDatabaseHandler ssDB = (SentimentScoreDatabaseHandler) params[1];

        Document document = new Document();
        document.setType("PLAIN_TEXT");
        document.setContent(textToBeAnalyzed);
        analyzeentityRequest.setDocument(document);
        Log.d("gog", "fuck");
        Log.d("gog", analyzeentityRequest.toString());

        try {
            CloudNaturalLanguage.Documents.AnalyzeEntities sentimentRequest = naturalLanguageAPI.documents().analyzeEntities(analyzeentityRequest);

            //Log.d("gog", sentimentRequest.toString());
            //Log.d("gog", "fuck");
            response = sentimentRequest.execute();
            //Log.d("fuck", response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
