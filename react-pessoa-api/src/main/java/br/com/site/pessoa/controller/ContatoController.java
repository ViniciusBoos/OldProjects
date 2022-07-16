package br.com.site.pessoa.controller;

import br.com.site.pessoa.model.PessoaContato;
import br.com.site.pessoa.repository.ContatoRepository;
import br.com.site.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/contatos")
    public List<PessoaContato> contatoList() {

        return contatoRepository.findAll();
    }
}
