package br.com.data.spring.data.service;

import br.com.data.spring.data.orm.UnidadeTrabalho;
import br.com.data.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private boolean system = true;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicio(Scanner scanner) {

        while (system) {
            System.out.println("Escolha uma opção");
            System.out.println("0 - sair");
            System.out.println("1 - salvar nova Unidade de trabalho");
            System.out.println("2 - atualizar Unidade ja existente");
            System.out.println("3 - mostrar Unidades de trabalho");
            System.out.println("4 - deletar unidade de trabalho");

            Integer decisao = scanner.nextInt();

            switch (decisao) {
                case 1: {
                    salvar(scanner);
                    break;
                }
                case 2: {
                    atualizar(scanner);
                    break;
                }
                case 3: {
                    mostrar();
                    break;
                }
                case 4: {
                    deletar(scanner);
                    break;
                }
                default: {
                    system = false;
                    break;
                }
            }
        }

    }

    private void salvar(Scanner scanner) {
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

        System.out.println("Digite a descricao da unidade de trabalho");
        scanner.nextLine();
        String nome = scanner.nextLine();
        unidadeTrabalho.setDescricao(nome);

        System.out.println("Digite o endereco da unidade de trabalho");
        nome = scanner.nextLine();
        unidadeTrabalho.setEndereco(nome);

        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade salva");
    }

    private void atualizar(Scanner scanner) {
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

        System.out.println("Qual o id dessa unidade de trabalho?");
        Integer idUnidade = scanner.nextInt();

        System.out.println("Digite a descricao da unidade de trabalho nova");
        scanner.nextLine();
        unidadeTrabalho.setDescricao(scanner.nextLine());

        System.out.println("Digite o endereco da unidade de trabalho nova");
        unidadeTrabalho.setEndereco(scanner.nextLine());

        unidadeTrabalho.setId(idUnidade);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
    }

    private void mostrar() {
        Iterable<UnidadeTrabalho> unidadesTrabalho = unidadeTrabalhoRepository.findAll();
        unidadesTrabalho.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        System.out.println("Qual o id da unidade que vocé quer deletar?");
        Integer idUnidade = scanner.nextInt();
        unidadeTrabalhoRepository.deleteById(idUnidade);
        System.out.println("Deletado");
    }
}
