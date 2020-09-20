package atividade;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CentroGravidade {
	
	String enderecoArquivo = "C:\\Users\\Gian\\eclipse-workspace\\atividade\\src\\atividade\\dados";
	String dadoLido = "";
	String dados = "";
	String[] dadosEmString;
	double[] dadosEmDouble;
	int quantidadeLinha;
	int quantidadeColuna;
	int indiceLinha;
	int indiceColuna;
	double matriz[][];
	double somaLinha[];
	double somaLinhaAuxiliar;
	double somaLinhaAuxiliar2;
	double somaColunaAuxiliar;
	double somaColunaAuxiliar2;
	double somaColuna[];
	double maiorLinha;
	double maiorColuna;
	
	public String LeArquivo() {
		
		try {
			// Le o arquivo
			FileReader leitorArquivo = new FileReader(enderecoArquivo);
			BufferedReader buffer = new BufferedReader(leitorArquivo);
			dadoLido = buffer.readLine();
			dados = dadoLido;
			
			//Joga os dados do arquivo para uma String
			while(dadoLido != null) {
				dadoLido = buffer.readLine();
				dados += dadoLido;
			}
			
			buffer.close();
		// Mostra um erro caso o caminho esteja errado	
		} catch (FileNotFoundException e) {
			System.out.println("Ocorreu um erro! Verifique o caminho do arquivo informado." + e.getMessage());
			e.printStackTrace();
		// Mostra um erro caso não consiga ler o arquivo
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao tentar ler o arquivo!" + e.getMessage());
			e.printStackTrace();
		}
		return dados; 
		
	}

	public double[] converterParaDouble(String dados) {
		
		// quebra os dados após os expaços e coloca em um vetor
		dadosEmString = dados.split(" ");
		// Cria um vetor de Double do tamanho do 'dadosEmString'
		dadosEmDouble = new double[dadosEmString.length - 1];
		
		//Converte todos os valores de String para Double e coloca em um vetor de Double.
		for(int i = 0; i < dadosEmDouble.length; i++) {
			dadosEmDouble[i] = Double.valueOf(dadosEmString[i]).doubleValue();
		}
		return dadosEmDouble;
	}

	public void calcularMatriz(double[] dadosConvertidos) {
		
		// Pega o tamanho de linha e coloca e coloca na variavel
		quantidadeLinha = (int) dadosConvertidos[0];
		quantidadeColuna = (int) dadosConvertidos[1];
		
		// Cria matriz do tamanho da linha e coluca
		matriz = new double[quantidadeLinha][quantidadeColuna];
		
		// Crio varivel para colocar os valores das somas
		somaLinha = new double[quantidadeLinha];
		somaColuna = new double[quantidadeColuna];
		
		//Pega os dados Convertidos e coloca dentro da Matriz
		int indice = 2;
		for(int i = 0; i < quantidadeLinha; i ++) {
			for(int j = 0; j < quantidadeColuna; j++) {
				matriz[i][j] = dadosConvertidos[indice++];
			}
		}
		
		//Somando linhas
		for(int i = 0; i < quantidadeLinha; i ++) {
			for(int j = 0; j < quantidadeColuna; j++) {
				somaLinha[i] += matriz[i][j];
			}
		}
		//Somando colunas
		for(int i = 0; i < quantidadeColuna; i ++) {
			for(int j = 0; j < quantidadeLinha; j++) {
				somaColuna[i] += matriz[j][i];
			}
		}

		//verificando qual é o centro de gravidade
		maiorLinha = somaLinha[0];
		maiorColuna = somaColuna[0];
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		// Verificando qual é centro de gravidade da linha
		for(int i = 0; i < somaLinha.length; i++) {
			somaLinhaAuxiliar = 0;
			somaLinhaAuxiliar2 = 0;
			for(int j = 0; j < somaLinha.length; j++) {
				if(i != j) {
					if(j < i) {
						somaLinhaAuxiliar += somaLinha[j];
					} else {
						somaLinhaAuxiliar2 += somaLinha[j];
					}
				}
			}
			if(somaLinhaAuxiliar2 != 0L && somaLinhaAuxiliar != 0L) {
				double total = Math.abs(somaLinhaAuxiliar2 - somaLinhaAuxiliar);
				df.setRoundingMode(RoundingMode.UP);
				total = Double.valueOf(df.format(total).replace(",", "."));
				if(total == 0.1) {
					indiceLinha = i;
				}
			}
		}
		
		// Verificando qual é centro de gravidade da coluna
		for(int i = 0; i < somaColuna.length; i++) {
			somaColunaAuxiliar = 0;
			somaColunaAuxiliar2 = 0;
			for(int j = 0; j < somaColuna.length; j++) {
				if(i != j) {
					if(j < i) {
						somaColunaAuxiliar += somaColuna[j];
					} else {
						somaColunaAuxiliar2 += somaColuna[j];
					}
				}
			}
			if(somaColunaAuxiliar2 != 0L && somaColunaAuxiliar != 0L) {
				double total = Math.abs(somaColunaAuxiliar2 - somaColunaAuxiliar);
				df.setRoundingMode(RoundingMode.UP);
				total = Double.valueOf(df.format(total).replace(",", "."));
				if(total == 0.1 || total == 0) {
					indiceColuna = i;
				}
			}
		}
		System.out.println("Centro de Gravidade = [" + indiceLinha + "," + indiceColuna + "].");
	}

}
