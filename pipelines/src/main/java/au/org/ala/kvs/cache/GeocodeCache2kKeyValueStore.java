package au.org.ala.kvs.cache;

import au.org.ala.kvs.GeocodeShpIntersectConfig;
import au.org.ala.kvs.client.GeocodeShpIntersectService;
import org.gbif.kvs.KeyValueStore;
import org.gbif.kvs.geocode.LatLng;
import org.gbif.rest.client.geocode.GeocodeResponse;
import org.gbif.rest.client.geocode.GeocodeService;

import java.io.IOException;

public class GeocodeCache2kKeyValueStore implements KeyValueStore<LatLng, GeocodeResponse> {

  private final GeocodeService service;

  private GeocodeCache2kKeyValueStore(GeocodeShpIntersectConfig config) {
    this.service = GeocodeShpIntersectService.getInstance(config);
  }

  public static GeocodeCache2kKeyValueStore create(GeocodeShpIntersectConfig config) {
    return new GeocodeCache2kKeyValueStore(config);
  }

  @Override
  public void close() throws IOException {
  }

  @Override
  public GeocodeResponse get(LatLng latLng) {
    return new GeocodeResponse(service.reverse(latLng.getLatitude(), latLng.getLongitude()));
  }
}
