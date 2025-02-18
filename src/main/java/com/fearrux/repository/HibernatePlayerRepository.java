package com.fearrux.repository;

import com.fearrux.entity.Player;
import com.fearrux.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Optional;

public class HibernatePlayerRepository implements PlayerRepository {

    @Override
    public Optional<Player> findByName(String name) {
        try (Session session = HibernateUtil.get().openSession()) {
            return session.createQuery(
                            "FROM Player player WHERE player.name = :name",
                            Player.class
                    )
                    .setParameter("name", name)
                    .uniqueResultOptional();
        }
    }

    @Override
    public Optional<Player> findById(Long id) {
        try (Session session = HibernateUtil.get().openSession()) {
            return Optional.ofNullable(session.find(Player.class, id));
        }
    }

    @Override
    public Player save(Player player) {
        try (Session session = HibernateUtil.get().openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
            return player;
        }
    }
}
