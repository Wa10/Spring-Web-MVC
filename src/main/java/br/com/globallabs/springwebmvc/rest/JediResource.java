package br.com.globallabs.springwebmvc.rest;


import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class JediResource {

    @Autowired
    private JediService service;

    @GetMapping("/api/jedi")
    public List<Jedi> getAllJedi(){
        return service.findAll();
    }

    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> getJedi(@PathVariable("id") Long id){
        Jedi jedi =  service.findById(id);

        return ResponseEntity.ok(jedi);
    }

    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi createJedi(@Valid @RequestBody Jedi jedi){
        return service.save(jedi);
    }

    @PutMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> updateJedi(@PathVariable("id") Long id, @Valid @RequestBody Jedi newJedi){
        Jedi updatedJedi = service.update(id, newJedi);

        return ResponseEntity.ok(updatedJedi);
    }

    @DeleteMapping("/api/jedi/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJedi(@PathVariable("id") Long id){
        service.delete(id);
    }
}
