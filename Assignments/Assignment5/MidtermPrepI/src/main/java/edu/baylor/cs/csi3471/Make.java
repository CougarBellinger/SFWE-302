package edu.baylor.cs.csi3471;

import java.util.Collection;
import java.util.Set;

public class Make {

	private Set<ModelSettings> modelSettingSet;

	public Make(Set<ModelSettings> modelSettingSet) {
		this.modelSettingSet = modelSettingSet;
	}

	public Set<ModelSettings> getModelSettingSet() {
		return modelSettingSet;
	}

	public void setModelSettingSet(Set<ModelSettings> modelSettingSet) {
		this.modelSettingSet = modelSettingSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modelSettingSet == null) ? 0 : modelSettingSet.hashCode());
		return result;
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
		if (modelSettingSet == null) {
			if (other.modelSettingSet != null)
				return false;
		} else if (!modelSettingSet.equals(other.modelSettingSet))
			return false;
		return true;
	}

	// there are 2 options, do this functionality here(its static),
	// or in your tester.java and call this method from the make object that a
	// line is. I would suggest number 2.
	public Collection<Make> creatorPattern(String[] line, Collection<Make> makes) {
		if (!modelSettingSet.contains(new ModelSettings(line))) {
			// if the make does not exist then create a new one
		} else {
			// if the make does exist, update its modelSettingSet
		}
		return makes;
	}

	@Override
	public String toString() {
		return "Make [modelSettingSet=" + modelSettingSet + ", hashCode()=" + hashCode() + "]";
	}

}
