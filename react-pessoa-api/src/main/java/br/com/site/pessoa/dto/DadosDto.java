package br.com.site.pessoa.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DadosDto {

    private PessoaDto pessoaDto;
    private List<ContatoDto> contatoDto;
}
