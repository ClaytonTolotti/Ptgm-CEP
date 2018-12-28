/*
 * @author: Marcelo Rebonatto
 * Classe usada na versão atual do Protegemed
 * https://github.com/rebonatto/Ptgm-InterfaceJava/blob/master/src/Similaridade/Funcoes.java
 */
package br.upf.protegemed.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CalculosSimilaridade {

	private CalculosSimilaridade() {
	}

	public static double[] pearson(List<Double> onda1List, List<Double> onda2List) {
		double soma1;
		double soma2;
		double media1 = 0.0;
		double media2 = 0.0;
		double pearsonAux;
		double[] pearson = new double[3];
		int tamanho;
		int primeiracorr = 0;

		if (onda1List.size() > onda2List.size()) {
			tamanho = onda2List.size();
		} else {
			tamanho = onda1List.size();
		}

		// obtém a média dos valores
		for (int i = 0; i < tamanho; i++) {
			media1 += onda1List.get(i);
			media2 += onda2List.get(i);
		}
		media1 /= tamanho;
		media2 /= tamanho;

		double desviopadrao1 = Calculos.desvioPadrao(onda1List, media1);
		double desviopadrao2 = Calculos.desvioPadrao(onda2List, media2);

		// for para deslocar onda
		for (int j = 0; j < tamanho; j++) {
			pearsonAux = 0;
			// for para percorrer onda
			for (int i = 0; i < tamanho; i++) {
				soma1 = (onda1List.get(i) - media1) / desviopadrao1;
				if ((i + j) < tamanho) {
					soma2 = (onda2List.get(i + j) - media2) / desviopadrao2;
				} else {
					soma2 = (onda2List.get(i + j - tamanho) - media2) / desviopadrao2;
				}
				pearsonAux += (soma1 * soma2);
			}
			pearsonAux /= (tamanho - 1);

			soma1 = Math.abs(pearsonAux);
			soma2 = Math.abs(pearson[0]);
			if (soma1 > soma2) {
				// armazena melhor pearson deslocando a onda
				pearson[0] = pearsonAux;
				// armazena deslocamento
				pearson[1] = j;
				// armazena pearson sem deslocar onda
				if (primeiracorr == 0) {
					pearson[2] = pearsonAux;
					primeiracorr = 1;
				}
			} else {
				if (soma1 == soma2 && pearsonAux > pearson[0]) {
					pearson[0] = pearsonAux;
					// armazena deslocamento
					pearson[1] = j;
				}
			}
		}
		return pearson;
	}

	public static double[] spearman(List<Double> onda1List, List<Double> onda2List) {
		double soma1;
		double soma2;
		double media1 = 0.0;
		double media2 = 0.0;
		double spearman;
		double spearmanaux1;
		double spearmanaux2;
		double[] maxspearman = new double[3];
		int tamanho;
		int primeirospear = 0;

		if (onda1List.size() > onda2List.size()) {
			tamanho = onda2List.size();
		} else {
			tamanho = onda1List.size();
		}

		for (int i = 0; i < tamanho; i++) {
			media1 += onda1List.get(i);
			media2 += onda2List.get(i);
		}
		media1 /= tamanho;
		media2 /= tamanho;

		for (int j = 0; j < tamanho; j++) {
			spearman = 0.0;
			spearmanaux1 = 0.0;
			spearmanaux2 = 0.0;

			for (int i = 0; i < tamanho; i++) {
				soma1 = onda1List.get(i) - media1;
				if ((i + j) < tamanho) {
					soma2 = onda2List.get(i + j) - media2;
				} else {
					soma2 = onda2List.get(i + j - tamanho) - media2;
				}
				spearman += soma1 * soma2;
				spearmanaux1 += soma1 * soma1;
				spearmanaux2 += soma2 * soma2;
			}

			spearmanaux1 *= spearmanaux2;
			spearmanaux1 = Math.sqrt(spearmanaux1);
			spearman /= spearmanaux1;

			soma1 = Math.abs(spearman);
			soma2 = Math.abs(maxspearman[0]);
			if (soma1 > soma2) {
				maxspearman[0] = spearman;
				maxspearman[1] = j;
				if (primeirospear == 0) {
					maxspearman[2] = spearman;
					primeirospear = 1;
				}
			} else {
				if (soma1 == soma2 && spearman > maxspearman[0]) {
					maxspearman[0] = spearman;
					maxspearman[1] = j;
				}
			}
		}
		return maxspearman;
	}

	/*
	 * author Clayton Tolotti
	 * param lista
	 * referência: http://www.portalaction.com.br/inferencia/64-teste-de-shapiro-wilk
	 * 
	 */
	public static boolean shapiroWilk(List<Double> lista) {

		double media = .0;
		double somatorio = .0;
		double b = 0.0;
		double w = 0.0;
		double significancia;
		
		Integer indic = lista.size();

		// Ordenar o vetor
		Collections.sort(lista, new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {

				return o1.compareTo(o2);
			}
		});

		// Encontrar média
		for (Double d : lista) {
			media += d;
		}

		media /= lista.size();

		//(xi - media(x))²
		for (Double d : lista) {
			somatorio += Math.pow((d - media), 2);
		}

		for (int i = (lista.size() - 1); i < lista.size(); i++) {
			for (int j = 0; j < (lista.size() / 2); j++) {
				b += (ValoresShapiroWilk.getMatrizcoeficientes(j,i) * (lista.get(indic - 1) - lista.get(j)));
				indic -= 1;
			}
		}
		if(somatorio != 0.0) {
			w = (Math.pow(b, 2)) / somatorio;	
		}

		for (int i = 0; i < 9; i++) {
			if (ValoresShapiroWilk.getMatriznivelsignificancia(0,i) == Utils.NIVELDESIGNIFICANCIA) {
				indic =  i;
			}
		}
		
		significancia = ValoresShapiroWilk.getMatriznivelsignificancia(lista.size(),indic);
		
		return w > significancia;
	}
}
