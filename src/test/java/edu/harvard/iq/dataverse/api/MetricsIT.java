package edu.harvard.iq.dataverse.api;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.harvard.iq.dataverse.metrics.MetricsUtil;
import edu.harvard.iq.dataverse.util.FileUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.ws.rs.core.MediaType;

//TODO: These tests are fairly flawed as they don't actually add data to compare on.
//To improve these tests we should try adding data and see if the number DOESN'T
//go up to show that the caching worked
public class MetricsIT {

    @BeforeAll
    public static void setUpClass() {
        RestAssured.baseURI = UtilIT.getRestAssuredBaseUri();
        UtilIT.clearMetricCache();
    }

    @AfterAll
    public static void cleanUpClass() {
        UtilIT.clearMetricCache();
    }

    @Test
    public void testGetDataversesToMonth() {
        String yyyymm = "2018-04";
//        yyyymm = null;
        Response response = UtilIT.metricsDataversesToMonth(yyyymm, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsDataversesToMonth(yyyymm, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());
        
        assertEquals(precache, postcache);
        
        //Test error when passing extra query params
        response = UtilIT.metricsDataversesToMonth(yyyymm, "dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());

    }

    @Test
    public void testGetDatasetsToMonth() {
        String yyyymm = "2018-04";
//        yyyymm = null;
        Response response = UtilIT.metricsDatasetsToMonth(yyyymm, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsDatasetsToMonth(yyyymm, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test ok when passing extra query params
        response = UtilIT.metricsDatasetsToMonth(yyyymm, "dataLocation=local");
        response.then().assertThat()
                .statusCode(OK.getStatusCode());
    }

    @Test
    public void testGetFilesToMonth() {
        String yyyymm = "2018-04";
//        yyyymm = null;
        Response response = UtilIT.metricsFilesToMonth(yyyymm, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsFilesToMonth(yyyymm, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test error when passing extra query params
        response = UtilIT.metricsFilesToMonth(yyyymm, "dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());
    }

    @Test
    public void testGetDownloadsToMonth() {
        String yyyymm = "2018-04";
//        yyyymm = null;
        Response response = UtilIT.metricsDownloadsToMonth(yyyymm, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsDownloadsToMonth(yyyymm, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test error when passing extra query params
        response = UtilIT.metricsDownloadsToMonth(yyyymm, "dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());
    }

    @Test
    public void testGetAccountsToMonth() {
        String thismonth = MetricsUtil.getCurrentMonth();

        Response response = UtilIT.metricsAccountsToMonth(thismonth, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsAccountsToMonth(thismonth, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test error when passing extra query params
        response = UtilIT.metricsAccountsToMonth(thismonth, "dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());
    }

    @Test
    public void testGetAccountsTimeSeries() {
        Response response = UtilIT.metricsAccountsTimeSeries(MediaType.APPLICATION_JSON, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsAccountsTimeSeries(MediaType.APPLICATION_JSON, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);

        response = UtilIT.metricsAccountsTimeSeries(FileUtil.MIME_TYPE_CSV, null);
        response.then().assertThat()
                .statusCode(OK.getStatusCode());
        
        //Test error when passing extra query params
        response = UtilIT.metricsAccountsTimeSeries(MediaType.APPLICATION_JSON, "dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());
    }
    
        
    @Test
    public void testGetDataversesPastDays() {
        String days = "30";

        Response response = UtilIT.metricsDataversesPastDays(days, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsDataversesPastDays(days, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test error when passing extra query params
        response = UtilIT.metricsDataversesPastDays(days, "dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testGetDatasetsPastDays() {
        String days = "30";

        Response response = UtilIT.metricsDatasetsPastDays(days, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsDatasetsPastDays(days, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test ok when passing extra query params
        response = UtilIT.metricsDatasetsPastDays(days, "dataLocation=local");
        response.then().assertThat()
                .statusCode(OK.getStatusCode());
    }
    
    
    @Test
    public void testGetFilesPastDays() {
        String days = "30";

        Response response = UtilIT.metricsFilesPastDays(days, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsFilesPastDays(days, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test error when passing extra query params
        response = UtilIT.metricsFilesPastDays(days, "dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testGetDownloadsPastDays() {
        String days = "30";

        Response response = UtilIT.metricsDownloadsPastDays(days, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsDownloadsPastDays(days, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test error when passing extra query params
        response = UtilIT.metricsDownloadsPastDays(days, "dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());
    }    

    @Test
    public void testGetAccountsPastDays() {
        String days = "30";

        Response response = UtilIT.metricsAccountsPastDays(days, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsAccountsPastDays(days, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);

        //Test error when passing extra query params
        response = UtilIT.metricsAccountsPastDays(days, "dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());
    }
    

    @Test
    public void testGetDataverseByCategory() {
        Response response = UtilIT.metricsDataversesByCategory(null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsDataversesByCategory(null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test error when passing extra query params
        response = UtilIT.metricsDataversesByCategory("dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());
    }
    
    @Test
    public void testGetDataverseBySubject() {
        Response response = UtilIT.metricsDataversesBySubject(null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsDataversesBySubject(null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test error when passing extra query params
        response = UtilIT.metricsDataversesBySubject("dataLocation=local");
        response.then().assertThat()
                .statusCode(BAD_REQUEST.getStatusCode());
        
        // Test cache delete for single metric - just tests that the call succeeds since
        // the client can't really tell whether it gets a new/cached value

        response = UtilIT.clearMetricCache("dataversesBySubject");
        response.then().assertThat().statusCode(OK.getStatusCode());
    }

    @Test
    public void testGetDatasetsBySubject() {
        Response response = UtilIT.metricsDatasetsBySubject(null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        response = UtilIT.metricsDatasetsBySubject(null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test ok when passing extra query params
        response = UtilIT.metricsDatasetsBySubject("dataLocation=local");
        response.then().assertThat()
                .statusCode(OK.getStatusCode());
    }

    @Test
    public void testGetDatasetsBySubjectToMonth() {
        String thismonth = MetricsUtil.getCurrentMonth();
        Response response = UtilIT.metricsDatasetsBySubjectToMonth(thismonth, null);
        String precache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        //Run each query twice and compare results to tests caching
        // See the "TODO" at the beginning of the class; 
        // ideally, we'll want to have more comprehensive tests. 
        response = UtilIT.metricsDatasetsBySubjectToMonth(thismonth, null);
        String postcache = response.prettyPrint();
        response.then().assertThat()
                .statusCode(OK.getStatusCode());

        assertEquals(precache, postcache);
        
        //Test ok passing extra query params
        response = UtilIT.metricsDatasetsBySubjectToMonth(thismonth, "dataLocation=local");
        response.then().assertThat()
                .statusCode(OK.getStatusCode());
    }

        @Test
        public void testUnsupportedQueryParam() {
            Response response = UtilIT.makeDataCountMetricTimeSeries("viewCount", "unsupportedParam=value");

            response.then().assertThat()
                    .statusCode(BAD_REQUEST.getStatusCode())
                    .body("message", Matchers.containsString("queryParameter unsupportedParam not supported for this endpoint"));
        }

        @Test
        public void testInvalidMetric() {
            Response response = UtilIT.makeDataCountMetricTimeSeries("invalidMetric", null);

            response.then().assertThat()
                    .statusCode(BAD_REQUEST.getStatusCode())
                    .body("message", Matchers.containsString("MetricType must be one of these values"));
        }

        @Test
        public void testInvalidCountryCode() {
            Response response = UtilIT.makeDataCountMetricTimeSeries("viewCount", "country=INVALID");

            response.then().assertThat()
                    .statusCode(BAD_REQUEST.getStatusCode())
                    .body("message", Matchers.containsString("Country must be one of the ISO 1366 Country Codes"));
        }

        @Test
        public void testValidRequest() {
            Response response = UtilIT.makeDataCountMetricTimeSeries("viewCount", "country=us");

            response.then().assertThat()
                    .statusCode(OK.getStatusCode())
                    .contentType(ContentType.JSON);
        }

    }
