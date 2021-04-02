package com.example.demo.repositaty;

import com.example.demo.entity.Player;
import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByPlayerOrderByCreatedDateDesc(Player player);
    List<Post> findAllByOrderByCreatedDateDesc();
    Optional<Post> findPostByIdAndPlayer(Long id, Player player);

}
