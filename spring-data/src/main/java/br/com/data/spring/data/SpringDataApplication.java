package br.com.data.spring.data;

import br.com.data.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private boolean system = true;

	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final RelatorioService relatorioService;
	private final RelatorioFuncionarioDynamic relatorioFuncionarioDynamic;

	public SpringDataApplication(CrudCargoService crudCargoService,
								 CrudFuncionarioService crudFuncionarioService,
								 CrudUnidadeTrabalhoService crudUnidadeTrabalhoService,
								 RelatorioService relatorioService,
								 RelatorioFuncionarioDynamic relatorioFuncionarioDynamic) {
		this.crudCargoService = crudCargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.relatorioService = relatorioService;
		this.relatorioFuncionarioDynamic = relatorioFuncionarioDynamic;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Oque deseja fazer?");
		System.out.println("0 - sair");
		System.out.println("1 - Cargo");
		System.out.println("2 - Unidade de trabalho");
		System.out.println("3 - Funcionario");
		System.out.println("4 - Relatorio");
		System.out.println("5 - Relatorio funcionario dinamico");

		Integer action = scanner.nextInt();

		switch (action) {
			case 1: {
				crudCargoService.inicio(scanner);
				break;
			}
			case 2: {
				crudUnidadeTrabalhoService.inicio(scanner);
				break;
			}
			case 3: {
				crudFuncionarioService.inicio(scanner);
				break;
			}

			case 4: {
				relatorioService.inicio(scanner);
				break;
			}

			case 5: {
				relatorioFuncionarioDynamic.inicio(scanner);
				break;
			}

			default: {
				system = false;
				break;
			}
		}
	}
}
