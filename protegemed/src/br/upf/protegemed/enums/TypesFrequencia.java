package br.upf.protegemed.enums;

public enum TypesFrequencia {
	NORMAL("NORMAL"),
	ATENCAO("ATENCAO"),
	PERIGO("PERIGO");
	
	private String url;
	 
    TypesFrequencia(String envUrl) {
        this.url = envUrl;
    }
 
    public String getUrl() {
        return url;
    }
}
