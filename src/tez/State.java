package tez;

import java.util.ArrayList;


public class State {

	private ArrayList<String> incomingTransitions = new ArrayList<String>();		
	private ArrayList<String> outgoingTransitions = new ArrayList<String>();
	private String uUID;
	private String name;
	private String xPosition="0";
	private String yPosition="0";
	private String chain="";
	
	
	
	public ArrayList<String> getIncomingTransitions() {
		return incomingTransitions;
	}
	public void setIncomingTransitions(ArrayList<String> giris) {
		this.incomingTransitions = giris;
	}
	public ArrayList<String> getOutgoingTransitions() {
		return outgoingTransitions;
	}
	public void setOutgoingTransitions(ArrayList<String> cikis) {
		this.outgoingTransitions = cikis;
	}
	public void addIncomingTransitions(String transition){
		ArrayList<String> transitions = new ArrayList<String>();
		transitions = getIncomingTransitions();
		transitions.add(transition);
		setIncomingTransitions(transitions);
		
	}
	public void addOutgoingTransitions(String transition){
		ArrayList<String> transitions = new ArrayList<String>();
		transitions = getOutgoingTransitions();
		transitions.add(transition);
		setOutgoingTransitions(transitions);
		
	}
	
	public String getuUID() {
		return uUID;
	}
	public void setuUID(String uUID) {
		this.uUID = uUID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getxPosition() {
		return xPosition;
	}
	public void setxPosition(String xPosition) {
		this.xPosition = xPosition;
	}
	public String getyPosition() {
		return yPosition;
	}
	public void setyPosition(String yPosition) {
		this.yPosition = yPosition;
	}
	public String getChain() {
		return chain;
	}
	public void setChain(String chain) {
		this.chain = chain;
	}
	
	public void printAll(){
		String state_control=" ";
		if(getName().equals("Invoke")){
			System.out.print( "  <invokeState uUID=\""+getuUID());
			state_control="Invoke";
		}
		else if (getName().equals("Terminate")){
			System.out.print( "  <terminateState uUID=\""+getuUID());
			state_control="Terminate";
		}
		else
			System.out.print( "  <states uUID=\""+getuUID());
		
		System.out.print( 		"\" name=\""+getName()
								+"\" xPosition=\""+getxPosition()
								+"\" yPosition=\""+getyPosition());
		
		if(!getIncomingTransitions().isEmpty()){
		System.out.print("\" incomingTransitions=\"");
		for(int i = 0; i<getIncomingTransitions().size();i++){
			if(i<getIncomingTransitions().size()-1){
				System.out.print(getIncomingTransitions().get(i)+" ");
			}
			else{
				System.out.print(getIncomingTransitions().get(i));
			}
		}
		}
		if(!getOutgoingTransitions().isEmpty()){
			System.out.print("\" outgoingTransitions=\"");
			for(int i = 0; i<getOutgoingTransitions().size();i++){
				if(i<getOutgoingTransitions().size()-1){
					System.out.print(getOutgoingTransitions().get(i)+" ");
				}
				else{
					System.out.print(getOutgoingTransitions().get(i));
				}
			}
		}
		

		
		if (state_control.equals("Terminate")||state_control.equals("Invoke")){
			System.out.println("\"/>");
		}
		else{
			System.out.println("\">");
			if(!getChain().isEmpty()){
				System.out.println("    <chain href=\""+getChain()+"\"/>");
			}
			System.out.println("  </states>");
		}
		
	}
}
