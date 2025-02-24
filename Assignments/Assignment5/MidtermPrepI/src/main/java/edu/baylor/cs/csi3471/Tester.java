package edu.baylor.cs.csi3471;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Tester {

	private static final int OPTION = 0;
	private static final int FILE_NAME = 1;

	private static int readOption(String[] args) {
		Integer option = null;

		if (args.length < 2) {
			System.err.println("USAGE: java Tester <1-4> <filename>");
			System.exit(1);
		} else {
			try {
				option = Integer.parseInt(args[OPTION]);
			} catch (NumberFormatException e) {
				System.err.println("call as java Tester <1-4> <filename>");
				System.exit(1);
			}
		}
		return option;
	}

	private static void executeOption(Set<Make> makes, int option, String[] args){
		switch (option) {
			case 1:
				Comparator<Make> compareMakeReversed = Comparator.comparing(Make::getMakeName).reversed();
				TreeSet<Make> sortedMakesReversed = new TreeSet<>(compareMakeReversed);
				sortedMakesReversed.addAll(makes);

				System.out.println("Total makes: " + makes.size());
				System.out.println("===============");

				for(Make make : sortedMakesReversed){
					System.out.println(make.getMakeName() + " has " + make.getModelSettingSet().size() + " models");
				}

				System.out.println("===============");

				for(Make make : sortedMakesReversed){
					System.out.println(make);
				}

				break;

			case 2:
				Comparator<Make> compareMake = Comparator.comparing(Make::getMakeName);
				Set<Make> filteredMakes = new TreeSet<>(compareMake);

				if (args[2].equalsIgnoreCase("makename")){
					for(Make make : makes){
						if (make.getMakeName().contains(args[3])){
							filteredMakes.add(make);
						}
					}
					
					System.out.println("Make names containing \"" + args[3] + "\" sorted alphebetically:");
					
					for(Make make : filteredMakes){
						System.out.println(make.getMakeName());
					}
					
				}
				else if(args[2].equalsIgnoreCase("modelname")){
					for(Make make : makes) {
						for(ModelSettings model : make.getModelSettingSet()){
							if (model.getModelName().contains(args[3])){
								filteredMakes.add(make);
							}
						}
					}

					System.out.println("Makes with model names containing \"" + args[3] + "\" sorted alphebetically:");

					for (Make make : filteredMakes) {
						System.out.println(make.getMakeName());
					}

				}
				else{
					System.out.println("Unable to read, please type \"makename\" or \"modelname\"");
				}
				break;

			case 3:
				Set<ModelSettings> masterModelSettings = new HashSet<ModelSettings>();

				for(Make make : makes){
					masterModelSettings.addAll(make.getModelSettingSet());
				}

				// create mapping of a string to a set of models
				Map<String, Set<ModelSettings>> categorizedModelSettings = new HashMap<>();

				for(ModelSettings model : masterModelSettings){
					String vClass = model.getVClass();

					// if vClass doesn't exist in map, create new entry
					if(!categorizedModelSettings.containsKey(vClass)){
						categorizedModelSettings.put(vClass, new HashSet<>());
					}

					// add model to its relative vClass entry in the map
					categorizedModelSettings.get(vClass).add(model);
				}

				System.out.println("vClass -> Average Combined MPG:");
				for(Map.Entry<String, Set<ModelSettings>> entry : categorizedModelSettings.entrySet()){
					String vClass = entry.getKey();
					Set<ModelSettings> models = entry.getValue();
					Integer total = models.size();
					Double combinedMPGSum = 0.0;
					Double averageMPG;

					for (ModelSettings model : models) {
						combinedMPGSum += model.getCombinedMPG();	
					}

					averageMPG = combinedMPGSum / total;

					System.out.printf("%s: Avg of %.2f Combined MPG\n", vClass, averageMPG);
				}

				break;

			case 4: //TODO

				Comparator<Make> compareMake1 = Comparator.comparing(Make::getMakeName);
				Set<Make> filteredMakes1 = new TreeSet<>(compareMake1);
				filteredMakes1.addAll(makes);

				//Set<ModelSettings> masterModelSettings1 = new HashSet<ModelSettings>();

				for (Make make : filteredMakes1){
					// create mapping of a int to a set of models
					Map<Integer, Set<ModelSettings>> modelSettingsByYear = new HashMap<>();

					// same as option 3 but for year instead of vClass
					for (ModelSettings model : make.getModelSettingSet()) {
						Integer year = model.getYear();

						if(year == null){
							continue;
						}

						// if year doesn't exist in map, create new entry
						if (!modelSettingsByYear.containsKey(year)) {
							modelSettingsByYear.put(year, new HashSet<>());
						}

						// add model to its relative vClass entry in the map
						modelSettingsByYear.get(year).add(model);
					}

					for (Map.Entry<Integer, Set<ModelSettings>> entry : modelSettingsByYear.entrySet()) {
						Set<ModelSettings> models = entry.getValue();
						Integer year = entry.getKey();

						System.out.println("<" + make.getMakeName() + ";" + year + ";" + models.size() + ">");
					}

				}
				
				
				break;
		
			default:
				break;
		}
	}

	/*
	 * public static Collection<Make> populateSet(Collection<Make> set, String[]
	 * line){ //check the colleciton for existing make }
	 */


	private static Set<Make> loadCSV(String file) throws FileNotFoundException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(file)));

			String line = null;
			Set<Make> makes = new HashSet<>();

			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");

				if(split.length != 11){
					continue;
				}

				//System.out.println(split[6] + " : " + split[7]);

				try{
					makes = Make.creatorPattern(split, makes);
				} catch (NumberFormatException nfe){
					continue;
				}
			}

			return makes;

		} catch (IOException e) {
			String hint = "";
			try {
				hint = "Current dir is: " + new File(".").getCanonicalPath();
			} catch (Exception local) {
				hint = local.getLocalizedMessage();
			}
			throw new FileNotFoundException(e.getLocalizedMessage() + "\n" + hint);

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println(e.getLocalizedMessage());
				}
			}
		}

	}

	public static void main(String[] args) {
		String file = "src/main/resources/vehiclesMini.csv";
		String[] testArgs = {"4", file}; //used for debugging

		int option = readOption(args);

		//int option = readOption(args);

		// String file = args[FILE_NAME];

		Set<Make> makes = null;
		try {
			makes = loadCSV(testArgs[FILE_NAME]);

		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

		executeOption(makes, option, args);
		
	}
}
