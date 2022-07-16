package br.com.data.spring.data.service;

import br.com.data.spring.data.orm.Cargo;
import br.com.data.spring.data.orm.Funcionario;
import br.com.data.spring.data.orm.UnidadeTrabalho;
import br.com.data.spring.data.repository.CargoRepository;
import br.com.data.spring.data.repository.FuncionarioRepository;
import br.com.data.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private boolean systema = true;
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
                                  CargoRepository cargoRepository,
                                  UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.cargoRepository = cargoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicio(Scanner scanner) {

        while(systema) {
            System.out.println("Escolha uma opção");
            System.out.println("0 - sair");
            System.out.println("1 - salvar novo funcionario");
            System.out.println("2 - atualizar funcionario");
            System.out.println("3 - mostrar funcionario");
            System.out.println("4 - deletar funcionario");
            Integer decisao = scanner.nextInt();
            switch (decisao) {
                case 1 : {
                    salvar(scanner);
                    break;
                }
                case 2 : {
                    atualizar(scanner);
                    break;
                }

                case 3 : {
                    mostrar(scanner);
                    break;
                }
                case 4 : {
                    deletar(scanner);
                    break;
                }
                default: {
                    systema = false;
                    break;
                }
            }
        }

    }

    private void salvar(Scanner scanner) {
        Funcionario funcionario = new Funcionario();

        System.out.println("Qual o nome do funcionario?");
        scanner.nextLine();
        funcionario.setNome(scanner.nextLine());

        System.out.println("Qual o cpf do funcionario?");
        funcionario.setCpf(scanner.nextLine());

        System.out.println("Qual o salario do funcionario?");
        funcionario.setSalario(scanner.nextBigDecimal());

        System.out.println("Qual o id do cargo desse funcionario?");
        Integer cargoId = scanner.nextInt();
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargoId(cargo.get());

        List<UnidadeTrabalho> unidade = unidade(scanner);
        funcionario.setUnidadeTrabalhos(unidade);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario salvo");
    }

    private List<UnidadeTrabalho> unidade (Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidade = new ArrayList<>();

        while(isTrue) {
            System.out.println("Digite o id da unidade de trabalho (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<UnidadeTrabalho> unidadeTrabalho = unidadeTrabalhoRepository.findById(unidadeId);
                unidade.add(unidadeTrabalho.get());
            } else {
                isTrue = false;
            }
        }

        return unidade;
    }

    private void atualizar(Scanner scanner) {
        Funcionario funcionario = new Funcionario();
        System.out.println("Qual o id desse funcionario?");
        Integer funcionarioId = scanner.nextInt();
        funcionario.setId(funcionarioId);

        System.out.println("Qual o nome do funcionario?");
        scanner.nextLine();
        funcionario.setNome(scanner.nextLine());

        System.out.println("Qual o cpf do funcionario?");
        funcionario.setCpf(scanner.nextLine());

        System.out.println("Qual o salario do funcionario?");
        funcionario.setSalario(scanner.nextBigDecimal());

        System.out.println("Qual o id do cargo desse funcionario?");
        Integer cargoId = scanner.nextInt();

        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargoId(cargo.get());

        List<UnidadeTrabalho> unidade = unidade(scanner);
        funcionario.setUnidadeTrabalhos(unidade);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario atualizado");
    }

    private void mostrar(Scanner scanner) {
        Pageable pageable = PageRequest.of(0, 5, Sort.unsorted());
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
        System.out.println("Qual pagina voce quer vizualizar? total de paginas = "
                + funcionarios.getTotalPages());
        pageable = PageRequest.of(scanner.nextInt() - 1, 5, Sort.by(Sort.Direction.ASC, "nome"));
        funcionarios = funcionarioRepository.findAll(pageable);
        funcionarios.forEach(System.out::println);
        System.out.println("Pagina atual = " + (funcionarios.getNumber() + 1));
        System.out.println("Elementos = " + funcionarios.getTotalElements());
    }

    private void deletar(Scanner scanner) {
        System.out.println("Qual o id do funcionario que vocé quer deletar?");
        Integer idUnidade = scanner.nextInt();
        funcionarioRepository.deleteById(idUnidade);
    }
}
