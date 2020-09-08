package com.bot.KaworiSpring.discord.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.MembroService;
import com.bot.KaworiSpring.service.TagService;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

@Controller
public class MembroController {

	@Autowired
	MembroService membroService;
	@Autowired
	OperatorController operatorController;
	@Autowired
	TagService tagService;

	public void updateAllMembers(Guild guild) {
		
		guild.loadMembers((member) -> {
			System.out.println("update member:" + member.getIdLong() + " from guild:" + member.getGuild().getIdLong());
			updateMember(member);
		});	

	}

	private void updateMember(Member member) {
		Membro membro = membroService.findByIdAndIdGuild(member.getIdLong(), member.getGuild().getIdLong());
		if (membro == null) {
			membro = new Membro();
			membro.setBanned(false);

			membro.setIdGuild(member.getGuild().getIdLong());
			membro.setIdUser(member.getIdLong());
		}
		if (membro.getFamilyName() == null) {
			membro.setFamilyName(member.getUser().getName());
		}
		updateEditGear(member, membro);
		membro.setNick(member.getNickname());
		operatorController.updateOperator(member.getUser());
		membroService.save(membro);
	}

	private void updateEditGear(Member member, Membro membro) {
		membro.setGear(checkRoleGear(member.getRoles()));
	}

	private boolean checkRoleGear(List<Role> roles) {
		boolean retorno = false;
		for (Role role : roles) {
			Tag tag = tagService.findByIdGuildAndIdRole(role.getGuild().getIdLong(), role.getIdLong());
			retorno = retorno || tag.isCmdBuild();

		}
		return retorno;
	}

	public void updateCanGearMember(Member member) {
		Membro membro = membroService.findByIdAndIdGuild(member.getIdLong(), member.getGuild().getIdLong());
		updateEditGear(member, membro);
		membroService.save(membro);
	}
	
	
}
