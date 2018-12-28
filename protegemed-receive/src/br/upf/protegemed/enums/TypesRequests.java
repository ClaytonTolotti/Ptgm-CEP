package br.upf.protegemed.enums;

public enum TypesRequests {
	RFID("RFID"),
	TYPE("TYPE"),
	OUTLET("OUTLET"),
	OFFSET("OFFSET"),
	GAIN("GAIN"),
	RMS("RMS"),
	MV("MV"),
	MV2("MV2"),
	UNDER("UNDER"),
	OVER("OVER"),
	DURATION("DURATION"),
	SIN("SIN"),
	COS("COS"),
	DATE("DATE");
	
	private String url;
	 
    TypesRequests(String envUrl) {
        this.url = envUrl;
    }
 
    public String getUrl() {
        return url;
    }
}
