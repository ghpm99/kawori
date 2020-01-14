/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author ghpm9
 */
public class PropertiesBot {
    
    
    private Properties prop;
    
	private File file = new File("config");

	public void carregar() {
		if (carregarProperties()) {
			System.out.println("Configurações carregadas.");
		}

	}

	private boolean carregarProperties() {
		try {
			prop = new Properties();
			FileInputStream fileInput = new FileInputStream(file);
			prop.load(fileInput);
			return true;
		} catch (IOException e) {
			if (!file.exists()) {
				try {
					file.createNewFile();
					gerarProperties();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return false;
	}

	private boolean salvarProperties() {
		try {
			prop.store(new FileOutputStream(file), new Date().toString());
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void gerarProperties() {
		if (prop == null) {
			prop = new Properties();
		}
		prop.setProperty("users", "D:\\bot discord\\usuarios\\");
		salvarProperties();
	}

	public String getCaminhoUsers() {
		
		return prop.getProperty("users");
	}
}
