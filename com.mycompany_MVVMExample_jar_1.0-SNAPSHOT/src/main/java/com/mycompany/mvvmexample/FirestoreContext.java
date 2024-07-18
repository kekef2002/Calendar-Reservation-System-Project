package com.mycompany.mvvmexample;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;

/**
 * FXML Controller class
 *
 * @author group1
 */

public class FirestoreContext {

    private static Firestore firestore;

    static {
        try {
            initializeFirebase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeFirebase() throws IOException {
        try (InputStream serviceAccount = FirestoreContext.class.getResourceAsStream("/com/mycompany/mvvmexample/key.json")) {
            if (serviceAccount == null) {
                throw new IOException("Resource not found: /com/mycompany/mvvmexample/key.json");
            }

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(options);
            }

            firestore = FirestoreClient.getFirestore();
        }
    }

    public static Firestore getFirestore() {
        return firestore;
    }

    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, (JsonSerializer<ZonedDateTime>) (src, typeOfSrc, context) -> context.serialize(src.toString()))
                .registerTypeAdapter(ZonedDateTime.class, (JsonDeserializer<ZonedDateTime>) (json, typeOfT, context) -> ZonedDateTime.parse(json.getAsString()))
                .create();
    }
}
