package com.tabletennisbusiness.app.presentation;

import com.tabletennisbusiness.app.application.RubberApplicationService;
import com.tabletennisbusiness.app.application.data.AddRubberCommand;
import com.tabletennisbusiness.app.model.Rubber;
import jakarta.inject.Inject;
import org.springframework.http.MediaType;
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


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Rubber createRubber(@ModelAttribute AddRubberCommand command) {
        return rubberApplicationService.addRubber(command);
    }
}
