package au.org.ala.kvs;

import au.org.ala.kvs.cache.GeocodeServiceFactory;
import org.gbif.kvs.cache.KeyValueCache;
import org.gbif.kvs.geocode.LatLng;
import org.gbif.pipelines.parsers.config.factory.KvConfigFactory;
import org.gbif.pipelines.parsers.config.model.KvConfig;
import org.gbif.pipelines.parsers.parsers.location.GeocodeKvStore;
import org.gbif.rest.client.geocode.GeocodeResponse;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GeocodeServiceTest {

    /**
     * Tests the Get operation on {@link KeyValueCache} that wraps a simple KV store backed by a HashMap.
     */
    @Test
    public void testInsideCountry() throws Exception {

        Properties p = new Properties();
        p.load(new FileInputStream(new File("src/test/resources/pipelines.properties")));

        KvConfig kvConfig = KvConfigFactory.create(p, KvConfigFactory.GEOCODE_PREFIX);
        GeocodeKvStore geoService = GeocodeServiceFactory.create(kvConfig);
        GeocodeResponse resp = geoService.get(LatLng.builder().withLongitude(146.2).withLatitude(-27.9).build());
        assert !resp.getLocations().isEmpty();
        assert resp.getLocations().iterator().next().getCountryName().equals("AU");
    }

    /**
     * Tests the Get operation on {@link KeyValueCache} that wraps a simple KV store backed by a HashMap.
     */
    @Test
    public void testInsideEEZ() throws Exception {

        Properties p = new Properties();
        p.load(new FileInputStream(new File("src/test/resources/pipelines.properties")));
        KvConfig kvConfig = KvConfigFactory.create(p, KvConfigFactory.GEOCODE_PREFIX);
        GeocodeKvStore geoService = GeocodeServiceFactory.create(kvConfig);
        GeocodeResponse resp = geoService.get(LatLng.builder().withLongitude(151.329751).withLatitude(-36.407357).build());
        assert !resp.getLocations().isEmpty();
        assert resp.getLocations().iterator().next().getCountryName().equals("AU");
    }
}
