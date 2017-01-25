import java.util.Scanner;

public class FolhaDePagamento {
	static Scanner scan = new Scanner(System.in);
	static Employee funcionario[] = new Employee[256];

	public static double aux;
	public static int auxInt;
	public static int systemDia;
	public static int alteracao = 0;
	public static int idAux = 0;
	public static int total_funcionarios = 0;

	public static void add_employee(Employee funcionario[], int indiceFuncionarios) {
		funcionario[indiceFuncionarios] = new Employee();
		int tipo;
		double c;

		System.out.print("Digite o nome do empregado: ");
		scan.nextLine();
		funcionario[indiceFuncionarios].nome = scan.nextLine();

		System.out.print("\nDigite o endereço do empregado: ");
		funcionario[indiceFuncionarios].endereco = scan.nextLine();

		System.out.print("\nDigite o tipo (1-Horista, 2-Assalariado ou 3-Commissionado): ");
		tipo = scan.nextInt();
		funcionario[indiceFuncionarios].tipo = tipo;

		if (tipo == 1) {
			System.out.print("\nSalario por hora: ");
			funcionario[indiceFuncionarios].salarioHora = scan.nextDouble();
			funcionario[indiceFuncionarios].nomeTipo = "Horista";
		} else if (tipo == 2) {
			System.out.print("\nSalario mensal: ");
			funcionario[indiceFuncionarios].salarioMensal = scan.nextDouble();
			funcionario[indiceFuncionarios].nomeTipo = "Assalariado";
		} else if (tipo == 3) {
			System.out.print("\nSalario mensal: ");
			funcionario[indiceFuncionarios].salarioMensal = scan.nextDouble();
			System.out.println("\nComissão: ");
			c = scan.nextDouble();
			funcionario[indiceFuncionarios].comissao = c;
			funcionario[indiceFuncionarios].nomeTipo = "Comissionado";
		}
		System.out.print("Pertence ao sindicato? 1-Sim 2-Não ");
		tipo = scan.nextInt();
		if (tipo == 1) {
			funcionario[indiceFuncionarios].sindicato = 1;
		}
		System.out.println("Escolha o método de pagamento");
		System.out.println("1- Cheque pelos correios, 2-Cheque em mãos, 3-Deposito em conta bancaria");
		funcionario[indiceFuncionarios].metodoDePagamento = scan.nextInt();

		alteracao = 1;
		idAux = indiceFuncionarios;
		System.out.println("---------------------------------");
		System.out.println("Empregado adicionado com sucesso!");
		System.out.printf("O ID do empregado é [%d]\n", indiceFuncionarios);
		if (tipo == 1) {
			System.out.printf("O ID no sindicato é [%d]\n", 1000 - indiceFuncionarios);
		}

	}

	public static void remove_employee(Employee funcionario[], int total_funcionarios) {
		int id;
		System.out.print("Por favor digite o código do empregado: ");
		id = scan.nextInt();
		funcionario[id].ativo = false;
		alteracao = 2;
		idAux = id;
		System.out.println("Empregado removido com sucesso!");
	}

	public static void add_card(Employee funcionario[]) {
		int id;
		System.out.println("Digite o ID do empregado");
		id = scan.nextInt();
		System.out.println("Digite as horas trabalhadas?");
		funcionario[id].cartaoDePonto[systemDia - 1] = scan.nextInt();
		alteracao = 3;
		idAux = id;
		System.out.println("Cartão Adicionado com Sucesso!");
	}

	public static void add_sale(Employee funcionario[]) {
		int id;
		double valor;
		System.out.print("Digite o ID do empregado: ");
		id = scan.nextInt();
		System.out.print("\nDigite o valor das vendas: ");
		valor = scan.nextInt();
		funcionario[id].vendas += valor * (funcionario[id].comissao / 100.00);
		alteracao = 4;
	}

	public static void add_rate(Employee funcionario[]) {
		int id;
		double valor;
		System.out.print("Digite o ID do empregado: ");
		id = scan.nextInt();
		System.out.println("\nDigite o valor da taxa :");
		valor = scan.nextDouble();
		funcionario[id].taxas = valor / 100.00;
		alteracao = 5;
	}

