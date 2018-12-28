package br.upf.protegemed.beans;

public class ParamRequest {
	
	private String RFID;
	private String TYPE;
	private String OUTLET;
	private String OFFSET;
	private String GAIN;
	private String RMS;
	private String MV;
	private String MV2;
	private String UNDER;
	private String OVER;
	private String DURATION;
	private String SIN;
	private String COS;
	
	public String getRFID() {
		return RFID;
	}
	public void setRFID(String rFID) {
		RFID = rFID;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getOUTLET() {
		return OUTLET;
	}
	public void setOUTLET(String oUTLET) {
		OUTLET = oUTLET;
	}
	public String getOFFSET() {
		return OFFSET;
	}
	public void setOFFSET(String oFFSET) {
		OFFSET = oFFSET;
	}
	public String getGAIN() {
		return GAIN;
	}
	public void setGAIN(String gAIN) {
		GAIN = gAIN;
	}
	public String getRMS() {
		return RMS;
	}
	public void setRMS(String rMS) {
		RMS = rMS;
	}
	public String getMV() {
		return MV;
	}
	public void setMV(String mV) {
		MV = mV;
	}
	public String getMV2() {
		return MV2;
	}
	public void setMV2(String mV2) {
		MV2 = mV2;
	}
	public String getUNDER() {
		return UNDER;
	}
	public void setUNDER(String uNDER) {
		UNDER = uNDER;
	}
	public String getOVER() {
		return OVER;
	}
	public void setOVER(String oVER) {
		OVER = oVER;
	}
	public String getDURATION() {
		return DURATION;
	}
	public void setDURATION(String dURATION) {
		DURATION = dURATION;
	}
	public String getSIN() {
		return SIN;
	}
	public void setSIN(String sIN) {
		SIN = sIN;
	}
	public String getCOS() {
		return COS;
	}
	public void setCOS(String cOS) {
		COS = cOS;
	}	
}
