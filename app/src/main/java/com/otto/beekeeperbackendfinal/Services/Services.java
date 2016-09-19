package com.otto.beekeeperbackendfinal.Services;

import java.util.List;

/**
 * Created by 212026992 on 9/2/2016.
 */
public interface Services<S, ID> {

    public S findById(ID id);

    public String save(S entity);

    public String update(S entity);

    public String delete(S entity);

    public List<S> findAll();
}