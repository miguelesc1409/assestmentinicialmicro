package com.example.demo.controllers;

import com.example.demo.model.panelista;
import com.example.demo.services.panelistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/panelista")
public class panelistaController {
    private final panelistaService panelistaService;

    @Autowired
    public panelistaController(panelistaService panelistaService)
    {
        this.panelistaService = panelistaService;
    }

    @GetMapping("/all")
    public List<panelista> getpanelista()
    {
        return this.panelistaService.getpanelista();
    }


    @PostMapping("/add")
    public ResponseEntity<Object> registrarpanelista (@RequestBody panelista panelista)
    {
        return this.panelistaService.newpanelista(panelista);
    }

    @PutMapping("/update/{panelistaID}")
    public ResponseEntity<Object> actualizarpanelista (@RequestBody panelista panelista, @PathVariable("panelistaID") Long id)
    {
        return this.panelistaService.updatepanelista(panelista, id);
    }

    @DeleteMapping(path = "/delete/{panelistaID}")
    public ResponseEntity<Object> eliminarpanelista(@PathVariable("panelistaID") Long id)
    {
        return this.panelistaService.deletepanelista(id);
    }
}