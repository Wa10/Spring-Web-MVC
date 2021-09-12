package br.com.globallabs.springwebmvc.service;

import br.com.globallabs.springwebmvc.exception.JediNotFoundException;
import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JediService {

    @Autowired
    private JediRepository repository;

    public List<Jedi> findAll() {
        return repository.findAll();
    }

    public Jedi findById(Long id) {
        Optional<Jedi> jedi = repository.findById(id);

        if(jedi.isPresent()){
            return jedi.get();
        } else {
            throw new JediNotFoundException();
        }
    }

    public Jedi save(Jedi jedi) {
        return repository.save(jedi);
    }

    public Jedi update(Long id, Jedi newJedi) {

        Optional<Jedi> jedi = repository.findById(id);
        Jedi updatedJedi;

        if(jedi.isPresent()){
            updatedJedi = jedi.get();
        } else {
            throw new JediNotFoundException();
        }

        updatedJedi.setName(newJedi.getName());
        updatedJedi.setLastName(newJedi.getLastName());

        return repository.save(updatedJedi);
    }

    public void delete(Long id) {
        Jedi jedi = findById(id);

        repository.delete(jedi);
    }
}
