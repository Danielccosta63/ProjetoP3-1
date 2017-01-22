
public class Employee {
	public String nome;
	public String endereco;
	public int tipo;// 1-hourly,2-salaried,3-comissioned
	public Double salarioHora;
	public Double salarioMensal;
	public Double comissao;
	public int[][] cartaoDePonto = new int[2][31];
	public double[] vendas = new double[31];
	public boolean ativo;
}
