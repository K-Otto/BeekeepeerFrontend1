package com.otto.beekeeperbackendfinal.Services.impl;

import com.otto.beekeeperbackendfinal.Model.User;
import com.otto.beekeeperbackendfinal.Repositories.RestAPI;
import com.otto.beekeeperbackendfinal.Repositories.rest.RestUserAPI;
import com.otto.beekeeperbackendfinal.Services.UserServices;

import java.util.List;

/**
 * Created by 212026992 on 9/2/2016.
 */
public class UserServiceIMPL implements UserServices {

    final RestAPI<User, Integer> rest = new RestUserAPI();

    @Override
    public User findById(Integer id) {
        return rest.get(id);
    }

    @Override
    public String save(User entity) {
        return rest.post(entity);
    }

    @Override
    public String update(User entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(User entity) {
        return rest.delete(entity);
    }

    @Override
    public List<User> findAll() {
        return rest.getAll();
    }
}