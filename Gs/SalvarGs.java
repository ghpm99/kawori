package Gs;

import java.util.HashMap;

import entity.Gs;
import net.dv8tion.jda.api.entities.User;

public class SalvarGs {

	private static HashMap<String, Gs> listaGs = new HashMap<String, Gs>();

	private static SalvarGs instancia;

	public static SalvarGs getInstancia() {
		if (instancia == null) {
			instancia = new SalvarGs();
		}
		return instancia;
	}
	
	private SalvarGs() {
		
	}

	public void salvarGs(User user, Gs gs) {
		for (String a : listaGs.keySet()) {
			if (a.equals(user.getId())) {
				listaGs.replace(user.getId(), gs);
				return;
			}
		}
		listaGs.put(user.getId(), gs);
		atualizarArquivo();
		
	}

	public String listarGs() {
		String retorno = "";
		for (Gs a : listaGs.values()) {

			String temp =  "AP: " + a.getAp() + " ApAwak: " + a.getApAwak() + " Dp: " + a.getDp() + " Lvl: " + a.getLvl()
					+ " Gs medio: " + a.getGs() + "\n";			
			retorno = retorno + temp;
		}

		return retorno;
	}

	private void atualizarArquivo() {
		
	}
	
}
