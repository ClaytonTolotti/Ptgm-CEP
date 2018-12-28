package br.upf.protegemed.utils;

public class Utils {

	private Utils() {
	}
	
	public static final String PASSWORD = "senha.123";
	public static final String USER = "root";
	public static final String BD = "protegemed?useTimezone=true&serverTimezone=UTC";
	public static final String JDBC = "mysql";
	public static final String HOST = "localhost";
	public static final String PORT = "3306";
	public static final String MASK_DATA = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String MASK_YYYY_MM_DD = "yyyy-MM-dd";
	public static final float NORMAL = 0.06F;
	public static final float ATENTION = 0.1F;
	public static final float INTERVENTION = 0.5F;
	public static final Integer VERSAO_FREQUENCIA = 1;

	public static final int MEMORIA = 256 * 1024 * 1024;
	public static final int MAXSALAS = 16;
	public static final int MAXTOMADAS = 12;
	public static final int HARMONICAS = 12;
	public static final int PONTOSONDA = 256;
	public static final int FREQBASE = 60;
	public static final int TEMPOATUALIZARELOGIOS = 1000;
	public static final int TEMPOATUALIZATABELA = 3000;
	public static final int DIGITOSSIGNIFICATIVOS = 3;
	public static final double NIVELDESIGNIFICANCIA = 0.05;

	public static Float convertHexToFloat(String string) {
		return Float.intBitsToFloat(Long.valueOf(Long.parseLong(string, 16)).intValue());
	}
	
	public class QuerySelect {
		private QuerySelect() {}
		public static final String QUERY_EVENTOS = "SELECT codEvento,`desc`,formaOnda FROM protegemed.eventos";
		public static final String QUERY_HARMONICA_ATUAL = "SELECT codCaptura,codHarmonica,sen,cos FROM protegemed.harmatual";
		public static final String QUERY_HARMONICA_PADRAO = "SELECT codHarmonica,codOndaPadrao,sen,cos FROM protegemed.harmpadrao";
		public static final String QUERY_MARCA = "SELECT codMarca, `desc` FROM protegemed.marca";
		public static final String QUERY_MODELO = "SELECT codModelo, `desc` FROM protegemed.modelo";
		public static final String QUERY_PROCEDIMENTO = "SELECT codProced,`desc` FROM protegemed.procedimento";
		public static final String QUERY_RESPONSAVEL = "SELECT codResp,`desc` FROM protegemed.responsavel";
		public static final String QUERY_SALA_CIRURGIA = "SELECT codSala,`desc` FROM protegemed.salacirurgia";
		public static final String QUERY_TIPO = "SELECT codTipo,`desc` FROM protegemed.tipo";
		public static final String QUERY_TIPO_ONDA = "SELECT codTipoOnda,`desc` FROM protegemed.tipoonda";
		public static final String QUERY_TIPO_PADRAO = "SELECT codTipoPadrao,`desc` FROM protegemed.tipopadrao";
		public static final String QUERY_TOMADA = "SELECT codTomada,codSala,indice,modulo,`desc` FROM protegemed.tomada";
		public static final String QUERY_USO_SALA = "SELECT codUsoSala,codSala,codProced,codResp,horaInicio,horaFinal,ativa FROM protegemed.usosala";
		public static final String QUERY_USO_SALA_CAPTURA = "SELECT codCaptura,codUsoSala FROM protegemed.usosalacaptura";
		public static final String QUERY_USO_SALA_EQUIP = "SELECT codEquip,codUsoSala FROM protegemed.usosalaequip";
		public static final String QUERY_CAPTURA_ATUAL = "SELECT codCaptura,codTomada,codTipoOnda,codEquip,codEvento, valorMedio, offset, gain, eficaz, dataAtual, vm2, under, over, duration FROM protegemed.capturaatual";
		public static final String QUERY_EQUIPAMENTO = "SELECT codEquip,codMarca,codModelo,codTipo,codTomada,rfid,codPatrimonio,`desc`,dataUltimaFalha,dataUltimaManutencao,tempoUso FROM protegemed.equipamento";
		public static final String QUERY_ONDA_PADRAO = "SELECT codOndaPadrao,codTipoOnda,codTomada,codEquip,valorMedio,offset,gain,eficaz,dataPadrao,codTipoPadrao FROM protegemed.ondapadrao";
		public static final String QUERY_COD_SALA = "SELECT s.codSala, s.desc FROM protegemed.salacirurgia s, tomada t WHERE t.codSala = s.codSala AND t.codTomada = ?";
		public static final String QUERY_COD_EQUIPAMENTO = "SELECT e.codEquip, e.codMarca, e.codModelo, e.codTipo, e.codPatrimonio, e.desc FROM protegemed.equipamento e WHERE e.rfid = ?";
		public static final String QUERY_FREQ_NORMAL = "SELECT f.valor FROM protegemed.frequencia_normal f inner join versao_frequencia v on f.id_versao = v.id AND v.id = ?";
		public static final String QUERY_FREQ_ATENCAO = "SELECT f.valor FROM protegemed.frequencia_atencao f inner join versao_frequencia v on f.id_versao = v.id AND v.id = ?";
		public static final String QUERY_FREQ_PERIGO = "SELECT f.valor FROM protegemed.frequencia_perigo f inner join versao_frequencia v on f.id_versao = v.id AND v.id = ?";
		public static final String QUERY_ESCALA_FREQUENCIA = "SELECT f.valor, f.frequencia FROM protegemed.escala_frequencia f, protegemed.versao v, protegemed.periculosidade_fuga pf WHERE f.id_versao = v.id AND f.id_tipo = pf.id AND f.id_tipo = ? AND f.id_versao = ?";
		public static final String QUERY_ESCALA_SIMILARIDADE = "SELECT es.valor_min, es.valor_max FROM protegemed.escala_similaridade es, protegemed.versao v, protegemed.periculosidade_fuga pf WHERE es.id_versao = v.id AND es.id_tipo = pf.id AND es.id_tipo = ? AND es.id_versao = ?";
		public static final String QUERY_ESCALA_CORRENTE = "SELECT ec.valor FROM protegemed.escala_corrente ec, protegemed.versao v, protegemed.periculosidade_fuga pf WHERE ec.id_versao = v.id AND ec.id_tipo = pf.id AND ec.id_tipo = ? AND ec.id_versao = ?";
		public static final String QUERY_VERSAO = "SELECT id, data, id_versao, descricao FROM versao WHERE id_versao = ?";
		public static final String QUERY_PERICULOSIDADE_FUGA = "SELECT id,tipo,descricao FROM protegemed.periculosidade_fuga";
		public static final String QUERY_SEQ_CAPTURA_ATUAL = "SELECT COALESCE(MAX(codCaptura), 0) + 1 FROM protegemed.capturaatual";
	}
	