	public static void change_data(Employee funcionario[]) {
		int id, tipo;
		System.out.println("Digite ID do empregado: ");
		id = scan.nextInt();

		System.out.print("Digite o novo nome do empregado: ");
		scan.nextLine();
		funcionario[id].nome = scan.nextLine();

		System.out.print("\nDigite o novo endereço do empregado: ");
		funcionario[id].endereco = scan.nextLine();

		System.out.print("\nDigite o tipo (1-Horista, 2-Assalariado ou 3-Commissionado: ");
		tipo = scan.nextInt();
		funcionario[id].tipo = tipo;

		if (tipo == 1) {
			System.out.print("\nSalario por hora: ");
			funcionario[id].salarioHora = scan.nextDouble();
		} else if (tipo == 2) {
			System.out.print("\nSalario mensal: ");
			funcionario[id].salarioMensal = scan.nextDouble();
		} else if (tipo == 3) {
			System.out.print("\nSalario mensal: ");
			funcionario[id].salarioMensal = scan.nextDouble();
			System.out.println("\nComissão: ");
			funcionario[id].comissao = scan.nextDouble();
		}
		System.out.print("Pertence ao sindicato? 1-Sim 2-Não ");
		tipo = scan.nextInt();
		if (tipo == 1) {
			funcionario[id].sindicato = 1;
			System.out.printf("O ID do funcionario no sindicato é %d\n", 1000 - id);
		}
		System.out.println("Escolha o método de pagamento");
		System.out.println("1- Cheque pelos correios, 2-Cheque em mãos, 3-Deposito em conta bancaria");
		funcionario[id].metodoDePagamento = scan.nextInt();
		System.out.println("Dados alterados com sucesso");
	}

	public static void rotate_sheet(Employee funcionario[]) {
		int id, dia, diaAux, tipo;
		double desconto = 0;
		System.out.println("Digite o dia");
		dia = scan.nextInt();
		diaAux = dia % 7;

		for (id = 0; id < total_funcionarios; id++) {
			tipo = funcionario[id].tipo;
			desconto += funcionario[id].taxas;
			if (funcionario[id].sindicato == 1) {
				desconto += 0.02;
			}
			if (funcionario[id].ativo == true) {

				if (tipo == 1) {
					if (diaAux == 6) {
						for (int i = dia - 7; i < dia; i++) {
							funcionario[id].salarioAcumulado += funcionario[id].cartaoDePonto[i]
									* funcionario[id].salarioHora;
						}
						System.out.printf("%s Deve receber %.2f\n", funcionario[id].nome,
								funcionario[id].salarioAcumulado - (funcionario[id].salarioAcumulado * desconto));
						funcionario[id].salarioAcumulado = 0.0;
					}
				} else if (tipo == 2) {
					if (dia == funcionario[id].diaPagamento) {
						System.out.printf("%s Deve receber %.2f\n", funcionario[id].nome,
								funcionario[id].salarioMensal - (funcionario[id].salarioMensal * desconto));
					}
				} else if (tipo == 3) {
					if (dia == funcionario[id].diaPagamento) {
						System.out.printf("%s Deve receber %.2f\n", funcionario[id].nome,
								(funcionario[id].salarioMensal + funcionario[id].vendas
										- ((funcionario[id].salarioMensal + funcionario[id].vendas) * desconto)));

					}
				}
				desconto = 0.0;

			}
		}

	}

