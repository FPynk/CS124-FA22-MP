package edu.illinois.cs.cs124.ay2022.mp.models;

import java.util.List;
import java.util.ArrayList;
//import java.util.regex.Pattern;
//import java.util.Locale;

/*
 * Model storing information about a place retrieved from the backend server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 */
@SuppressWarnings("unused")
public final class Place {
  /*
   * The Jackson JSON serialization library that we are using requires an empty constructor.
   * So don't remove this!
   */
  public Place() {}

  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
  }

  // ID of the place
  private String id;

  public static List<Place> search(final List<Place> places, final String searchInput) {
    if (places == null || searchInput == null) {
      throw new IllegalArgumentException();
    } else if (places.size() == 0 || searchInput.trim().length() == 0) {
      return places;
    }
    List<Place> output = new ArrayList<>();
    String search = " " + searchInput.trim().toLowerCase() + " ";
    for (Place p : places) {
      // process description
      String desc = p.getDescription().toLowerCase();
      desc = desc.replaceAll("[.!?,:;/]", " ");
      desc = " " + desc.replaceAll("[^a-zA-Z0-9 ]", "") + " ";
      if (desc.contains(search)) {
        //System.out.println(desc + "\n and the search is: " + search + "\n result is " + desc.contains(search));
        output.add(p);
      }
    }
    //assert false : "TO DO";
    return output;
  }

  public String getId() {
    return id;
  }

  // Name of the person who submitted this favorite place
  private String name;

  public String getName() {
    return name;
  }

  // Latitude and longitude of the place
  private double latitude;

  public double getLatitude() {
    return latitude;
  }

  private double longitude;

  public double getLongitude() {
    return longitude;
  }

  // Description of the place
  private String description;

  public String getDescription() { //fixed
    return description;
  }
}
