package org.itmo_olesia.filesystemhelper.Services.LogicServices.Verifiers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class EmailVerifierService {
    // using Hunter, token = 006404cd633a7817bb16a6ddbb172216300903ed
    private  String HUNTER_API_KEY = "006404cd633a7817bb16a6ddbb172216300903ed";
    private   String API_URL = "https://api.hunter.io/v2/";
    //private String email = "risposta@yandex.ru";
    //private String email = "info@urladoga.ru";
    //private String email = "mashauz@yandex.ru";
    //private String email = "kotputya@gmail.com";
    //@Async
    public boolean verifyEmail(String email){
        String requestUrl = API_URL + "email-verifier?email=" + email + "&api_key=" + HUNTER_API_KEY;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Обработка ответа от сервера
                return processResponse(response.toString());
            } else {
                System.out.println("Error: " + responseCode);
                return false;
            }
        } catch (Exception e){
            //
        }

        return true;
    }

    public  boolean processResponse(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(response);

        // Получение поля "result" из JSON-ответа
        JsonNode resultNode = jsonResponse.path("data").path("result");
        String result = resultNode.asText();

        // Вывод результата для отладки
        System.out.println("Email verification result: " + result);

        // Проверка значения результата
        return "deliverable".equalsIgnoreCase(result);

    }
}
