package edu.baylor.cs.csi3471;

public class ModelSettings {

	public static class MPG {
		// City 	-> [1]
		// Combined -> [2]
		// Highway 	-> [3]
		private Integer[] mpg = new Integer[3];

		public MPG(String[] line){
			//TODO
		}

		@Override
		public String toString() {
			return mpg[1] + "/" + mpg[2] + "/" + mpg[3];
		}
	}

	private static Integer idCounter = 0;
	private Integer uniqueId;

	private MPG mpg = null;
	private Make make = null;
	private String cylinders, displacement, fuelType, modelName, transmission, vClass, year = null;

	public MPG getMpg() {
		return mpg;
	}

	public void setMpg(MPG mpg) {
		this.mpg = mpg;
	}

	
	public ModelSettings(String[] line) {
		// line:
		// city08,comb08,cylinders,displ,fuelType,highway08,make,model,trany,VClass,year
		uniqueId = idCounter;
		++idCounter;

		this.mpg = new MPG(new String[] {line[0], line[1], line[5]});
		this.cylinders = line[2];
		this.displacement = line[3];
		this.fuelType = line[4];
		this.modelName = line[7];
		this.transmission = line[8];
		this.vClass = line[9];
		this.year = line[10];
		
		// this.make = line[6];  TODO
	}

	@Override
	public String toString() {
		return modelName + " " + year + " v" + cylinders + " " + displacement + " " + fuelType + " "
			+ make + " " + transmission + " " + vClass + " " + mpg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mpg == null) ? 0 : mpg.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((cylinders == null) ? 0 : cylinders.hashCode());
		result = prime * result + ((displacement == null) ? 0 : displacement.hashCode());
		result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + ((transmission == null) ? 0 : transmission.hashCode());
		result = prime * result + ((vClass == null) ? 0 : vClass.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		ModelSettings other = (ModelSettings) obj;
		if (mpg == null) {
			if (other.mpg != null)
				return false;
		} else if (!mpg.equals(other.mpg))
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (cylinders == null) {
			if (other.cylinders != null)
				return false;
		} else if (!cylinders.equals(other.cylinders))
			return false;
		if (displacement == null) {
			if (other.displacement != null)
				return false;
		} else if (!displacement.equals(other.displacement))
			return false;
		if (fuelType == null) {
			if (other.fuelType != null)
				return false;
		} else if (!fuelType.equals(other.fuelType))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (transmission == null) {
			if (other.transmission != null)
				return false;
		} else if (!transmission.equals(other.transmission))
			return false;
		if (vClass == null) {
			if (other.vClass != null)
				return false;
		} else if (!vClass.equals(other.vClass))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	


}
