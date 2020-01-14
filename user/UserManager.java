/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import com.sun.tools.javac.Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import kawori.Kawori;

/**
 *
 * @author ghpm9
 */
public class UserManager {
    public User buscarUsuario(User usuario) {
		File user = new File(Kawori.properties.getCaminhoUsers() + usuario.getId());
		if (user.exists()) {
			return carregarUsuario(user);
		} else {
			return salvarUsuario(usuario, user);
		}

	}

	private User carregarUsuario(File file) {
		User user = new User("Erro", "Erro", "Erro", "Erro", 0);
		try {
			Properties pro = new Properties();			
			pro.load(new FileInputStream(file));
			user.setName(pro.getProperty("nome"));
			user.setFamilia(pro.getProperty("familia"));
			user.setId(pro.getProperty("id"));
			user.setTag(pro.getProperty("tag"));
			user.setAcesso(Integer.valueOf(pro.getProperty("acesso")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;

	}

	private User salvarUsuario(User user, File file) {
		User usuario;
		try {
			Properties pro = new Properties();
			file.createNewFile();
			pro.setProperty("nome", user.getName());
			pro.setProperty("familia", "");
			pro.setProperty("id", user.getId());
			//pro.setProperty("tag", user.getAsTag());
			pro.setProperty("acesso", "1");
			pro.store(new FileOutputStream(file), new Date().toString());
			usuario = new User(user.getName(),"" , user.getId(), "", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			usuario = new User("Erro", "Erro", "Erro", "Erro", 0);
		}
		return usuario;
	}
}
