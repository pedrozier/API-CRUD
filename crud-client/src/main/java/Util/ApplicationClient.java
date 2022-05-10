/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import Model.MyUser;
import Model.Token;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author zier
 */
public class ApplicationClient {

    private Token token = new Token();

    private String result;
    private int status;

    private static final String LOGIN_URL = "http://localhost:8080/login";
    private static final String HOME_URL = "http://localhost:8080/api/home/welcome";
    private static final String FIND_BY_ID_URL = "http://localhost:8080/api/user/find/";
    private static final String FIND_ALL_URL = "http://localhost:8080/api/user/users";
    private static final String SAVE_URL = "http://localhost:8080/api/user/save";
    private static final String UPDATE_URL = "http://localhost:8080/api/user/update/";
    private static final String DELETE_URL = "http://localhost:8080/api/user/delete/";

    public ApplicationClient() {
    }

    public void testApi() throws Exception {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet(HOME_URL));
        status = response.getStatusLine().getStatusCode();

    }

    public void doLogin(String username, char[] password) throws Exception {

        String passwordString = String.valueOf(password);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(LOGIN_URL);

        Gson gson = new Gson();
        String json = gson.toJson(new MyUser(username, passwordString));
        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);

        status = response.getStatusLine().getStatusCode();
        String jsonToken = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        token.setToken(gson.fromJson(jsonToken, Token.class).getToken());

        client.close();
    }

    public void save(MyUser user) throws Exception {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(SAVE_URL);

        Gson gson = new Gson();
        String json = gson.toJson(user);
        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, token.getToken());
        CloseableHttpResponse response = client.execute(httpPost);

        status = response.getStatusLine().getStatusCode();

        if (status == 200) {
            result = "SAVED";
        } else if (status == 400) {
            result = "NOT FOUND";
        } else if (status == 500) {
            result = "Login Expired";
        }

    }

    public void update(MyUser user) throws IOException {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut((UPDATE_URL + user.getId()));

        Gson gson = new Gson();
        String json = gson.toJson(user);
        StringEntity entity = new StringEntity(json);

        httpPut.setEntity(entity);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-Type", "application/json");
        httpPut.setHeader(HttpHeaders.AUTHORIZATION, token.getToken());
        HttpResponse response = client.execute(httpPut);

        status = response.getStatusLine().getStatusCode();

        if (status == 200) {
            result = "UPDATED";
        } else if (status == 400) {
            result = "NOT FOUND";
        } else if (status == 500) {
            result = "Login Expired";
        }

    }

    public MyUser findByID(long id) throws Exception {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(FIND_BY_ID_URL + id);

        Gson gson = new Gson();

        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader(HttpHeaders.AUTHORIZATION, token.getToken());

        HttpResponse response = client.execute(httpGet);

        status = response.getStatusLine().getStatusCode();

        if (status == 200) {
            String responsejson = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            return gson.fromJson(responsejson, MyUser.class);
        } else if (status == 500) {
            result = "Login Expired";
        } else if (status == 400) {
            result = "NOT FOUND";
        }
        return null;
    }

    public List<MyUser> findAll() throws Exception {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(FIND_ALL_URL);

        Gson gson = new Gson();

        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader(HttpHeaders.AUTHORIZATION, token.getToken());

        HttpResponse response = client.execute(httpGet);

        status = response.getStatusLine().getStatusCode();

        if (status == 200) {
            String responsejson = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            List<MyUser> list = gson.fromJson(responsejson, new TypeToken<ArrayList<MyUser>>() {
            }.getType());
            return list;
        } else if (status == 500) {
            result = "Login Expired";
        } else if (status == 400) {
            result = "NOT FOUND";
        }
        return null;
    }

    public void delete(long id) throws Exception {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpDelete httpDelete = new HttpDelete(DELETE_URL + id);

        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Content-Type", "application/json");
        httpDelete.setHeader(HttpHeaders.AUTHORIZATION, token.getToken());

        HttpResponse response = client.execute(httpDelete);

        status = response.getStatusLine().getStatusCode();

        if (status == 200) {
            result = "DELETED";
        } else if (status == 400) {
            result = "NOT FOUND";
        } else if (status == 500) {
            result = "Login Expired";
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

}
