package br.com.globallabs.springwebmvc.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "jedi")
public class Jedi {

    @Id
    @Column(name="id_jedi")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Size(max = 20, message = "Last Name must not have more than 20 letters")
    @Column(name = "last_name")
    private String lastName;

    public Jedi(final String name, final String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Jedi() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
