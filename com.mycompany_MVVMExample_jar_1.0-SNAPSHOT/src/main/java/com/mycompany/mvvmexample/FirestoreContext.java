package com.mycompany.mvvmexample;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * FXML Controller class
 *
 * @author kekef
 */

public class FirestoreContext {

    private Firestore firestore;

    public FirestoreContext() {
        initializeFirebase();
    }

    private void initializeFirebase() {
        try (InputStream serviceAccount = getClass().getResourceAsStream("/com/mycompany/mvvmexample/key.json")) {
            if (serviceAccount == null) {
                throw new IOException("Resource not found: /com/mycompany/mvvmexample/key.json");
            }

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(options);
            }

            this.firestore = FirestoreClient.getFirestore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Firestore getFirestore() {
        return firestore;
    }
}