	public static void undo(Employee funcionario[]) {
		if (alteracao == 1) {
			funcionario[idAux].ativo = false;
			alteracao = 11;
		} else if (alteracao == 2) {
			funcionario[idAux].ativo = true;
			alteracao = 21;
		} else if (alteracao == 3) {
			auxInt = funcionario[idAux].cartaoDePonto[systemDia - 1];
			funcionario[idAux].cartaoDePonto[systemDia - 1] = 0;
			alteracao = 31;
		} else if (alteracao == 4) {
			aux = funcionario[idAux].vendas;
			funcionario[idAux].vendas = 0;
			alteracao = 41;
		} else if (alteracao == 5) {
			aux = funcionario[idAux].taxas;
			funcionario[idAux].taxas = 0;
			alteracao = 51;
		} else if (alteracao == 12) {
			funcionario[idAux].ativo = false;
			alteracao = 11;
		} else if (alteracao == 22) {
			funcionario[idAux].ativo = true;
			alteracao = 21;
		} else if (alteracao == 32) {
			auxInt = funcionario[idAux].cartaoDePonto[systemDia - 1];
			funcionario[idAux].cartaoDePonto[systemDia - 1] = 0;
			alteracao = 31;
		} else if (alteracao == 42) {
			aux = funcionario[idAux].vendas;
			funcionario[idAux].vendas = 0;
			alteracao = 41;
		} else if (alteracao == 52) {
			aux = funcionario[idAux].taxas;
			funcionario[idAux].taxas = 0;
			alteracao = 51;
		}
	}

	public static void redo(Employee funcionario[]) {
		if (alteracao == 11) {
			funcionario[idAux].ativo = true;
			alteracao = 12;
		} else if (alteracao == 21) {
			funcionario[idAux].ativo = false;
			alteracao = 22;
		} else if (alteracao == 31) {
			funcionario[idAux].cartaoDePonto[systemDia - 1] = auxInt;
			alteracao = 32;
		} else if (alteracao == 41) {
			funcionario[idAux].vendas = aux;
			alteracao = 42;
		} else if (alteracao == 51) {
			funcionario[idAux].taxas = aux;
			alteracao = 52;
		}
	}

	public static void listar(Employee funcionario[]) {
		System.out.println("-NOME-------------ID------TIPO");
		for (int i = 0; i < total_funcionarios; i++) {
			if (funcionario[i].ativo == true) {
				System.out.printf("%s %d %S\n", funcionario[i].nome, i, funcionario[i].nomeTipo);
			}
		}
	}

	public static void create_calendar(Employee funcionario[]) {
		int id;
		System.out.println("Digite o ID do empregado");
		id = scan.nextInt();
		System.out.println("Digite um nome para o novo tipo:");
		funcionario[id].nomeTipo = scan.next();
		System.out.println("Digite a nova data: ");
		scan.nextLine();
		funcionario[id].diaPagamento = scan.nextInt();
	}

	public static void main(String[] args) {
		int op;
		System.out.println("Sistema de Folha de Pagamento");
		System.out.println("-----------------------------");
		System.out.println("Primeiro vamos configurar o sistema");
		System.out.print("Dia de hoje:");
		systemDia = scan.nextInt();

		do {
			System.out.println("-----------------------------");
			System.out.println("Selecione uma opção\n\n");
			System.out.println("1 -> Adicionar um empregado");
			System.out.println("2 -> Remover um empregado");
			System.out.println("3 -> Adicionar cartão de ponto");
			System.out.println("4 -> Adicionar vendas");
			System.out.println("5 -> Adicionar taxas");
			System.out.println("6 -> Alterar dados");
			System.out.println("7 -> Rodar folha");
			System.out.println("8 -> Undo");
			System.out.println("9 -> Redo");
			System.out.println("10 -> Listar Empregados");
			System.out.println("11 -> Criar novo calendario");
			System.out.println("0 -> Sair");

			op = scan.nextInt();

			if (op == 1) {
				add_employee(funcionario, total_funcionarios);
				total_funcionarios++;
			} else if (op == 2)
				remove_employee(funcionario, total_funcionarios);
			else if (op == 3)
				add_card(funcionario);
			else if (op == 4)
				add_sale(funcionario);
			else if (op == 5)
				add_rate(funcionario);
			else if (op == 6)
				change_data(funcionario);
			else if (op == 7)
				rotate_sheet(funcionario);
			else if (op == 8)
				undo(funcionario);
			else if (op == 9)
				redo(funcionario);
			else if (op == 10)
				listar(funcionario);
			else if (op == 11)
				create_calendar(funcionario);
			else if (op == 0) {
				System.exit(0);
			}
		} while (op != 0);

	}

}
