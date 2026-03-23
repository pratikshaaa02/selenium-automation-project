package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserApiTest {

	
	@Test(enabled = false)
 //   @Test
    public void userApiTest() {

        // 🔑 Step 1: Paste token from DevTools (/connect/token → Response)
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjI3N0FFNEJCQkU1RTlFQkVDMjczRjAxQTc3NzkzOTM3QzBENDMxRkQiLCJ4NXQiOiJKM3JrdTc1ZW5yN0NjX0FhZDNrNU44RFVNZjAiLCJ0eXAiOiJhdCtqd3QifQ.eyJpc3MiOiJodHRwczovL2F1dGgtcWEubmV4dGVyZS5jb20vIiwiZXhwIjoxNzc0MjYyMDg5LCJpYXQiOjE3NzQyNTg0ODksImF1ZCI6WyJBY2NvdW50U2VydmljZSIsIklkZW50aXR5U2VydmljZSIsIkFkbWluaXN0cmF0aW9uU2VydmljZSIsIlNhYXNTZXJ2aWNlIiwiQ29yZWRhdGFTZXJ2aWNlIiwiTm90aWZpY2F0aW9uU2VydmljZSIsIkNvbW1pc3Npb25TZXJ2aWNlIiwiQ2FsZW5kYXJTZXJ2aWNlIiwiVGFza1NlcnZpY2UiLCJQYXltZW50U2VydmljZSIsIlJlcG9ydFNlcnZpY2UiXSwic2NvcGUiOiJvZmZsaW5lX2FjY2VzcyBvcGVuaWQgcHJvZmlsZSBlbWFpbCBwaG9uZSBBY2NvdW50U2VydmljZSBJZGVudGl0eVNlcnZpY2UgQWRtaW5pc3RyYXRpb25TZXJ2aWNlIFNhYXNTZXJ2aWNlIENvcmVkYXRhU2VydmljZSBOb3RpZmljYXRpb25TZXJ2aWNlIENvbW1pc3Npb25TZXJ2aWNlIENhbGVuZGFyU2VydmljZSBUYXNrU2VydmljZSBQYXltZW50U2VydmljZSBSZXBvcnRTZXJ2aWNlIiwianRpIjoiYmQyNzE5YWUtYTZhNy00MDgyLTljZGEtODI1MWQ0MDI5YjkxIiwic3ViIjoiOTBlNzM5MjgtZWVhYi1hZTVhLWQ0OGUtM2ExZDk4MTMwNzNmIiwidGVuYW50aWQiOiI0NjJjMmQ1Ny00M2QxLTI2ODYtNWFiZi0zYTFkOTgxMzA3MzQiLCJ1bmlxdWVfbmFtZSI6IlJ1dHZpIiwidXNlclR5cGUiOiI0IiwiRmlyc3ROYW1lQ2xhaW0iOiJSdXR2aSIsIkxhc3ROYW1lQ2xhaW0iOiJGTU8iLCJ0ZW5hbnRfbmFtZSI6IlJ1dHZpIiwiTW9kZVR5cGUiOiJDb25maWd1cmF0aW9uIiwiQWdlbmN5SWRDbGFpbSI6IjI5NTAwOSIsIkFnZW5jeU5hbWVDbGFpbSI6IlJ1dHZpIEFnZW5jeSBGTU8iLCJBZ2VudElkQ2xhaW0iOiIxMjQyMzYzIiwiQWdlbnROYW1lQ2xhaW0iOiJSdWRkeXkgQW5nZWwgU2FudG9zIiwiTlBOQ2xhaW0iOiIyMTExNTE0MyIsIm9pX3Byc3QiOiJPbmJvYXJkaW5nX0FuZ3VsYXIiLCJvaV9hdV9pZCI6IjA5OWZhNTcwLWY5MGItMjQ0OC1jNTgwLTNhMWZkNDg4Njk3OSIsInByZWZlcnJlZF91c2VybmFtZSI6IlJ1dHZpIiwiZ2l2ZW5fbmFtZSI6IlJ1dHZpIiwiZmFtaWx5X25hbWUiOiJGTU8iLCJyb2xlIjoiRk1PIiwiZW1haWwiOiJydXR2aV9mbW9AeW9wbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6IlRydWUiLCJwaG9uZV9udW1iZXIiOiI3ODc3ODc4Nzg4IiwicGhvbmVfbnVtYmVyX3ZlcmlmaWVkIjoiRmFsc2UiLCJjbGllbnRfaWQiOiJPbmJvYXJkaW5nX0FuZ3VsYXIiLCJvaV90a25faWQiOiJjNzM2ZDU3ZC1hMTA0LWVhYmYtMjY2OS0zYTIwMmMzOGUxNjAifQ.O42sTSjaeVLPM91VP5OTcfD5oY0FuHVdAbXsdwhQ-LJd1Zg28UikwhZz9HzmSa2jYo6I8fE7y3k95cWJdDlijaeqM6N_TY4w9sn-Fk9T944YZY1yo-1N1D9O--M7L0QpgD3PD7bG9u4F6L5E7L_f9mbzV5EULPj41fKNhl36Uyz9dLI0pS6ctg6iIvCN0cGfvCvQuOHC4nAf83ui-5Vx9uFMOis3Dgxe54eBaAmOay8S5NWIUEXDGQWWzaPGhJfm6-MTcGS_vGjJd3o2FVzBf-dcuGnREHQuegm0JExqaJvhISnGneV6OVH0Wg7autOsaTji5J2BC9qUYXZqNOyMZWvSUkqKgBZ_UvMzp_N7MD4H6aL7xG7XLcj9KIhdxMMAKXaa_i8KNm99e6xUb5C3MJ81qpUibLDtnT1kAg__DKN3l0z1wivMZE-6TIDMTrFDYZqmqJ5F0Bepw0djCYNbT9zJC6_OBaz_a5HtBkAr1VEeedS4TDsQv_gs1gGlkdF8AbTH92FlL_OX8t1fEJJnEM1wJwxbWOUQEaEzp6Ykxn8Wh8UEcYFreJVsY61aXz0NO58-bB4_zmMojSRSVmjwVmqdWwFdGomldVlccM_7dgIczKXB_JkmGf_YYbfr2kA1zvirugaBt0fiK-CsbCdiyDbcw8NN_wr2KEXrvelOyTo";

        // ❗ Validate token
        Assert.assertNotNull(token, "❌ Token is NULL");
        Assert.assertFalse(token.isEmpty(), "❌ Token is EMPTY");

        // 🔹 Step 2: Base URL
        RestAssured.baseURI = "https://gateway-qa.nextere.com";

        // 🔹 Step 3: API Call (POST + headers)
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                
                // 🔥 IMPORTANT: Tenant header from Network tab
                .header("__tenant", "462c2d57-43d1-2686-5abf-3a1d98130734")

                // 🔥 IMPORTANT: POST (not GET)
                .post("/api/user-detail/check-user-info");

        // 🔍 Step 4: Debug logs
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:");
        System.out.println(response.asString());

        // 🔹 Step 5: Validation
        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println("✅ API Test Passed");
    }
}