package br.com.site.pessoa.controller;

import br.com.site.pessoa.dto.ContatoDto;
import br.com.site.pessoa.dto.DadosDto;
import br.com.site.pessoa.dto.DadosUpdateDto;
import br.com.site.pessoa.dto.DeletePessoaDto;
import br.com.site.pessoa.model.*;
import br.com.site.pessoa.repository.*;
import br.com.site.pessoa.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PessoaController {

    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
    @Autowired
    private ContatoRepository contatoRepository;


    @CrossOrigin("http://localhost:3000")
    @PostMapping("/pessoa")
    public ResponseEntity<Pessoa> pessoaPost(@RequestBody DadosDto dadosDto) {

        Pessoa pessoa = dadosDto.getPessoaDto().getPessoa(paisRepository);
        PessoaEndereco endereco = dadosDto.getPessoaDto().getEndereco(estadoRepository, cidadeRepository);
        PessoaTelefone telefone = dadosDto.getPessoaDto().getTelefone();

        endereco.setPessoa(pessoa);
        telefone.setPessoa(pessoa);

        pessoaRepository.save(pessoa);
        telefoneRepository.save(telefone);
        enderecoRepository.save(endereco);

        for (ContatoDto contato : dadosDto.getContatoDto()) {
            PessoaContato pessoaContato = contato.createContato(pessoa);
            contatoRepository.save(pessoaContato);
        }
        if (pessoa.getTipoPessoa() == TipoPessoa.PESSOA_FISICA) {
            PessoaFisica pessoaFisica = dadosDto.getPessoaDto().getPessoaFisica(pessoa);
            pessoaFisicaRepository.save(pessoaFisica);
        } else {
            PessoaJuridica pessoaJuridica = dadosDto.getPessoaDto().getPessoaJuridica(pessoa);
            pessoaJuridicaRepository.save(pessoaJuridica);
        }
        return ResponseEntity.ok(pessoa);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/emails")
    public List<String> getEmails() {
        return pessoaRepository.findAllEmails();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/pessoas")
    public List<DadosUpdateDto> getPessoas(@RequestParam String codigo) {

        codigo = codigo + "%";
        List<Pessoa> pessoas = pessoaRepository.findByNomeLike(codigo);
        List<DadosUpdateDto> dadosUpdateList = new ArrayList<DadosUpdateDto>();

        for (Pessoa pessoa : pessoas) {
            DadosUpdateDto dadosUpdate = new DadosUpdateDto();
            List<PessoaContato> contatos = contatoRepository.findByPessoaId(pessoa.getIdPessoa());
            PessoaTelefone pessoaTelefone = telefoneRepository.findByPessoaId(pessoa.getIdPessoa());
            PessoaEndereco pessoaEndereco = enderecoRepository.findByPessoaId(pessoa.getIdPessoa());
            if (pessoa.getTipoPessoa() == TipoPessoa.PESSOA_FISICA) {
                PessoaFisica pessoaFisica = pessoaFisicaRepository.findByPessoaId(pessoa.getIdPessoa());
                dadosUpdate.setPessoaFisica(pessoaFisica);
            } else {
                PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findByPessoaId(pessoa.getIdPessoa());
                dadosUpdate.setPessoaJuridica(pessoaJuridica);
            }

            dadosUpdate.setPessoaEndereco(pessoaEndereco);
            dadosUpdate.setPais(pessoa.getPais());
            dadosUpdate.setEstado(pessoaEndereco.getEstado());
            dadosUpdate.setCidade(pessoaEndereco.getCidade());
            dadosUpdate.setPessoaTelefone(pessoaTelefone);
            dadosUpdate.setPessoa(pessoa);
            dadosUpdate.setContatoList(contatos);
            dadosUpdateList.add(dadosUpdate);
        }
        return dadosUpdateList;
    }

    @CrossOrigin("http://localhost:3000")
    @DeleteMapping("/deletePessoa")
    public ResponseEntity deletePessoa(@RequestBody DeletePessoaDto deletePessoaDto) {

        Optional<Pessoa> optional = pessoaRepository.findById(Long.parseLong(deletePessoaDto.getId()));
        if (optional.isPresent()) {
            Pessoa pessoa = optional.get();
            PessoaEndereco endereco = enderecoRepository.findByPessoaId(pessoa.getIdPessoa());
            List<PessoaContato> contatos = contatoRepository.findByPessoaId(pessoa.getIdPessoa());
            PessoaTelefone pessoaTelefone = telefoneRepository.findByPessoaId(pessoa.getIdPessoa());
            PessoaEndereco pessoaEndereco = enderecoRepository.findByPessoaId(pessoa.getIdPessoa());

            if (pessoa.getTipoPessoa() == TipoPessoa.PESSOA_FISICA) {
                PessoaFisica pessoaFisica = pessoaFisicaRepository.findByPessoaId(pessoa.getIdPessoa());
                pessoaFisicaRepository.delete(pessoaFisica);
            } else {
                PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findByPessoaId(pessoa.getIdPessoa());
                pessoaJuridicaRepository.delete(pessoaJuridica);
            }

            for (PessoaContato contato :
                    contatos) {
                contatoRepository.delete(contato);
            }
            telefoneRepository.delete(pessoaTelefone);
            enderecoRepository.delete(pessoaEndereco);
            pessoaRepository.delete(pessoa);
            return ResponseEntity.ok(pessoa);
        }

        return (ResponseEntity) ResponseEntity.notFound();

    }

    @CrossOrigin("http://localhost:3000")
    @PutMapping("/updatePessoa")
    public ResponseEntity<Pessoa> updatePessoa(@RequestBody DadosDto dadosDto) {

        if (dadosDto.getPessoaDto().getId() != null) {
            Pessoa pessoa = pessoaRepository.getById(Long.parseLong(dadosDto.getPessoaDto().getId()));
            pessoa.setNome(dadosDto.getPessoaDto().getNome());
            pessoa.setEmail(dadosDto.getPessoaDto().getEmail());
            pessoa.setPais(paisRepository.getById(Long.parseLong(dadosDto.getPessoaDto().getPais())));

            if (dadosDto.getPessoaDto().getTipo().equals("0")) {
                if (pessoa.getTipoPessoa() != TipoPessoa.PESSOA_FISICA) {
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findByPessoaId(pessoa.getIdPessoa());
                    pessoaJuridicaRepository.delete(pessoaJuridica);

                    pessoa.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
                    PessoaFisica pessoaFisica = dadosDto.getPessoaDto().getPessoaFisica(pessoa);
                    pessoaFisicaRepository.save(pessoaFisica);
                } else {
                    PessoaFisica pessoaFisica = pessoaFisicaRepository.findByPessoaId(pessoa.getIdPessoa());
                    pessoaFisica.setRg(dadosDto.getPessoaDto().getRg());
                    pessoaFisicaRepository.save(pessoaFisica);
                }

            } else {
                if (pessoa.getTipoPessoa() != TipoPessoa.PESSOA_JURIDICA) {
                    PessoaFisica pessoaFisica = pessoaFisicaRepository.findByPessoaId(pessoa.getIdPessoa());
                    pessoaFisicaRepository.delete(pessoaFisica);

                    pessoa.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
                    PessoaJuridica pessoaJuridica = dadosDto.getPessoaDto().getPessoaJuridica(pessoa);
                    pessoaJuridicaRepository.save(pessoaJuridica);
                } else {
                    PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findByPessoaId(pessoa.getIdPessoa());
                    pessoaJuridica.setCnae(dadosDto.getPessoaDto().getCnae());
                    pessoaJuridicaRepository.save(pessoaJuridica);
                }
            }

            PessoaEndereco endereco = enderecoRepository.findByPessoaId(pessoa.getIdPessoa());

            if (!dadosDto.getPessoaDto().getCidade().equals("0")
                    && !dadosDto.getPessoaDto().getCidade().equals("1")) {
                endereco.setCidade(cidadeRepository.getById(Long.parseLong(dadosDto.getPessoaDto().getCidade())));
                enderecoRepository.save(endereco);
            } else {
                endereco.setCidade(null);
                enderecoRepository.save(endereco);
            }
            if (!dadosDto.getPessoaDto().getEstado().equals("0")
                    && !dadosDto.getPessoaDto().getEstado().equals("1")) {
                endereco.setEstado(estadoRepository.getById(dadosDto.getPessoaDto().getEstado()));
                enderecoRepository.save(endereco);
            } else {
                endereco.setEstado(null);
                enderecoRepository.save(endereco);
            }

            endereco.setRua(dadosDto.getPessoaDto().getRua());
            endereco.setBairro(dadosDto.getPessoaDto().getBairro());
            endereco.setCep(dadosDto.getPessoaDto().getCep());
            endereco.setFachada(dadosDto.getPessoaDto().getFachada());
            endereco.setComplemento(dadosDto.getPessoaDto().getComplemento());

            PessoaTelefone pessoaTelefone = telefoneRepository.findByPessoaId(pessoa.getIdPessoa());
            String ddd = dadosDto.getPessoaDto().getNumero().substring(0, 2);
            String numeroApenasDoTelefone = dadosDto.getPessoaDto().getNumero().substring(2);
            pessoaTelefone.setDdd(ddd);
            pessoaTelefone.setTelefone(numeroApenasDoTelefone);
            pessoaTelefone.setObservacao(dadosDto.getPessoaDto().getObsNumero());

            List<PessoaContato> contatosADeletar = contatoRepository.findByPessoaId(pessoa.getIdPessoa());
            List<ContatoDto> contatoDtos = dadosDto.getContatoDto();
            List<PessoaContato> contatos = new ArrayList<>();

            for(ContatoDto contatoDto:
                contatoDtos) {
                if(contatoDto.getIdContato() == null) {
                    PessoaContato contato = contatoDto.createContato(pessoa);
                    contatos.add(contato);
                }
                if(contatoDto.getIdContato() != null) {
                    Optional<PessoaContato> possivelContato = contatoRepository
                            .findByIdContato(Long.parseLong(contatoDto.getIdContato()));
                    if(possivelContato.isPresent()) {
                        PessoaContato pessoaContato = possivelContato.get();
                        contatosADeletar.remove(pessoaContato);

                        ContatoService.alterarContato(contatoDto, pessoaContato);
                        contatos.add(pessoaContato);
                    }

                }
            }
            for (PessoaContato pessoaContato:
                 contatosADeletar) {
                contatoRepository.delete(pessoaContato);
            }
            pessoaRepository.save(pessoa);
            telefoneRepository.save(pessoaTelefone);
            enderecoRepository.save(endereco);

            for (PessoaContato pessoaContato:
                 contatos) {
                contatoRepository.save(pessoaContato);
            }

            return ResponseEntity.ok(pessoa);
        }
        return (ResponseEntity<Pessoa>) ResponseEntity.badRequest();
    }
}
