package com.example.wordlegamebackend.game.repository;

import com.example.wordlegamebackend.game.model.Game;
import com.example.wordlegamebackend.word.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<List<Game>> findAllByUserId(Long userId);

    Optional<List<Game>> findAllByWord(Word word);
}
