package com.example.carcassonne.data.chat;

import com.example.carcassonne.domain.model.СhatMessage;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<СhatMessage, Integer> {

}
