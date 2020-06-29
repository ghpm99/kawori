package com.bot.KaworiSpring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Canal;
import com.bot.KaworiSpring.repository.CanalRepository;

import net.dv8tion.jda.api.entities.TextChannel;

@Service
public class CanalService {

	@Autowired
	private CanalRepository canalRepository;

	public Canal save(Canal canal) {
		return canalRepository.save(canal);
	}

	public Optional<Canal> findById(long id) {
		return canalRepository.findById(id);
	}

	public Canal createNew(TextChannel channel) {
		Canal canal = new Canal();

		canal.setId(channel.getIdLong());
		canal.setName(channel.getName());
		canal.setIdGuild(channel.getGuild().getIdLong());
		canal.setSendMessage(true);
		canal.setTipo(channel.getType().name());

		return save(canal);
	}

	public Canal UpdateCanal(TextChannel channel) {
		Optional<Canal> canal = findById(channel.getIdLong());
		Canal retorno;
		if (canal.isEmpty()) {
			retorno = createNew(channel);
		} else {
			retorno = canal.get();
			retorno.setName(channel.getName());
		}
		return save(retorno);
	}

}
