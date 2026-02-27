package com.example.lunar.helper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;

@Log4j2
public class MailHelper {

    private static final String API_USER = System.getenv("ENGAGELAB_API_USER");
    private static final String API_KEY = System.getenv("ENGAGELAB_API_KEY");
    private static final String MAIL_URL = "https://email.api.engagelab.cc/v1/mail/send";

    public static void sendMail(String to, String subject, String html) {
        String auth = getAuth();
        OkHttpClient client = new OkHttpClient();
        RequestBody body = buildRequestBody(to, subject, html);

        Request request = new Request.Builder()
                .url(MAIL_URL)
                .post(body)
                .addHeader("Authorization", auth)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Status: " + response.code());
            System.out.println("Body: " + response.body().string());
            log.info("Test send mail success", response);
        } catch (Exception e) {
            log.info("Test send mail error", e);
        }
    }

    private static String getAuth() {
        String credentials = API_USER + ":" + API_KEY;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    }

    private static RequestBody buildRequestBody(String to, String subject, String html) {
        String json =
                """
        {
          "from": "EngageLab@c4kzgreqaq61gcijflhg7iig93ceqq3j.send.engagelab.email",
          "to": ["%s"],
          "body": {
            "subject": "%s",
            "content": {
              "html": "%s"
            }
          }
        }
        """
                        .formatted(to, subject, html);

        return RequestBody.create(json, MediaType.get("application/json"));
    }
}
