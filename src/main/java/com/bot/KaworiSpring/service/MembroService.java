package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.repository.MembroRepository;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

@Service
public class MembroService {

	private MembroRepository membroRepository;

	@Autowired
	public MembroService(MembroRepository membroRepository) {
		// TODO Auto-generated constructor stub
		this.membroRepository = membroRepository;
	}

	public Membro save(Membro membro) {
		membro.setNewRecord(false);
		return membroRepository.save(membro);
	}

	public List<Membro> findById(String id) {
		return membroRepository.findByIdUser(id);
	}

	public Membro findByIdAndIdGuild(String id, String idGuild) {
		return membroRepository.findByIdUserAndIdGuild(id, idGuild).orElseGet(() -> {
			Membro membro = new Membro();
			membro.setIdUser(id);
			membro.setIdGuild(idGuild);
			return membro;
		});
	}

	public Membro loadMembro(Guild guild, Member user) {
		Membro membro = findByIdAndIdGuild(user.getId(), guild.getId());

		if (membro.isNewRecord()) {
			save(membro);
		}

		return membro;
	}

	public List<Membro> findAll() {
		return membroRepository.findAll();
	}

	public Page<Membro> findAll(Pageable pageable) {
		return membroRepository.findAll(pageable);
	}

}
