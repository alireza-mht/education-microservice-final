package io.github.alirezamht.educational.service;

import io.github.alirezamht.educational.model.Session;

import java.util.List;

public interface SessionService {

    Session getSessionBySS(String secureString);

    List<Session> getSessionByUserIdentifier(String id);

    void save(Session session);

    void update(Session session);

    void delete(Session session);
}
