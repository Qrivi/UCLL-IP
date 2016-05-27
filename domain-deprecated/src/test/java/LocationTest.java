import model.exception.DomainException;
import model.wrapper.Location;
import org.junit.Test;

/**
 * Created by Krivi on 21/02/16.
 */
public class LocationTest{

    Location location;

    @Test
    public void test_create_location_with_standard_address() throws Exception{
        location = new Location(
                "Bij Koning Filip", // name
                50.885933, // latitude
                4.354272, // longitude
                "Avenue du Parc Royal 1", // address
                "", // cross street
                "Laeken", // city
                1020, // zip code
                "Brussels", // region
                "Belgium" // coountry
        );
    }

    @Test( expected = DomainException.class )
    public void test_fail_create_location_with_non_existent_coordinates() throws Exception{
        location = new Location(
                "Bij Koning Filip", // name
                150.885933, // latitude
                404.354272, // longitude
                "Avenue du Parc Royal 1", // address
                "", // cross street
                "Laeken", // city
                1020, // zip code
                "Brussels", // region
                "Belgium" // coountry
        );
    }
}
