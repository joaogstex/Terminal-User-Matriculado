package br.com.ecoground.user.user_ecoground.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DigestAuthUtil {

    public static HttpResponse<String> sendWithDigestAuth(String url, String method, String jsonBody, String username, String password) throws Exception {
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder reqBuilder = HttpRequest.newBuilder()
                .uri(uri)
                .method(method, HttpRequest.BodyPublishers.noBody());

        if ("POST".equalsIgnoreCase(method) && jsonBody != null) {
            reqBuilder = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(jsonBody));
        }

        HttpResponse<String> resp401 = client.send(reqBuilder.build(), HttpResponse.BodyHandlers.ofString());

        if (resp401.statusCode() != 401) {
            return resp401;
        }

        String authHeader = resp401.headers().firstValue("WWW-Authenticate")
                .orElseThrow(() -> new RuntimeException("WWW-Authenticate não encontrado"));

        Map<String, String> authParams = parseDigestHeader(authHeader);

        String realm = authParams.get("realm");
        String nonce = authParams.get("nonce");
        String qop = authParams.get("qop");
        String opaque = authParams.get("opaque");
        String nc = "00000001";
        String cnonce = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        String pathOnly = uri.getRawPath();

        String ha1 = md5(username + ":" + realm + ":" + password);
        String ha2 = md5(method + ":" + pathOnly);
        String response = md5(ha1 + ":" + nonce + ":" + nc + ":" + cnonce + ":" + qop + ":" + ha2);

        String authorization = String.format(
                "Digest username=\"%s\", realm=\"%s\", nonce=\"%s\", uri=\"%s\", qop=%s, nc=%s, cnonce=\"%s\", response=\"%s\", opaque=\"%s\"",
                username, realm, nonce, pathOnly, qop, nc, cnonce, response, opaque);

        HttpRequest.Builder authRequest = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .header("Authorization", authorization)
                .method(method,
                        jsonBody != null ? HttpRequest.BodyPublishers.ofString(jsonBody)
                                : HttpRequest.BodyPublishers.noBody());

                                System.out.println("Request Method: " + method);
                                System.out.println("Request URL: " + url);
                                System.out.println("Request Body: " + jsonBody);
                                System.out.println("Authorization Header: " + authorization);


        return client.send(authRequest.build(), HttpResponse.BodyHandlers.ofString());
    }

    private static Map<String, String> parseDigestHeader(String header) {
        Map<String, String> map = new HashMap<>();
        Matcher matcher = Pattern.compile("(\\w+)=\"?([^\"]+)\"?").matcher(header);
        while (matcher.find()) {
            map.put(matcher.group(1), matcher.group(2));
        }
        return map;
    }

    private static String md5(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

