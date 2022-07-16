package br.com.data.spring.data.service;

import br.com.data.spring.data.orm.Funcionario;
import br.com.data.spring.data.orm.FuncionarioProcecao;
import br.com.data.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {

    private final FuncionarioRepository funcionarioRepository;
    private boolean system = true;

    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicio(Scanner scanner) {

        while (system) {
            System.out.println("Escolha uma opção");
            System.out.println("0 - sair");
            System.out.println("1 - Listar por nome");
            System.out.println("2 - Listar por Data de contratacao e salario maior que");
            System.out.println("3 - Listar por Data de contratacao maior que");
            System.out.println("4 - Listar com salario");
            Integer decisao = scanner.nextInt();
            switch (decisao) {
                case 1: {
                    buscaFuncionarioNome(scanner);
                    break;
                }
                case 2: {
                    buscaFuncionarioPorDataEsalario(scanner);
                    break;
                }

                case 3: {
                    buscaFuncionarioPorDataMaiorQue(scanner);
                    break;
                }

                case 4: {
                    buscaFuncionarioSalario(scanner);
                    break;
                }

                default: {
                    system = false;
                    break;
                }
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner) {
        System.out.println("Qual o nome do funcionario que dejesa procurar?");
        scanner.nextLine();
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(scanner.nextLine());
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioPorDataEsalario(Scanner scanner) {
        System.out.println("Qual a data de contratacao que voce deseja procurar? dia/mes/ano");
        scanner.nextLine();
        String data = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataContratacao = LocalDate.parse(data, formatter);
        System.out.println("Os funcionarios que voce deseja procurar tem um salario acima de qual valor?");
        BigDecimal salario = scanner.nextBigDecimal();
        List<Funcionario> funcionarios = funcionarioRepository.encontrePorDataESalarioMaiorQue(
                                            dataContratacao, salario);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioPorDataMaiorQue(Scanner scanner) {
        System.out.println("Acima de qual a data de contratacao que voce deseja procurar? dia/mes/ano");
        scanner.nextLine();
        String data = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataContratacao = LocalDate.parse(data, formatter);
        List<Funcionario> funcionarios = funcionarioRepository.
                encontrePorDataContratacaoMaiorQue(dataContratacao);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioSalario(Scanner scanner) {
        List<FuncionarioProcecao> salarios = funcionarioRepository.encontrePorSalario();
        salarios.forEach(f -> System.out.println("ID: " + f.getId() + " | Nome: " + f.getNome() +
                " | Salario: " + f.getSalario()));
    }
}
