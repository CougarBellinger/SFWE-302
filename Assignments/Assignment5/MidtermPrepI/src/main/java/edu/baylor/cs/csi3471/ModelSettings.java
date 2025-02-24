package edu.baylor.cs.csi3471;

public class ModelSettings {

	public static class MPG {
		private Integer[] mpg = new Integer[3];
		// City 	-> [0]
		// Combined -> [1]
		// Highway 	-> [2]

		public MPG(String[] line){
			mpg[0] = Integer.parseInt(line[0]);
			mpg[1] = Integer.parseInt(line[1]);
			mpg[2] = Integer.parseInt(line[2]); 
		}

		@Override
		public String toString() {
			return mpg[0] + "/" + mpg[1] + "/" + mpg[2];
		}

		public Integer[] getMpg() {
			return mpg;
		}

		public void setMpg(Integer[] mpg) {
			this.mpg = mpg;
		}
	}

	private static int idCounter = 0;
	private int uniqueId;

	private MPG mpg = null;
	private Make make = null;
	
	private Double displacement = null;
	private Integer cylinders, year = null;

	private String makeName = null;
	private String fuelType= null;
	private String modelName = null;
	private String transmission = null;
	private String vClass = null;

	public MPG getMpg() {
		return mpg;
	}

	public Integer getCombinedMPG(){
		return this.mpg.getMpg()[1];
	}

	public Integer getYear() {
		return this.year;
	}

	public String getVClass(){
		return this.vClass;
	}

	public void setMpg(MPG mpg) {
		this.mpg = mpg;
	}

	public String getModelName(){
		return this.modelName;
	}

	public Make getMake(){
		return this.make;
	}

	public void setMake(Make make){
		this.make = make;
	}

	// city08,comb08,cylinders,displ,fuelType,highway08,make,model,trany,VClass,year
	public ModelSettings(String[] line) {
		this.uniqueId = idCounter++;

		this.mpg = new MPG(new String[] { line[0], line[1], line[5] });

		this.fuelType = line[4];
		this.makeName = line[6];
		this.modelName = line[7];
		this.transmission = line[8];
		this.vClass = line[9];
		
		this.displacement = Double.parseDouble(line[3]);
		this.cylinders = Integer.parseInt(line[2]);
		this.year = Integer.parseInt(line[10]);
	}

	public ModelSettings(String[] line, Make make) {
		this.uniqueId = idCounter++;

		this.mpg = new MPG(new String[] { line[0], line[1], line[5] });
		this.make = make;

		this.fuelType = line[4];
		this.makeName = line[6];
		this.modelName = line[7];
		this.transmission = line[8];
		this.vClass = line[9];

		this.displacement = Double.parseDouble(line[3]);
		this.cylinders = Integer.parseInt(line[2]);
		this.year = Integer.parseInt(line[10]);
	}

	@Override
	public String toString() {
		return modelName + " " + year + " v" + cylinders + " " + displacement + " " + fuelType + " "
			+ makeName + " " + transmission + " " + vClass + " " + mpg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mpg == null) ? 0 : mpg.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((displacement == null) ? 0 : displacement.hashCode());
		result = prime * result + ((cylinders == null) ? 0 : cylinders.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		result = prime * result + ((makeName == null) ? 0 : makeName.hashCode());
		result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + ((transmission == null) ? 0 : transmission.hashCode());
		result = prime * result + ((vClass == null) ? 0 : vClass.hashCode());
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
		if (displacement == null) {
			if (other.displacement != null)
				return false;
		} else if (!displacement.equals(other.displacement))
			return false;
		if (cylinders == null) {
			if (other.cylinders != null)
				return false;
		} else if (!cylinders.equals(other.cylinders))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		if (makeName == null) {
			if (other.makeName != null)
				return false;
		} else if (!makeName.equals(other.makeName))
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
		return true;
	}

	
}
