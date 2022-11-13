package edu.illinois.cs.cs124.ay2022.mp.activities;

import static edu.illinois.cs.cs124.ay2022.mp.application.FavoritePlacesApplication.CLIENT_ID;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.CompletableFuture;
import edu.illinois.cs.cs124.ay2022.mp.R;
import edu.illinois.cs.cs124.ay2022.mp.models.Place;
import edu.illinois.cs.cs124.ay2022.mp.models.ResultMightThrow;
import edu.illinois.cs.cs124.ay2022.mp.network.Client;

public class AddPlaceActivity extends AppCompatActivity {
  //Add in post
  //private static final String TAG = AddPlaceActivity.class.getSimpleName();
  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_addplace);

    Intent returnToMain = new Intent(this, MainActivity.class);
    returnToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

    Button cancelButton = findViewById(R.id.cancel_button);
    cancelButton.setOnClickListener(v -> startActivity(returnToMain));

    //Accesses description
    EditText descText = findViewById(R.id.description);

    Button saveButton = findViewById(R.id.save_button);
    saveButton.setOnClickListener(v -> {
      //Extract location data passed to this activity
      double lat = Double.parseDouble(this.getIntent().getStringExtra("latitude"));
      double lon = Double.parseDouble(this.getIntent().getStringExtra("longitude"));
      System.out.println(lat + " " + lon);

      //Name can be anything
      String name = "Suck ma DICK";
      System.out.println(name);

      // Get description from Text box
      String desc = descText.getText().toString();
      System.out.println(desc);

      //get ID which is hardcoded
      System.out.println(CLIENT_ID);

      //Create new favourite place
      Place place = new Place(CLIENT_ID, name, lat, lon, desc);

      //post that place
      final Client client = Client.start();
      CompletableFuture<ResultMightThrow<Boolean>> completableFuture = new CompletableFuture<>();
      client.postFavoritePlace(place, completableFuture::complete);
      startActivity(returnToMain);
    });
  }
}
