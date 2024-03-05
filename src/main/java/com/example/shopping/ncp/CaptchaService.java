package com.example.shopping.ncp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Service
public class CaptchaService {
    private static final String CAPTCHA_API_URL = "https://openapi.naver.com/v1/captcha/nkey";

    @Value("${ncp.api.client-id}")
    private String CLIENT_ID;
    @Value("${ncp.api.client-secret}")
    private String CLIENT_SECRET;
    private static final String CAPTCHA_IMAGE_URL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=";


    public String requestCaptchaKey() {
        try {
            String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", CLIENT_SECRET);
            int responseCode = con.getResponseCode();
            if (responseCode == 200) { // 정상 호출
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                return response.toString();
            } else {  // 오류 발생
                throw new RuntimeException("Failed to request captcha key. Response code: " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to request captcha key", e);
        }
    }

    public void captchaImage(String key) {
        try {
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key + "&X-NCP-APIGW-API-KEY-ID=" + CLIENT_ID;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == 200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 파일 생성
                String tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File(tempname + ".jpg");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read = is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();
            } else {  // 오류 발생
                throw new RuntimeException("Failed to request captcha image. Response code: " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to request captcha image", e);
        }
    }

    public boolean verifyCaptcha(String key, String userValue) {
        try {
            String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code + "&key=" + key + "&value=" + userValue;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", CLIENT_SECRET);
            int responseCode = con.getResponseCode();
            if (responseCode == 200) { // 정상 호출
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                return response.toString().contains("true");
            } else {  // 오류 발생
                throw new RuntimeException("Failed to verify captcha. Response code: " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify captcha", e);
        }
    }
}
