import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WalletRu {



    @Test
    public void responseTimeLowThan500Milliseconds() {

        given()
                .get("https://api.coinmarketcap.com/v2/ticker/?limit=10&sort=volume_24h")
                .then()
                .assertThat().time(lessThan(500L));

    }


    @Test
    public void infoAboutAllJsonCurrencyUpdate() {

        int timeNowUnix = (int) (System.currentTimeMillis()/1000L);

        int s = given()
                .get("https://api.coinmarketcap.com/v2/ticker/?limit=10&sort=volume_24h")
                .then()
                .extract().path("metadata.timestamp");

        assert s<timeNowUnix;

    }


    @Test
    public void totalPackageSize() {


        Response response = get("https://api.coinmarketcap.com/v2/ticker/?limit=10&sort=volume_24h");
        assert response.asByteArray().length<=10000;


    }



}
