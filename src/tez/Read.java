package tez;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Read {

	private ArrayList<State> allStates = new ArrayList<State>();		
	private ArrayList<Transitions> allTransitions = new ArrayList<Transitions>();
	private String version;
	private String diagram;
	private String chain;

	private ArrayList<String> stateName = new ArrayList<String>();
	private ArrayList<String> linkValue1 = new ArrayList<String>();
	private ArrayList<String> linkValue2 = new ArrayList<String>();
	private ArrayList<String> linkValue3 = new ArrayList<String>();
	
	public ArrayList<State> getAllStates() {
		return allStates;
	}


	public void setAllStates(ArrayList<State> allStates) {
		this.allStates = allStates;
	}


	public ArrayList<Transitions> getAllTransitions() {
		return allTransitions;
	}


	public void setAllTransitions(ArrayList<Transitions> allTransitions) {
		this.allTransitions = allTransitions;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public String getDiagram() {
		return diagram;
	}
	public void setDiagram(String diagram) {
		this.diagram = diagram;
	}
	public String getChain() {
		return chain;
	}
	public void setChain(String chain) {
		this.chain = chain;
	}
	
	public ArrayList<String> getStateName() {
		return stateName;
	}

	public void setStateName(ArrayList<String> stateName) {
		this.stateName = stateName;
	}

	public ArrayList<String> getLinkValue1() {
		return linkValue1;
	}

	public void setLinkValue1(ArrayList<String> linkValue1) {
		this.linkValue1 = linkValue1;
	}

	public ArrayList<String> getLinkValue2() {
		return linkValue2;
	}

	public void setLinkValue2(ArrayList<String> linkValue2) {
		this.linkValue2 = linkValue2;
	}

	public ArrayList<String> getLinkValue3() {
		return linkValue3;
	}

	public void setLinkValue3(ArrayList<String> linkValue3) {
		this.linkValue3 = linkValue3;
	}


	public void data(String args){
		ArrayList<String> allStateName = getStateName();
		ArrayList<String> allLinkValue1 = getLinkValue1();
		ArrayList<String> allLinkValue2 = getLinkValue2();
		ArrayList<String> allLinkValue3 = getLinkValue3();
		try {
			File file = new File(args);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
				while ((line = bufferedReader.readLine()) != null) {
					//System.out.println(line);
					String[] out = line.split("	:",2);
					String[] o = out[1].split("	",4);
					allStateName.add(out[0]);
					allLinkValue1.add(o[1]);
					allLinkValue2.add(o[2]);
					allLinkValue3.add(o[3]);					
				}
			
				setStateName(allStateName);
				setLinkValue1(allLinkValue1);
				setLinkValue2(allLinkValue2);
				setLinkValue3(allLinkValue3);
				
			fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void read (String args)  {
		ArrayList<State> allStates = getAllStates();
		ArrayList<Transitions> allTransitions = getAllTransitions();
		try {
			File file = new File(args);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.indexOf("?xml")>=0){
				    setVersion(line);
					}
				if(line.indexOf("<diagram")>=0){
					setDiagram(line);
				}
				if(line.indexOf("<chain")>=0)
				{
					setChain(line);
				}
				//System.out.println(line);
				//Read transitions item: uUID, sourceState, targetState, key, value
				if((line.indexOf("transitions ")>=0)){
					Transitions transitions = new Transitions();
					if(line.indexOf("uUID")>=0){
						String[] out = line.split("uUID",2);
						String[] o = out[1].split("\"",3);
						transitions.setuUID(o[1]);
					}
					if(line.indexOf("sourceState")>=0){
						String[] out = line.split("sourceState",2);
						String[] o = out[1].split("\"",3);
						transitions.setSourceState(o[1]);
					}
					if(line.indexOf("targetState")>=0){
						String[] out = line.split("targetState",2);
						String[] o = out[1].split("\"",3);
						transitions.setTargetState(o[1]);	
					}

					while ((line = bufferedReader.readLine()).indexOf("<probabilities>")<=0) {}
					line = bufferedReader.readLine(); /*<probabilities>*/
					if(line.indexOf("key")>=0){
						String[] out = line.split("href=",2);
						String[] o = out[1].split("\"",3);
						transitions.setKey(o[1]);	
					}
					line = bufferedReader.readLine();
					if(line.indexOf("value")>=0){
						String[] out = line.split("value=",2);
						String[] o = out[1].split("\"",3);
						transitions.setValue(o[1]);	
					}		
						//transitions.printAll();
						allTransitions.add(transitions);						
				}
				
				if((line.indexOf("<states")>=0)||(line.indexOf("<terminateState")>=0)||(line.indexOf("<invokeState")>=0)){
					State state = new State();
					ArrayList<String> incoming = new ArrayList<String>();
					ArrayList<String> outgoing = new ArrayList<String>();
					if(line.indexOf("uUID")>=0){
						String[] out = line.split("uUID",2);
						String[] o = out[1].split("\"",3);
						state.setuUID(o[1]);
					}
					if(line.indexOf("name")>=0){
						String[] out = line.split("name",2);
						String[] o = out[1].split("\"",3);
						state.setName(o[1]);	
					}
					if(line.indexOf("xPosition")>=0){
						String[] out = line.split("xPosition",2);
						String[] o = out[1].split("\"",3);
						state.setxPosition(o[1]);	
					}
					if(line.indexOf("yPosition")>=0){
						String[] out = line.split("yPosition",2);
						String[] o = out[1].split("\"",3);
						state.setyPosition(o[1]);
					}
					if(line.indexOf("incomingTransitions")>=0){
						String[] out = line.split("incomingTransitions",2);
						String[] o = out[1].split("\"",3);
						String[] incom = o[1].split(" ");
						for(int i=0; i<incom.length; i++){
							incoming.add(incom[i]);
						}
						state.setIncomingTransitions(incoming);
					}
					if(line.indexOf("outgoingTransitions")>=0){
						String[] out = line.split("outgoingTransitions",2);
						String[] o = out[1].split("\"",3);
						String[] outgo = o[1].split(" ");
						for(int i=0; i<outgo.length; i++){
							outgoing.add(outgo[i]);
						}	
						state.setOutgoingTransitions(outgoing);
					}
					if((line.indexOf("<states")>=0)&&(line.indexOf("\">")>=0)){ 
						line=bufferedReader.readLine();
						if(line.indexOf("<chain")>=0)
						{
							String[] out = line.split("href=",2);
							String[] o = out[1].split("\"",3);
							state.setChain(o[1]);
						}
					}

					
					//state.printAll();	
					allStates.add(state);
					
				}

				setAllTransitions(allTransitions);
				setAllStates(allStates);
			
			}
			fileReader.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void write(){

		System.out.println(getVersion());
		System.out.println(getDiagram());
		
		for(int i=0;i<getAllStates().size();i++){
			getAllStates().get(i).printAll();
			for(int j=0;j<getAllTransitions().size();j++){
				if(getAllTransitions().get(j).getSourceState().equals(getAllStates().get(i).getuUID())){
					getAllTransitions().get(j).printAll();
				}
			}
		}
		System.out.println(getChain());
		System.out.println("</diagram:Diagram>");
		
	
	}
	
}
