package br.com.globallabs.springwebmvc.repository;

import br.com.globallabs.springwebmvc.model.Jedi;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JediRepository {

    public List<Jedi> getAllJedi(){
        List<Jedi> jedi = new ArrayList<>();
        jedi.add(new Jedi("Luke", "skywalker"));

        return jedi;
    }
}