	public class QueryInsert {
		private QueryInsert() {}	
		public static final String INSERT_CAPTURA_ATUAL = "INSERT INTO protegemed.capturaatual (codCaptura, codtomada,codtipoonda,codequip,codevento,valormedio,offset,gain,eficaz,dataatual,vm2,under,`over`,duration) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public static final String INSERT_HARMONICA_ATUAL = "INSERT INTO protegemed.harmatual (codCaptura, codHarmonica, sen, cos) VALUES(?,?,?,?)";
	}
	
	public class QueryUpdate {
		private QueryUpdate() {}
		public static final String UPDATE_DURATION_CAPTURA_ATUAL = "update protegemed.capturaatual set duration = ? where codCaptura in(?,?)";
		public static final String UPDATE_CAPTURA_ATUAL_ORFAO = "update protegemed.capturaatual set duration = -1 where codCaptura = ?";
		public static final String UPDATE_SIMILARIDADE = "UPDATE protegemed.capturaatual SET similaridade = ?, spearman = ?, periculosidade_similaridade = ? WHERE codCaptura in(?,?)";
		public static final String UPDATE_PERICULOSIDADE_CORRENTE = "UPDATE protegemed.capturaatual SET periculosidade_corrente = ? WHERE codCaptura = ?";
		public static final String UPDATE_PERICULOSIDADE_FREQUENCIA = "UPDATE protegemed.capturaatual SET periculosidade_frequencia = ? WHERE codCaptura = ?";
	}

}
