package com.tabletennisbusiness.app.presentation;

import com.tabletennisbusiness.app.application.PlayerApplicationService;
import com.tabletennisbusiness.app.application.data.AddPlayerCommand;
import com.tabletennisbusiness.app.model.Player;
import org.springframework.http.MediaType;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerApplicationService playerApplicationService;

    public PlayerController(PlayerApplicationService playerApplicationService) {
        this.playerApplicationService = playerApplicationService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerApplicationService.searchPlayers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return playerApplicationService.findPlayer(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Player createPlayer(@Valid @ModelAttribute AddPlayerCommand command) {
        return playerApplicationService.addPlayer(command);
    }

//    @GetMapping("/{id}")
//    public Optional<Player> getPlayerById(@PathVariable Long id) {
//        return playerRepository.findById(id);
//    }
//
//
//    @PutMapping("/{id}")
//    public Player updatePlayer(@PathVariable Long id, @RequestBody Player player) {
//        return playerRepository.findById(id)
//                .map(existingPlayer -> {
//                    existingPlayer.setName(player.getName());
//                    existingPlayer.setRating(player.getRating());
//                    return playerRepository.save(existingPlayer);
//                })
//                .orElseGet(() -> {
//                    player.setId(id);
//                    return playerRepository.save(player);
//                });
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletePlayer(@PathVariable Long id) {
//        playerRepository.deleteById(id);
//    }
}
