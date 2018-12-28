package br.upf.protegemed.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.OndaPadrao;

/**
 *
 * @author rebonatto
 */
public class Calculos {

	private Calculos() {
		super();
	}

	public static float ajustaRMSValorMedio(CapturaAtual c) {

		float res;
		float total;
		float mod;
		float vm;
		float totalharm = 0.0F;

		if (c.getMv() == 0)
			return c.getEficaz();
		// coloca o valor do VM
		vm = Math.abs((c.getMv() / 256 - c.getOffset()) / c.getGain());
		total = vm;

		// calcula e adiciona cada uma das barras
		for (HarmAtual h : c.getListHarmAtual()) {
			mod = modulo(h.getSen(), h.getCos(), c.getGain());
			totalharm += mod;
		}
		total += totalharm;
		res = (totalharm * c.getEficaz()) / total;
		return res;
	}

	public static float modulo(float sen, float cos, float gain) {
		/* calcula o modulo, igual ao grafico do programa */
		float f = (float) (Math.sqrt(sen * sen + cos * cos) / 128);
		f = f / gain;

		return f;
	}

	public static float picoDoisRMS(float f) {
		/* ajusta o valor de pico para RMS */
		f = f * f;
		f = f / 2;
		f = (float) Math.sqrt(f);
		return f;
	}

	public static OndaPadrao minDifValorMedio(CapturaAtual cap) {
		OndaPadrao ondaPadrao = null;

		float dif;
		float aux;

		Collection<OndaPadrao> lista = new ArrayList<>();
		dif = Float.MAX_VALUE;

		for (OndaPadrao onda : lista) {
			aux = Math.abs(cap.getMv() - onda.getValorMedio());

			if (aux < dif) {
				ondaPadrao = onda;
				dif = aux;
			}
		}
		return ondaPadrao;
	}

	public static double casasDouble(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places);
		return bd.doubleValue();
	}

	public static double desvioPadrao(List<Double> onda, double media) {
		double elevar;
		double desvio = 0.0;

		int tamanho = onda.size();
		int i;

		for (i = 0; i < tamanho; i++) {
			elevar = onda.get(i) - media;
			desvio += (elevar * elevar);
		}
		desvio /= (tamanho - 1);
		desvio = Math.sqrt(desvio);
		return desvio;
	}

	public static double variancia(List<Double> onda) {
		double media = 0.0;
		double elevar;
		double variancia = 0.0;

		int tamanho = onda.size();
		int i;

		for (i = 0; i < tamanho; i++) {
			media += (onda.get(i));
		}
		media /= tamanho;
		for (i = 0; i < tamanho; i++) {
			elevar = onda.get(i) - media;
			variancia += (elevar * elevar);
		}

		variancia /= (tamanho - 1);
		return variancia;
	}

	public static Double calcularSpearman(CapturaAtual capturaAtualOne, CapturaAtual capturaAtualTwo) {

		List<Double> listaUm = ProtegeDataset.newDatasetOnda(capturaAtualOne);
		List<Double> listaDois = ProtegeDataset.newDatasetOnda(capturaAtualTwo);
		double[] res = null;
		res = CalculosSimilaridade.spearman(listaUm, listaDois);
		return Math.abs(Double.valueOf(res[0]));
//		res = CalculosSimilaridade.spearman(listaUm, listaDois);
//		
//		if (res[0] >= 0.95 || res[0] <= 1.0) {
//			return 3;
//		} else if (res[0] <= 0.84900) {
//			return 1;
//		} else {
//			return 2;
//		}
	}
}
