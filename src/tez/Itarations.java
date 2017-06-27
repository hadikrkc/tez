package tez;

import java.util.ArrayList;

public class Itarations {

	public void itarations1(String args,String args1){
		Read read = new Read();
		read.read(args);
		read.data(args1);
		
		for(int count=0; count<read.getStateName().size(); count++ ){
			for(int i=0;i<read.getAllStates().size();i++){
				if(read.getAllStates().get(i).getName().equals(read.getStateName().get(count))){
					//read.getAllStates().get(i).printAll();
					for(int j=0;j<read.getAllTransitions().size();j++){
						if(read.getAllTransitions().get(j).getuUID().equals(read.getAllStates().get(i).getIncomingTransitions().get(0))){
							read.getAllTransitions().get(j).setValue(read.getLinkValue2().get(count));
							//read.getAllTransitions().get(j).printAll();
						}
					}
				}
			}
		}

		
		read.write();
	}
	public void itarations2(String args,String args1){
		Read read = new Read();
		read.read(args);
		read.data(args1);
		//String [] s  = {"game","bank","shop"};
		//String [] v = {"0.30","0.10","0.60"};
		
		ArrayList<State> allStates = new ArrayList<State>();
		ArrayList<Transitions> allTransitions = new ArrayList<Transitions>();
		allStates=read.getAllStates();
		allTransitions=read.getAllTransitions();
		
		State state = new State();
		String stateName = "Error State for Static Analyzes";
		state.setuUID("_Static");
		state.setName(stateName);
		
		/*create transitions*/
		for(int i =0; i<read.getStateName().size(); i++){
			for(int j=0;j<read.getAllStates().size();j++){
				//if(read.getAllStates().get(j).getName().equals(s[i])){
				if(read.getAllStates().get(j).getName().equals(read.getStateName().get(i))){
					Transitions transition = new Transitions();
					transition.setuUID(read.getAllStates().get(j).getuUID()+"-_Static");
					transition.setSourceState(read.getAllStates().get(j).getuUID());
					transition.setTargetState("_Static");
					transition.setKey(read.getAllTransitions().get(0).getKey());
					//transition.setValue(v[i]);
					transition.setValue(read.getLinkValue2().get(i));
						for(int t=0;t<read.getAllTransitions().size();t++){
							if(read.getAllTransitions().get(t).getSourceState().equals(read.getAllStates().get(j).getuUID())){
								String value = read.getAllTransitions().get(t).getValue();
								
								float foo1 = Float.parseFloat(value);
								float foo2 = Float.parseFloat(read.getLinkValue2().get(i));
								
								String fo = Float.toString((1-foo2)*foo1);
								read.getAllTransitions().get(t).setValue(fo);
							}							
						}
					//transition.printAll();
					allTransitions.add(transition);
					state.addIncomingTransitions(read.getAllStates().get(j).getuUID()+"-_Static");
					read.getAllStates().get(j).addOutgoingTransitions(read.getAllStates().get(j).getuUID()+"-_Static");
				}
			}
				
		}
		for(int i=0;i<read.getAllStates().size();i++){
			if(read.getAllStates().get(i).getName().equals("Terminate")){
				Transitions transition = new Transitions();
				transition.setuUID("_Static"+"-"+read.getAllStates().get(i).getuUID());
				transition.setSourceState("_Static");
				transition.setTargetState(read.getAllStates().get(i).getuUID());
				transition.setKey(read.getAllTransitions().get(0).getKey());
				transition.setValue("1.000");
				allTransitions.add(transition);
				state.addOutgoingTransitions("_Static"+"-"+read.getAllStates().get(i).getuUID());
				read.getAllStates().get(i).addIncomingTransitions("_Static"+"-"+read.getAllStates().get(i).getuUID());
			}
		}
		
		allStates.add(state);
		read.setAllStates(allStates);
		read.write();
	
	}
	public void itarations3(String args,String args1){
		Read read = new Read();
		read.read(args);
		read.data(args1);
		
		ArrayList<State> allStates = new ArrayList<State>();
		ArrayList<Transitions> allTransitions = new ArrayList<Transitions>();
		allStates=read.getAllStates();
		allTransitions=read.getAllTransitions();
		
		State state = new State();
		String stateName = "Error State for Dynamic Analyzes";
		state.setuUID("_Error");
		state.setName(stateName);
		
		/*create transitions*/
		for(int i =0; i<read.getStateName().size(); i++){
			for(int j=0;j<read.getAllStates().size();j++){
				if(read.getAllStates().get(j).getName().equals(read.getStateName().get(i))){
					Transitions transition = new Transitions();
					transition.setuUID(read.getAllStates().get(j).getuUID()+"-_Error");
					transition.setSourceState(read.getAllStates().get(j).getuUID());
					transition.setTargetState("_Error");
					transition.setKey(read.getAllTransitions().get(0).getKey());
					transition.setValue(read.getLinkValue2().get(i));
						for(int t=0;t<read.getAllTransitions().size();t++){
							if(read.getAllTransitions().get(t).getSourceState().equals(read.getAllStates().get(j).getuUID())){
								String value = read.getAllTransitions().get(t).getValue();
								
								float foo1 = Float.parseFloat(value);
								float foo2 = Float.parseFloat(read.getLinkValue2().get(i));
								
								String fo = Float.toString((1-foo2)*foo1);
								read.getAllTransitions().get(t).setValue(fo);
							}							
						}
					//transition.printAll();
					allTransitions.add(transition);
					state.addIncomingTransitions(read.getAllStates().get(j).getuUID()+"-_Error");
					read.getAllStates().get(j).addOutgoingTransitions(read.getAllStates().get(j).getuUID()+"-_Error");
				}
			}
				
		}
		for(int i=0;i<read.getAllStates().size();i++){
			if(read.getAllStates().get(i).getName().equals("Terminate")){
				Transitions transition = new Transitions();
				transition.setuUID("_Error"+"-"+read.getAllStates().get(i).getuUID());
				transition.setSourceState("_Error");
				transition.setTargetState(read.getAllStates().get(i).getuUID());
				transition.setKey(read.getAllTransitions().get(0).getKey());
				transition.setValue("1.000");
				allTransitions.add(transition);
				state.addOutgoingTransitions("_Error"+"-"+read.getAllStates().get(i).getuUID());
				read.getAllStates().get(i).addIncomingTransitions("_Error"+"-"+read.getAllStates().get(i).getuUID());
			}
		}
		
		allStates.add(state);
		read.setAllStates(allStates);
		//read.write();
	}
}
