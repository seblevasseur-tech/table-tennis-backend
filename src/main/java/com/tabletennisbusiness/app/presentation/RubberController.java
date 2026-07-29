package com.tabletennisbusiness.app.presentation;

import com.tabletennisbusiness.app.application.RubberApplicationService;
import com.tabletennisbusiness.app.application.data.AddRubberCommand;
import com.tabletennisbusiness.app.model.Rubber;
import jakarta.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rubbers")
public class RubberController {

    private final RubberApplicationService rubberApplicationService;

    @Inject
    public RubberController(RubberApplicationService rubberApplicationService) {
        this.rubberApplicationService = rubberApplicationService;
    }

    @GetMapping
    public List<Rubber> getAllRubbers() {
        return rubberApplicationService.searchRubbers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Rubber> getRubberById(@PathVariable Long id) {
        return rubberApplicationService.findRubber(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Rubber createRubber(@ModelAttribute AddRubberCommand command) {
        return rubberApplicationService.addRubber(command);
    }
}
