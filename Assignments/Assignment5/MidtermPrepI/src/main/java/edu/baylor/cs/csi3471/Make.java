package edu.baylor.cs.csi3471;

import java.io.StringWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Make {

	private static int idCounter = 0;
	private int uniqueId;

	private Set<ModelSettings> modelSettingSet;
	private String makeName = null;

	public Make(Set<ModelSettings> modelSettingSet) {
		this.modelSettingSet = modelSettingSet;
		this.uniqueId = idCounter++;
	}
	
	public Make(ModelSettings model, String name) {
		this.modelSettingSet = new HashSet<>();

		model.setMake(this);
		this.modelSettingSet.add(model);

		this.makeName = name;

		this.uniqueId = idCounter++;
	}

	public String getMakeName() {
		return makeName;
	}

	public Set<ModelSettings> getModelSettingSet() {
		return modelSettingSet;
	}
	
	// may not need
	public void setModelSettingSet(Set<ModelSettings> modelSettingSet) {
		this.modelSettingSet = modelSettingSet;
	}

	// there are 2 options, do this functionality here(its static),
	// or in your tester.java and call this method from the make object that a
	// line is. I would suggest number 2.
	public static Set<Make> creatorPattern(String[] line, Set<Make> makes) {
		boolean makeDNE = true;
		Make match = null;
		ModelSettings model = new ModelSettings(line);

		for (Make searchMake : makes) { 
			if(searchMake.getMakeName().equals(line[6])){ 
				makeDNE = false;
				match = searchMake;
				break;
			}
		}

		if(makeDNE){
			makes.add(new Make(model, line[6]));
		}
		else{
			if (match.modelSettingSet.contains(model)) {
				match.modelSettingSet.remove(model);
			} 
			match.modelSettingSet.add(model);
		}

		return makes;
	}

	@Override
	public String toString() {
		String out = makeName + "\n";

		for(ModelSettings model : this.modelSettingSet){
			out += model + "\n";
		}

		return out;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Make other = (Make) obj;
		if (uniqueId != other.uniqueId)
			return false;
		if (modelSettingSet == null) {
			if (other.modelSettingSet != null)
				return false;
		} else if (!modelSettingSet.equals(other.modelSettingSet))
			return false;
		if (makeName == null) {
			if (other.makeName != null)
				return false;
		} else if (!makeName.equals(other.makeName))
			return false;
		return true;
	}

	

}
