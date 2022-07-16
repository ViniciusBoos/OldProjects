package br.com.data.spring.data.service;

import br.com.data.spring.data.orm.Funcionario;
import br.com.data.spring.data.repository.FuncionarioRepository;
import br.com.data.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDynamic {

    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDynamic(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicio(Scanner scanner) {
        System.out.println("Digite NULL caso não deseje procurar por esse atributo");
        System.out.println("O nome que deseja procurar e parecido com qual?");
        scanner.nextLine();
        String nome = scanner.nextLine();

        if(nome.equalsIgnoreCase("NULL")) {
            nome = null;
        }

        System.out.println("Qual o cpf que deseja procurar?");
        String cpf = scanner.nextLine();

        if(cpf.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        System.out.println("O salario que deseja procurar e maior que?");
        String sal = scanner.nextLine();
        BigDecimal salario;

        if(sal.equalsIgnoreCase("Null")) {
            salario = null;
        } else {
            salario = new BigDecimal(sal);
        }

        System.out.println("A data do funcionario que deseja procurar vem depois de? dia/mes/ano");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String data = scanner.nextLine();

        LocalDate dataContratacao;
        if(data.equalsIgnoreCase("NULL")){
            dataContratacao = null;
        } else {
            dataContratacao = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.
                where(SpecificationFuncionario.nome(nome))
                .or(SpecificationFuncionario.cpf(cpf))
                .or(SpecificationFuncionario.salario(salario))
                .or(SpecificationFuncionario.dataContratacao(dataContratacao)));
        System.out.println();
        funcionarios.forEach(System.out::println);
        System.out.println();
    }
}
