package usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import net.dv8tion.jda.api.entities.User;

public class UsuarioManager {

	public Usuario buscarUsuario(User usuario) {
		File user = new File("");
		if (user.exists()) {
			return carregarUsuario(user);
		} else {
			return salvarUsuario(usuario, user);
		}

	}

	private Usuario carregarUsuario(File file) {
		Usuario user = new Usuario("Erro", "Erro", "Erro", "Erro", 0);
		try {
			Properties pro = new Properties();			
			pro.load(new FileInputStream(file));
			user.setNome(pro.getProperty("nome"));
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

	private Usuario salvarUsuario(User user, File file) {
		Usuario usuario;
		try {
			Properties pro = new Properties();
			file.createNewFile();
			pro.setProperty("nome", user.getName());
			pro.setProperty("familia", "");
			pro.setProperty("id", user.getId());
			pro.setProperty("tag", user.getAsTag());
			pro.setProperty("acesso", "1");
			pro.store(new FileOutputStream(file), new Date().toString());
			usuario = new Usuario(user.getName(), user.getAsTag(), user.getId(), "", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			usuario = new Usuario("Erro", "Erro", "Erro", "Erro", 0);
		}
		return usuario;
	}

}
