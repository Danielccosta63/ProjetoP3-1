
public class Employee {
	public String nome;
	public String endereco;
	public int tipo;// 1-hourly,2-salaried,3-comissioned
	public String nomeTipo;
	public Double salarioHora;
	public Double salarioMensal;
	public Double comissao;
	public int[] cartaoDePonto = new int[31];
	public double vendas;
	public int sindicato=0;
	public double taxas;
	public int metodoDePagamento; //1- Cheque pelos correios, 2-Cheque em mãos, 3-Deposito em conta bancaria
	public int diaPagamento=31;
	public double salarioAcumulado;
	public boolean ativo=true;
	
}
