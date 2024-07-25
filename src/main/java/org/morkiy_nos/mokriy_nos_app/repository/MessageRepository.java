package org.morkiy_nos.mokriy_nos_app.repository;

import jakarta.validation.constraints.NotNull;
import org.morkiy_nos.mokriy_nos_app.model.Message;
import org.morkiy_nos.mokriy_nos_app.model.MessageType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Optional<Message> findByTitle(String title);

    List<Message> findByType(@NotNull(message = "Выберите опцию") MessageType type, Sort sort);

    List<Message> findByMarked(boolean marked);

    List<Message> findByMarked(boolean marked, Sort sort);

    List<Message> findByMarkedAndType(boolean marked, @NotNull(message = "Выберите опцию") MessageType type, Sort sort);
}
