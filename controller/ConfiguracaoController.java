package controller;

import dao.ConfiguracaoDao;
import entity.Configuracao;

public class ConfiguracaoController {
	
	private ConfiguracaoDao configDao;
	
	public ConfiguracaoController() {
		configDao = new ConfiguracaoDao();
	}
	
	public Configuracao getByType(String type) {
		return configDao.getByType(type);
	}
	
}
