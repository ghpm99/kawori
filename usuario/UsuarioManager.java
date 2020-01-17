package usuario;

import java.io.File;

import entity.Usuario;
import net.dv8tion.jda.api.entities.User;

public class UsuarioManager {

	public Usuario buscarUsuario(User usuario) {
		return null;

	}

	private Usuario carregarUsuario(File file) {
		Usuario user = new Usuario("Erro", "Erro", "Erro", "Erro", 0);

		return user;

	}

	private Usuario salvarUsuario(User user, File file) {
		Usuario usuario = null;

		return usuario;
	}

}
