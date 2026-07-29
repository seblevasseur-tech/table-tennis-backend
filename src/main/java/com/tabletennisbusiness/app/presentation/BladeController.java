package com.tabletennisbusiness.app.presentation;

import com.tabletennisbusiness.app.application.BladeApplicationService;
import com.tabletennisbusiness.app.application.PlayerApplicationService;
import com.tabletennisbusiness.app.application.data.AddBladeCommand;
import com.tabletennisbusiness.app.application.data.AddPlayerCommand;
import com.tabletennisbusiness.app.model.Blade;
import com.tabletennisbusiness.app.model.Player;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blades")
public class BladeController {

    private final BladeApplicationService bladeApplicationService;

    @Inject
    public BladeController(BladeApplicationService bladeApplicationService) {
        this.bladeApplicationService = bladeApplicationService;
    }

    @GetMapping
    public List<Blade> getAllBlades() {
        return bladeApplicationService.searchBlades();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Blade> getBladeById(@PathVariable Long id) {
        return bladeApplicationService.findBlade(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Blade updateBlade(@PathVariable Long id, @Valid @ModelAttribute AddBladeCommand command) {
        return bladeApplicationService.updateBlade(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteBlade(@PathVariable Long id) {
        bladeApplicationService.deleteBlade(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Blade createBlade(@ModelAttribute AddBladeCommand command) {
        return bladeApplicationService.addBlade(command);
    }
}
