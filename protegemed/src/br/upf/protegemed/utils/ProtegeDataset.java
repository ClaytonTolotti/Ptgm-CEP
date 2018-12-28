/*
 * autor: Marcelo Rebonatto;
 * adaptação: Clayton Tolotti
 */

package br.upf.protegemed.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.HarmAtual;

public class ProtegeDataset implements Serializable {

	private static final long serialVersionUID = -6577342938668740781L;

	public ProtegeDataset() {
		super();
	}

	public static List<Double> newDatasetOnda(CapturaAtual capturaAtual) {
		
		List<Double> newDataSet = new ArrayList<>();
		
		double[] val = new double[Utils.PONTOSONDA];
		double[] sen = new double[Utils.HARMONICAS];
		double[] cos = new double[Utils.HARMONICAS];
		int i = 0;
		double s;
		double[] tempo = new double[Utils.PONTOSONDA + 1];
		double[] tempoi = new double[Utils.PONTOSONDA + 1];

		for (i = 0; i < Utils.HARMONICAS; i++) {
			sen[i] = 0;
			cos[i] = 0;
		}

		i = 0;
		for (HarmAtual oa : capturaAtual.getListHarmAtual()) {
			sen[i] = oa.getSen();
			cos[i] = oa.getCos();
			i++;
		}

		
		tempo[0] = 0;
		tempoi[0] = (double) 1 / Utils.FREQBASE;
		
		for (i = 0; i < Utils.PONTOSONDA; i++) {
			s = capturaAtual.getMv();
			
			for (int x = 0; x < Utils.HARMONICAS; x++) {
				s += (sen[x] * Math.sin(2 * Math.PI * ((x + 1) * Utils.FREQBASE) * tempo[i]))
						+ (cos[x] * Math.cos(2 * Math.PI * ((x + 1) * Utils.FREQBASE) * tempo[i]));
			}
			
			val[i] = s;
			
			val[i] = (val[i]) / capturaAtual.getGain();
			newDataSet.add(val[i]);
		
			tempo[i + 1] = (tempo[i] + (1.0 / (Utils.FREQBASE * Utils.PONTOSONDA)));
			tempoi[i + 1] = (tempoi[i] - (1.0 / (Utils.FREQBASE * Utils.PONTOSONDA)));
		}

		return newDataSet;
	}
}
