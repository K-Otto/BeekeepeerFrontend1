package com.otto.beekeeperbackendfinal.Repositories.rest;

import com.otto.beekeeperbackendfinal.Model.User;
import com.otto.beekeeperbackendfinal.Repositories.RestAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 212026992 on 9/2/2016.
 */
public class RestUserAPI implements RestAPI<User, Integer> {

    final String BASE_URL = "http://148.100.5.56:8080/hosp/api/**/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public User get(Integer id) {
        final String url = BASE_URL+"getUserById/"+id.toString();
        HttpEntity<User> requestEntity = new HttpEntity<User>(requestHeaders);
        ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User.class);
        return responseEntity.getBody();
    }

    @Override
    public String post(User entity) {
        final String url = BASE_URL+"saveUser";
        HttpEntity<User> requestEntity = new HttpEntity<User>(entity, requestHeaders);
        HttpEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(User entity) {
        final String url = BASE_URL+"staff/update/"+entity.getName();
        HttpEntity<User> requestEntity = new HttpEntity<User>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(User entity) {
        final String url = BASE_URL+"/staff/delete/"+entity.getName().toString();
        HttpEntity<User> requestEntity = new HttpEntity<User>(entity, requestHeaders);
        ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, User.class);
        return  responseEntity.getBody().getName();
    }

    @Override
    public List<User> getAll() {
        List<User> staff = new ArrayList<>();
        final String url = BASE_URL+"getAllUserDetails";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<User[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User[].class);
        User[] results = responseEntity.getBody();

        for(User staff_ : results){
            staff.add(staff_);
        }

        return staff;
    }
}