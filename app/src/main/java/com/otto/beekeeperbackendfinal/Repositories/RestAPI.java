package com.otto.beekeeperbackendfinal.Repositories;

import java.util.List;

/**
 * Created by 212026992 on 9/2/2016.
 */
public interface RestAPI<S, ID> {

        S get(ID id);

        String post(S entity);

        String put(S entity);

        String delete(S entity);

        List<S> getAll();
        }