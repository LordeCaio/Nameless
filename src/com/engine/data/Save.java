package com.engine.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

	public static String path; 
	
	public static String split0 = ": ";
	public static String split1 = "/";
	
	public static void saveFile(String[] val1, int[] val2, int encode) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("save.txt"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < val1.length; i++) {
			String current = val1[i];
			current+=split0;
			char[] value = Integer.toString(val2[i]).toCharArray();
			for(int ii = 0; ii < value.length; ii++) {
				value[ii]+=encode;
				current+=value[ii];
			}
			try {
				writer.write(current);
				if(i < val1.length - 1) {
					writer.newLine();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}				
		}
		try {
			writer.flush();
			writer.close();			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String loadFile(int encode) {
		String line = "";
		File file = new File("save.txt");
		if(file.exists()) {
			try {
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				try {
					while((singleLine = reader.readLine()) != null) {
						String[] trans = singleLine.split(split0);
						char[] val = trans[1].toCharArray();
						trans[1] = "";
						for(int i = 0; i < val.length; i++) {
							val[i]-=encode;
							trans[1]+=val[i];							
						}
						line+=trans[0];
						line+=split0;
						line+=trans[1];
						line+=split1;
					}
				}catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}		
		return line;
	}
	
	public static void applyValues(String str) {
		String[] spl = str.split(split1);
		for(int i = 0; i <spl.length; i++) {
			String[] spl2 = spl[i].split(split0);
			switch (spl2[0]) {
			case "Level":
				Data.CUR_LEVEL = Integer.parseInt(spl2[1]);
				Data.World.newWorld("/Maps/map0"+Data.CUR_LEVEL+".png");	
				Data.CUR_STATE = Data.GAME_STATE[2];
				break;

			}
		}
	}
	
	public static void createSave() {
		Data.saveGame = false;
		String[] opt0 = {"Level"};
		int[] opt1 = {Data.CUR_LEVEL};
		saveFile(opt0, opt1, 0);
	}
	
}
