package atividade;

/*
 
	Entrega de trabalho
	N�s,
	
	Gian de Manincor Barbosa Guimar�es
	Hugo Luis Ferraz
	Eduardo Luis Ferreira Reis
	
	declaramos que
	todas as respostas s�o fruto de nosso pr�prio trabalho,
	n�o copiamos respostas de colegas externos � equipe,
	n�o disponibilizamos nossas respostas para colegas externos � equipe e
	n�o realizamos quaisquer outras atividades desonestas para nos beneficiar ou
	prejudicar outros.

*/

public class Main {

	public static void main(String[] args) {
		
		CentroGravidade cg = new CentroGravidade();
		String dados = cg.LeArquivo();
		double[] dadosConvertidos = cg.converterParaDouble(dados);
		cg.calcularMatriz(dadosConvertidos);
		
	}

}
