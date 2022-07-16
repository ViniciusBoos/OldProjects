package br.com.data.spring.data.service;

import br.com.data.spring.data.orm.Cargo;
import br.com.data.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private boolean systema = true;
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository){
        this.cargoRepository = cargoRepository;
    }

    public void inicio(Scanner scanner) {

        while(systema) {
            System.out.println("Escolha uma opção");
            System.out.println("0 - sair");
            System.out.println("1 - salvar novo cargo");
            System.out.println("2 - atualizar cargo ja existente");
            System.out.println("3 - mostrar cargos");
            System.out.println("4 - deletar cargo");
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
                    mostrar();
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
        System.out.println("Digite a descricao do cargo");
        scanner.nextLine();
        Cargo cargo = new Cargo(scanner.nextLine());
        cargoRepository.save(cargo);
        System.out.println("Cargo salvo");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Qual o id desse cargo?");
        Integer idCargo = scanner.nextInt();
        System.out.println("Qual o novo cargo?");
        scanner.nextLine();
        Cargo cargo = new Cargo(scanner.nextLine());
        cargo.setId(idCargo);
        cargoRepository.save(cargo);
    }

    private void mostrar() {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        System.out.println("Qual o id do cargo que vocé quer deletar?");
        Integer idCargo = scanner.nextInt();
        cargoRepository.deleteById(idCargo);
    }
}
