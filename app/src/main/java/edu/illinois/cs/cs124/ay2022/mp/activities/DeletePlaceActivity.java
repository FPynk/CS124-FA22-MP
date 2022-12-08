package edu.illinois.cs.cs124.ay2022.mp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.CompletableFuture;
import edu.illinois.cs.cs124.ay2022.mp.R;
import edu.illinois.cs.cs124.ay2022.mp.network.models.Place;
import edu.illinois.cs.cs124.ay2022.mp.network.models.ResultMightThrow;
import edu.illinois.cs.cs124.ay2022.mp.network.Client;

public class DeletePlaceActivity extends AppCompatActivity {
  // Edit to delete place activity
  // Edited activity_deleteplace.xml
  // edited Strings.xml
  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_deleteplace);

    Intent returnToMain = new Intent(this, MainActivity.class);
    returnToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

    Button cancelButton = findViewById(R.id.cancel_button);
    cancelButton.setOnClickListener(v -> startActivity(returnToMain));

    Button deleteButton = findViewById(R.id.delete_button);
    deleteButton.setOnClickListener(v -> {
      //Extract location data passed to this activity
      Log.d("DeleteTest", "DeletePlaceActivity.java launched");
      String name = this.getIntent().getStringExtra("name");
      String id = this.getIntent().getStringExtra("id");
      String desc = this.getIntent().getStringExtra("desc");
      double lat = Double.parseDouble(this.getIntent().getStringExtra("lat"));
      double lon = Double.parseDouble(this.getIntent().getStringExtra("lon"));

      Place place = new Place(id, name, lat, lon, desc);

      //delete that place
      final Client client = Client.start();
      CompletableFuture<ResultMightThrow<Boolean>> completableFuture = new CompletableFuture<>();
      client.deleteFavoritePlace(place, completableFuture::complete);
      startActivity(returnToMain);
    });
  }
}
