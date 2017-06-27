package tez;

public class Transitions {

	private String uUID;
	
	private String sourceState;
	private String targetState;
	private String value;
	private String key;
	
	public String getuUID() {
		return uUID;
	}
	public void setuUID(String uUID) {
		this.uUID = uUID;
	}
	public String getSourceState() {
		return sourceState;
	}
	public void setSourceState(String sourceState) {
		this.sourceState = sourceState;
	}
	public String getTargetState() {
		return targetState;
	}
	public void setTargetState(String targetState) {
		this.targetState = targetState;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void printAll(){
		System.out.print( "  <transitions uUID=\""+getuUID()
								+"\" sourceState=\""+getSourceState()
								+"\" targetState=\""+getTargetState());
		
		System.out.println("\">");
		System.out.println("    <probabilities>");
		System.out.println("      <key href=\""+getKey()+"\"/>");
		System.out.println("      <value frequency=\"Normal\" value=\""+getValue()+"\"/>");
		System.out.println("    </probabilities>");
		System.out.println("  </transitions>");

	}
}
