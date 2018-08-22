package pl.franek.atm;

import pl.franek.atm.model.RouteHeader;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class AtmsRoute extends SpringRouteBuilder {

    private final Logger logger = Logger.getLogger(AtmsRoute.class);

    private static final String PROTOCOL = "https";
    private static final String HOST = "www.ing.nl";
    private static final int PORT = 443;
    private static final String BASIC_PATH = "api/locator/atms/";
    private static final String LOCATION_CRITERIA_NAME = "locatedin/";

    @Override
    public void configure() throws Exception {
        getFormattedJsonFromExternalService();
    }

    private void getFormattedJsonFromExternalService() {
        from("direct:atms")
                .to("restlet:" + buildUrl(RouteHeader.CITY_NAME.getRouteRepresentation()))
                .process(new AtmsJsonProcessor());
    }

    private String buildUrl(String cityName) {
        String path = buildPathWithLocation(cityName);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(PROTOCOL);
        uriBuilder.setHost(HOST);
        uriBuilder.setPort(PORT);
        uriBuilder.setPath(path);
        String url;
        try {
            url = uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            logger.error("Error while building request URL", e);
            throw new IllegalArgumentException(e);
        }
        return url;
    }

    private String buildPathWithLocation(String cityName) {
        String path = BASIC_PATH;
        if (StringUtils.isNotBlank(cityName)) {
            path = path + LOCATION_CRITERIA_NAME + cityName;
        }
        return path;
    }
}
