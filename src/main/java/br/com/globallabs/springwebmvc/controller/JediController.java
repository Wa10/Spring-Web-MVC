package br.com.globallabs.springwebmvc.controller;

import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class JediController {

    @Autowired
    private JediRepository repository;

    @GetMapping("/jedi")
    public ModelAndView jedi(){

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        modelAndView.addObject("allJedi", repository.findAll());

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "name") String name){

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        modelAndView.addObject("allJedi", repository.findByNameContainingIgnoreCase(name));

        return modelAndView;
    }

    @GetMapping("/new-jedi")
    public String createJedi(Model model){

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-jedi");

        model.addAttribute("jedi", new Jedi());
        return "new-jedi";
    }

    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "new-jedi";
        }

        repository.save(jedi);
        redirectAttributes.addFlashAttribute("message", "Jedi, cadastrado com sucesso");

        return "redirect:jedi";
    }

    @GetMapping("/jedi/{id}/delete")
    public String deleteJedi(@PathVariable("id") final Long id, RedirectAttributes redirectAttributes) {
        final Optional<Jedi> jedi = repository.findById(id);

        repository.delete(jedi.get());
        redirectAttributes.addFlashAttribute("message", "Jedi removido com sucesso.");

        return "redirect:/jedi";
    }

    @GetMapping("/jedi/{id}/update")
    public String updateJedi(@PathVariable("id") final Long id, Model model) {
        final Optional<Jedi> jedi = repository.findById(id);

        model.addAttribute("jedi", jedi.get());

        return "edit-jedi";
    }
}
