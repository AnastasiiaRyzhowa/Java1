package org.ekg.front.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.ekg.front.MainApplication;
import org.ekg.front.dto.requests.ChangePasswordDTO;
import org.ekg.front.dto.requests.LoginDTO;
import org.ekg.front.dto.responses.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Requests {
    private static HttpResponse<String> baseRequest(String url, Object entity) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(ow.writeValueAsString(entity)))
                .header("Content-Type", "application/json")
                .build();

        return client
                .send(request, HttpResponse.BodyHandlers.ofString());
    }
    private static HttpResponse<String> baseRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        return client
                .send(request, HttpResponse.BodyHandlers.ofString());
    }
    public static boolean authRequest(String url, LoginDTO loginDTO) throws IOException, InterruptedException {
        HttpResponse<String> response = Requests.baseRequest(url, loginDTO);

        if(response.statusCode()==200){
            ContextHolder config = MainApplication.getContextHolder();
            ObjectMapper objectMapper = new ObjectMapper();
            UserDTO me = objectMapper.readValue(response.body(), new TypeReference<>() {});
            config.setUser(me);
            MainApplication.setContextHolder(config);
            return true;
        }
        return false;

    }
    public static boolean changePasswordRequest(String url, ChangePasswordDTO changePasswordEntity) throws IOException, InterruptedException {
        return Requests.baseRequest(url, changePasswordEntity).statusCode() == 200;
    }
    public static List<PatientDTO> getPatientsRequest(String url) throws IOException, InterruptedException {
        HttpResponse<String> response = Requests.baseRequest(url);
        if(response.statusCode()==200){
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(response.body(), new TypeReference<>() {});
        }
        return null;
    }
    public static List<SurveyDTO> getSurveysRequest(String url) throws IOException, InterruptedException {

        HttpResponse<String> response = Requests.baseRequest(url);
        if(response.statusCode()==200){
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(response.body(), new TypeReference<>() {});
        }
        return null;

    }
    public static List<EkgDTO> getEKGRequest(String url) throws IOException, InterruptedException {
        HttpResponse<String> response = Requests.baseRequest(url);
        if(response.statusCode()==200){
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(response.body(), new TypeReference<>() {});
        }
        return null;

    }

}
