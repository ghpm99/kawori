package com.bot.KaworiSpring.discord.nodewar;

import java.util.Calendar;

public enum Nodes {

	POSTOSENTINELA("T4", "Posto Sentinela de Epheria", "Calpheon 1", 100, 10, Calendar.MONDAY),
	VALEEPHERIA("T2", "Vale de Epheria", "Calpheon 1", 60, 3, Calendar.TUESDAY),
	COLINAQUINT("T3", "Colina de Quint", "Calpheon 1", 100, 5, Calendar.FRIDAY),
	FORTIFICACAOANTITROLL("T3", "Fortificacao Anti-Troll", "Calpheon 1", 55, 5, Calendar.SUNDAY),
	TERRAABANDONADA("T1A", "Terra Abandonada", "Calpheon 1", 40, 0, Calendar.WEDNESDAY),
	FAZENDACONTAMINADA("T1A", "Fazenda Contaminada", "Calpheon 1", 55, 0, Calendar.THURSDAY),
	BALENOS("S", "Siege Balenos", "Balenos 1", 100, 100, Calendar.SATURDAY),
	SERENDIA("S", "Siege Serendia", "Serendia 1", 100, 100, Calendar.SATURDAY),
	CALPHEON("S", "Siege Calpheon", "Calpheon 1", 100, 100, Calendar.SATURDAY),
	MEDIAH("S", "Siege Mediah", "Mediah 1", 100, 100, Calendar.SATURDAY),
	VALENCIA("S", "Siege Valencia", "Valencia 1", 100, 100, Calendar.SATURDAY),
	PLANTACAOVALENCIA("T2", "Plantacao de Valencia", "Valencia 1", 30, 3, Calendar.MONDAY),
	FAZENDAERDAL("T2", "Fazenda Erdal", "Valencia 1", 30, 3, Calendar.WEDNESDAY),
	FAZENDAFOHALAM("T1M", "Fazenda Fohalam", "Valencia 1", 40, 0, Calendar.THURSDAY),
	TERRASAGRICOLASALTAS("T1I", "Terras Agricolas de Altas", "Valencia 1", 25, 0, Calendar.WEDNESDAY),
	TOPOMOTANHANORTEKAIA("T3", "Topo da Motanha Norte de Kaia", "Calpheon 1", 55, 5, Calendar.TUESDAY),
	ACAMPGUARDAOESTE("T4", "Acamp. de Guarda do Oeste", "Balenos 1", 100, 10, Calendar.FRIDAY),
	CIDADEARRUINADARUNA("T1M", "Cidade Arruinada de Runa", "Valencia 1", 25, 0, Calendar.FRIDAY),
	LITORALANCADA("T3", "Litoral de Ancado", "Valencia 1", 40, 4, Calendar.FRIDAY),
	PENHASCOIVERO("T1M", "Penhasco Ivero", "Valencia 1", 55, 0, Calendar.WEDNESDAY),
	TERRADEVASTADA("T1M", "Terra Devastada de Marfim", "Valencia 1", 40, 0, Calendar.MONDAY),
	CRATERAGRANDE("T2", "Cratera Grande de Gavinya", "Valencia 1", 45, 3, Calendar.FRIDAY),
	PENHASCOCOSTEIRO("T2", "Penhasco Costeiro de Gavinya", "Valencia 1", 60, 3, Calendar.TUESDAY),
	ZONAVULCANICA("T2", "Zona Vulcanica de Gavianya", "Valencia 1", 45, 3, Calendar.TUESDAY),
	OFICINAMINA("T4", "Oficina da Mina de Enxofre", "Valencia 1", 100, 10, Calendar.SUNDAY),
	ZONACASTELOVALENCIA("T3", "Zona do Castelo de Valencia", "Valencia 1", 100, 5, Calendar.FRIDAY),
	OBSERVATORIORAKSHAN("T1M", "Observatorio de Rakshan", "Valencia 1", 25, 0, Calendar.THURSDAY),
	SANTUARIOJEJUM("T1A", "Santuario do Peregrino - Jejum", "Valencia 1", 55, 0, Calendar.SUNDAY),
	SANTUARIOOBEDIENCIA("T1M", "Santuario do Peregrino - Obediencia", "Valencia 1", 25, 0, Calendar.TUESDAY),
	SANTUARIOHUMILDADE("T2", "Santuario do Peregrino - Humildade", "Valencia 1", 45, 3, Calendar.MONDAY),
	SANTUARIOLUACRESCENTE("T2", "Santuario Lua Crescente", "Valencia 1", 60, 3, Calendar.FRIDAY),
	MONTANHASLUACRESCENTE("T2", "Montanhas Lua Crescente", "Valencia 1", 45, 3, Calendar.WEDNESDAY),
	AAKMAN("T3", "Aakman", "Valencia 1", 100, 5, Calendar.TUESDAY),
	SANTUARIOHONESTIDADE("T1M", "Santuario do Peregrino - Honestidade", "Valencia 1", 40, 0, Calendar.SUNDAY),
	OASISIBELLAB("T3", "Oasis Ibellab", "Valencia 1", 55, 5, Calendar.SUNDAY),
	SANTUARIOABSTINECIA("T1M", "Santuario do Peregrino - Abstinencia", "Valencia 1", 55, 0, Calendar.MONDAY),
	ABRIGOPEREGRINO("T3", "Abrigo do Peregrino", "Valencia 1", 30, 3, Calendar.WEDNESDAY),
	TEMPLONAGADESERTO("T2", "Templo Naga do Deserto", "Valencia 1", 30, 3, Calendar.FRIDAY),
	CAPOTIA("T2", "Capotia", "Valencia 1", 60, 3, Calendar.MONDAY),
	PLANALTOOESTEVALENCIA("T1M", "Planalto Oeste de Valencia", "Valencia 1", 25, 0, Calendar.THURSDAY),
	NINHOWARAGON("T4", "Ninho de Waragon", "Valencia 1", 100, 10, Calendar.WEDNESDAY),
	ACAMPAMENTOBASHIM("T1A", "Acampamento de Bashim", "Valencia 1", 55, 0, Calendar.FRIDAY),
	RAVINAPUJIYA("T1M", "Ravina Pujiya", "Valencia 1", 40, 0, Calendar.TUESDAY),
	PORTALBARHAN("T2", "Portal de Barhan", "Valencia 1", 30, 3, Calendar.THURSDAY),
	PLANICETAPHTAR("T4", "Planicie Taphtar", "Valencia 1", 100, 10, Calendar.TUESDAY),
	RUINASCADRY("T4", "Ruinas Cadry", "Valencia 1", 100, 10, Calendar.THURSDAY),
	POSTOSENTINELAROCHA("T3", "Posto de Sentinela da Rocha", "Valencia 1", 55, 5, Calendar.MONDAY),
	RAVINAVETERANO("T1A", "Ravina do Veterano", "Valencia 1", 40, 0, Calendar.WEDNESDAY),
	REGIAOROCHOSA("T2", "Regiao Rochosa Gorgo", "Valencia 1", 45, 3, Calendar.SUNDAY),
	PORTALALTINOVA("T2", "Portal de Altinova", "Valencia 1", 60, 3, Calendar.SUNDAY),
	SEGUNDOLABORATORIOMARNI("T1M", "2º Laboratorio de Marni", "Mediah 1", 25, 0, Calendar.MONDAY),
	ALDEIATAMBORIL("T3", "Aldeia Tamboril", "Mediah 1", 55, 5, Calendar.FRIDAY),
	VALEROCHOSOALUMN("T2", "Vale Rochoso Alumn", "Mediah 1", 45, 3, Calendar.TUESDAY),
	MINAFERRO("T4", "Mina de Ferro Abandonada", "Mediah 1", 30, 3, Calendar.SUNDAY),
	LITORALBICOPEDRA("T2", "Litoral do Bico de Pedra", "Mediah 1", 60, 3, Calendar.THURSDAY),
	SINODESPERTADOR("T3", "Sino Despertador", "Mediah 1", 55, 5, Calendar.WEDNESDAY),
	ACAMPAMENTOBANDIDOS("T2", "Acampamento dos Bandidos Viajantes", "Mediah 1", 45, 3, Calendar.THURSDAY),
	PLANALTOASULA("T2", "Planalto Asula", "Mediah 1", 45, 3, Calendar.MONDAY),
	CAVERNALAVA("T3", "Caverna de Lava do Omar", "Mediah 1", 100, 5, Calendar.SUNDAY),
	KUSHA("T2", "Kusah", "Mediah 1", 30, 3, Calendar.MONDAY),
	POSTOAVANCADOSARMA("T3", "Posto Avancado de Sarma", "Mediah 1", 55, 5, Calendar.TUESDAY),
	DESERTOCAUDA("T1M", "Deserto Cauda de Pedra", "Mediah 1", 40, 0, Calendar.THURSDAY),
	RUINASCASTELO("T2", "Ruinas do Castelo", "Serendia 1", 30, 3, Calendar.WEDNESDAY),
	PENHASCOHASRAH("T2", "Penhasco de Hasrah", "Mediah 1", 30, 3, Calendar.FRIDAY),
	ESCONDERIJOMOICAN("T1M", "Esconderijo dos Moicans", "Mediah 1", 25, 0, Calendar.FRIDAY),
	FAZENDAKASULA("T1M", "Fazenda Kasula", "Mediah 1", 55, 0, Calendar.WEDNESDAY),
	FLORESTATUNGRAD("T1A", "Floresta Tungrad", "Mediah 1", 40, 0, Calendar.SUNDAY),
	SEPULTURASOLDADOS("T4", "Sepultura de Soldados", "Mediah 1", 100, 10, Calendar.MONDAY),
	TEMPLOKAMASYLVIA("T4", "Templo Kamasylvia", "Mediah 1", 100, 10, Calendar.FRIDAY),
	LOCALESCAVACAO("T1A", "Local de Escavacao das Ruinas Antigas", "Mediah 1", 55, 0, Calendar.MONDAY),
	FAZENDAAHTO("T1M", "Fazenda Ahto", "Mediah 1", 25, 0, Calendar.TUESDAY),
	RANCHOCAVALOPEDRA("T2", "Rancho de Cavalo de Cauda de Pedra", "Mediah 1", 30, 3, Calendar.SUNDAY),
	ACAMPAMENTOSAUSAN("T2", "Acampamento de Sausan", "Mediah 1", 60, 3, Calendar.FRIDAY),
	SANTUARIOELRIC("T3", "Santuario Elric", "Mediah 1", 100, 5, Calendar.THURSDAY),
	RAVINACORRUPCAO("T2", "Ravina da Corrupcao", "Mediah 1", 60, 3, Calendar.TUESDAY),
	PLANALTONORTEMEDIAH("T1I", "Planalto Norte de Mediah", "Mediah 1", 55, 0, Calendar.SUNDAY),
	ACAMPAMENTOELMOS("T1M", "Acampamento dos Elmos", "Mediah 1", 40, 0, Calendar.WEDNESDAY),
	PORTALNORTEMEDIAH("T2", "Portal Norte de Mediah", "Mediah 1", 60, 3, Calendar.WEDNESDAY),
	FRONTEIRALESTE("T3", "Fronteira do Leste", "Serendia 1", 55, 5, Calendar.FRIDAY),
	PORTALLESTE("T2", "Portal do Leste", "Serendia 1", 60, 3, Calendar.SUNDAY),
	PLANTACAOMORETTI("T3", "Plantacao Moretti", "Serendia 1", 100, 5, Calendar.MONDAY),
	MONASTERIOSANGRENTO("T1M", "Monasterio Sangrento", "Serendia 1", 40, 0, Calendar.FRIDAY),
	SANTUARIOSERENDIA("T3", "Santuario de Serendia", "Serendia 1", 30, 3, Calendar.THURSDAY),
	ACAMPAMENTOGUARDASUL("T4", "Acampamento de Guarda do Sul", "Serendia 1", 100, 10, Calendar.TUESDAY),
	PANTANOSUL("T1M", "Pantano do Sul", "Serendia 1", 40, 0, Calendar.THURSDAY),
	RUINASGLISH("T4", "Ruinas de Glish", "Serendia 1", 30, 3, Calendar.FRIDAY),
	ACAMPAMENTOGUARDACENTRAL("T1I", "Acampamento de Guarda Central", "Serendia 1", 55, 0, Calendar.TUESDAY),
	FAZENDACOSTA("T2", "Fazenda Costa", "Serendia 1", 30, 3, Calendar.MONDAY),
	PANTANOGLISH("T2", "Pantano de Glish", "Serendia 1", 45, 3, Calendar.WEDNESDAY),
	PORTALNOROESTE("T3", "Portal do Noroeste", "Serendia 1", 40, 4, Calendar.SUNDAY),
	PLANICENORTESERENDIA("T4", "Planicie Norte de Serendia", "Serendia 1", 100, 10, Calendar.THURSDAY),
	ESCONDERIJOBIRAGHI("T4", "Esconderijo Biraghi", "Serendia 1", 100, 10, Calendar.SUNDAY),
	CAVERNACOSTEIRA("T1M", "Caverna Costeira", "Balenos 1", 55, 0, Calendar.SUNDAY),
	CASTELOCRON("T3", "Castelo Cron", "Balenos 1", 100, 5, Calendar.SUNDAY),
	ZONACASTELOCRON("T1I", "Zona do Castelo Cron", "Balenos 1", 40, 0, Calendar.MONDAY),
	COLINAEHWAZ("T1A", "Colina Ehwaz", "Balenos 1", 55, 0, Calendar.FRIDAY),
	CAVERNAGLOBINS("T3", "Caverna de Globins", "Balenos 1", 100, 5, Calendar.TUESDAY),
	PASSAGEMHEIDEL("T2", "Passagem de Heidel", "Balenos 1", 45, 3, Calendar.THURSDAY),
	FLORESTAPILHAGEM("T4", "Floresta de Pilhagem", "Balenos 1", 100, 10, Calendar.WEDNESDAY),
	FAZENDAALEJANDRO("T1M", "Fazenda Alejandro", "Serendia 1", 25, 0, Calendar.SUNDAY),
	CAVERNADIABRETES("T2", "Caverna dos Diabretes", "Balenos 1", 30, 3, Calendar.TUESDAY),
	ACAMPGUARDANORTE("T1M", "Acamp de Guarda do Norte", "Serendia 1", 40, 0, Calendar.WEDNESDAY),
	FAZENDATOSCANI("T1A", "Fazenda Toscani", "Balenos 1", 25, 0, Calendar.WEDNESDAY),
	PEDREIRANORTEHEIDEL("T3", "Pedreira Norte do Heidel", "Serendia 1", 55, 5, Calendar.MONDAY),
	CAMARAPEDRA("T1A", "Camara de Pedra dos Anciaos", "Balenos 1", 40, 0, Calendar.THURSDAY),
	RANCHOLYNCH("T2", "Rancho Lynch", "Serendia 1", 45, 3, Calendar.TUESDAY),
	ALTARAGRIS("T2", "Altar do Agris", "Balenos 1", 60, 3, Calendar.MONDAY),
	PORTALOESTE("T3", "Portal do Oeste", "Balenos 1", 55, 5, Calendar.WEDNESDAY),
	PORTALSUDOESTE("T1M", "Portal do Sudoeste", "Serendia 1", 55, 0, Calendar.WEDNESDAY),
	TORREVIGIA("T1A", "Torre de Vigia", "Serendia 1", 55, 0, Calendar.FRIDAY),
	FORTALEZABRADY("T1A", "Fortaleza Brady", "Serendia 1", 25, 0, Calendar.TUESDAY),
	ACAMPAMENTOORCS("T2", "Acampamento dos Orcs", "Serendia 1", 60, 3, Calendar.THURSDAY),
	ZONANEUTRASUL("T1A", "Zona Neutra do Sul", "Serendia 1", 25, 0, Calendar.MONDAY),
	PASSAGEMOZE("T1M", "Passagem de Oze", "Calpheon 1", 55, 0, Calendar.TUESDAY),
	CASAOZE("T1I", "Casa do Oze", "Calpheon 1", 25, 0, Calendar.THURSDAY),
	PEDREIRAABANDONADA("T1A", "Pedreira Abandonada", "Calpheon 1", 25, 0, Calendar.SUNDAY),
	ARREDORESKEPLAN("T1M", "Arredores de Keplan", "Calpheon 1", 40, 0, Calendar.MONDAY),
	COLINAKEPLAN("T2", "Colina de Keplan", "Calpheon 1", 45, 3, Calendar.FRIDAY),
	ACAMPAMENTOGIGANTES("T2", "Acampamento dos Gigantes", "Calpehon 1", 60, 3, Calendar.FRIDAY),
	PEDREIRARUINAS("T2", "Pedreira em Ruinas", "Calpheon 1", 30, 3, Calendar.WEDNESDAY),
	LABORATORIOMARNI("T1M", "Laboratorio de Marni", "Calpheon 1", 25, 0, Calendar.TUESDAY),
	BALIZAFOGO("T2", "Baliza de Fogo da Trina", "Calpheon 1", 30, 3, Calendar.SUNDAY),
	POSTOENTRADABALIZA("T1M", "Posto de Entrada da Baliza do Fogo", "Calpheon 1", 25, 0, Calendar.WEDNESDAY),
	FORTETRINA("T3", "Forte da Trina", "Calpheon 1", 100, 5, Calendar.MONDAY),
	ACAMPAMENTOSAUNIL("T2", "Acampamento de Saunil", "Calpheon 1", 30, 3, Calendar.THURSDAY),
	PAREDEHEXE("T2", "Parede de Pedra de Hexe", "Calpheon 1", 60, 3, Calendar.WEDNESDAY),
	CAVERNAMARIE("T2", "Caverna Marie", "Calpehon 1", 45, 3, Calendar.THURSDAY),
	CAPELABRUXA("T4", "Capela da Bruxa", "Calpheon 1", 30, 3, Calendar.MONDAY),
	SANTUARIOHEXE("T3", "Santuario de Hexe", "Calpheon 1", 30, 3, Calendar.SUNDAY),
	TOCOAARVORERHUA("T1M", "Toco da Arvore Rhua", "Calpheon 1", 25, 0, Calendar.FRIDAY),
	JUSANTERIOBEHR("T1M", "Jusante do Rio Behr", "Calpheon 1", 40, 0, Calendar.TUESDAY),
	FLORESTAARVOREFOLHALONGA("T1A", "Floresta de Arvore de Folha Longa", "Calpheon 1", 55, 0, Calendar.WEDNESDAY),
	CRIO("T2", "Crio", "Calpehon1", 45, 3, Calendar.SUNDAY),
	POSTOSENTINELAARVORE("T3", "Posto de Sentinela da Arvore Folha Longa", "Calpheon 1", 40, 4, Calendar.THURSDAY),
	ORIGEMRIOBEHR("T1M", "Origem do Rio Behr", "Calpheon 1", 55, 0, Calendar.MONDAY),
	CABINEPHONIEL("T3", "Cabine do Phoniel", "Calpheon 1", 40, 4, Calendar.TUESDAY),
	ACAMPAMENTORHUTUMS("T2", "Acampamento dos Rhutums", "Calpheon 1", 45, 3, Calendar.MONDAY),
	FLORESTATREANT("T1I", "Floresta de Treant", "Calpheon 1", 40, 0, Calendar.FRIDAY),
	CABINETOBARE("T1M", "Cabine do Tobare", "Calpheon 1", 40, 0, Calendar.THURSDAY),
	FLORESTAMANSHA("T4", "Floresta Mansha", "Calpheon 1", 100, 10, Calendar.FRIDAY),
	LAGOKAIA("T2", "Lago de Kaia", "Calpheon 1", 30, 3, Calendar.WEDNESDAY),
	ACAMPAMENTOHOMENSBAGRE("T2", "Acampamento dos Homens-Bagre", "Calpheon 1", 60, 3, Calendar.SUNDAY),
	CASTELOCAVALEIROSDELPHE("T2", "Castelo dos Cavaleiros Delphe", "Calpheon 1", 30, 3, Calendar.TUESDAY),
	CAVERNAKHURUTO("T1A", "Caverna de Khuruto", "Calpheon 1", 25, 0, Calendar.MONDAY),
	POSTOAVANCADODELPHE("T1M", "Posto Avancado de Delphe", "Calpheon 1", 40, 0, Calendar.SUNDAY),
	CUMEKARANDA("T3", "Cume Karanda", "Calpheon 1", 55, 5, Calendar.THURSDAY),
	FLORESTACORUJA("T4", "Floresta da Coruja Mascara", "Balenos 1", 100, 10, Calendar.TUESDAY),
	FOZRIOBALENOS("T1M", "Foz do Rio Balenos", "Balenos 1", 40, 0, Calendar.TUESDAY),
	COLINALOBOS("T1M", "Colina dos Lobos", "Balenos 1", 25, 0, Calendar.FRIDAY),
	LITORALOLVIA("T3", "Litoral de Olvia", "Balenos 1", 40, 4, Calendar.MONDAY),
	FAZENDACASTA("T4", "Fazenda Casta", "Balenos 1", 100, 10, Calendar.SUNDAY),
	FAZENDAWALE("T1M", "Fazenda Wale", "Balenos 1", 55, 0, Calendar.THURSDAY),
	PENHASCOTHERMIAN("T2", "Penhasco Thermian", "Balenos 1", 45, 3, Calendar.SUNDAY),
	PORTALFLORIN("T3", "Portal de Florin", "Balenos 1", 55, 5, Calendar.THURSDAY),
	SOPEMONTANHATHERMIAN("T2", "Sope da Montanha Thermian", "Balenos 1", 30, 3, Calendar.FRIDAY),
	PONTEIDOSO("T1M", "Ponte do Idoso", "Balenos 1", 25, 0, Calendar.MONDAY),
	RUINASARVOREBREE("T1M", "Ruinas da Arvore Bree", "Calpheon 1", 55, 0, Calendar.FRIDAY),
	PLANTACAOTRIGONORTE("T3", "Plantacao de Trigo do Norte", "Calpheon 1", 100, 5, Calendar.WEDNESDAY),
	CUMEEPHERIA("T2", "Cume de Epheria", "Balenos 1", 60, 3, Calendar.WEDNESDAY);

	private final String tier;
	private final String name;
	private final String channel;
	private final int limitPlayer;
	private final int limitHeroi;
	private final int dayOfWeek;

	private Nodes(String tier, String name, String channel, int limitPlayer, int limitHeroi, int dayOfWeek) {
		this.tier = tier;
		this.name = name;
		this.channel = channel;
		this.limitPlayer = limitPlayer;
		this.limitHeroi = limitHeroi;
		this.dayOfWeek = dayOfWeek;
	}

	public String getTier() {
		return tier;
	}

	public String getName() {
		return name;
	}

	public String getChannel() {
		return channel;
	}

	public int getLimitPlayer() {
		return limitPlayer;
	}

	public int getLimitHeroi() {
		return limitHeroi;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

}
