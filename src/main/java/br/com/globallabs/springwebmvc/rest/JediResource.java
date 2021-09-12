package br.com.globallabs.springwebmvc.rest;

import br.com.globallabs.springwebmvc.exception.JediNotFoundException;
import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class JediResource {

    @Autowired
    private JediRepository repository;

    @GetMapping("/api/jedi")
    public List<Jedi> getAllJedi(){
        return repository.findAll();
    }

    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> getJedi(@PathVariable("id") Long id){

        final Optional<Jedi> jedi =  repository.findById(id);

        if(jedi.isPresent()){
            return ResponseEntity.ok(jedi.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi createJedi(@Valid @RequestBody Jedi jedi){

        return repository.save(jedi);
    }

    @PutMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> updateJedi(@PathVariable("id") Long id, @Valid @RequestBody Jedi newJedi){

        final Optional<Jedi> jedi = repository.findById(id);
        final Jedi updatedJedi;

        if(jedi.isPresent()){
        updatedJedi = jedi.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        updatedJedi.setName(newJedi.getName());
        updatedJedi.setLastName(newJedi.getLastName());

        return ResponseEntity.ok(repository.save(updatedJedi));
    }

    @DeleteMapping("/api/jedi/{id}")
    public ResponseEntity deleteJedi(@PathVariable("id") Long id){

        final Optional<Jedi> jedi = repository.findById(id);
        if(jedi.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
