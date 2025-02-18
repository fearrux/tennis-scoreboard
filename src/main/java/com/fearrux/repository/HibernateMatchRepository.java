package com.fearrux.repository;

import com.fearrux.entity.Match;
import com.fearrux.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class HibernateMatchRepository implements MatchRepository {

    @Override
    public List<Match> findByName(String name, int offset, int limit) {
        try (Session session = HibernateUtil.get().openSession()) {
            return session.createQuery(
                            """
                                         SELECT match FROM Match match
                                         WHERE match.firstPlayer.name = :name or match.secondPlayer.name = :name
                                    """,
                            Match.class
                    )
                    .setParameter("name", name)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        }
    }

    @Override
    public List<Match> findAll(int offset, int limit) {
        try (Session session = HibernateUtil.get().openSession()) {
            return session.createQuery("SELECT match FROM Match match", Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        }
    }

    @Override
    public Optional<Match> findById(Long id) {
        try (Session session = HibernateUtil.get().openSession()) {
            return Optional.ofNullable(session.find(Match.class, id));
        }
    }

    @Override
    public Match save(Match entity) {
        try (Session session = HibernateUtil.get().openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }
}
