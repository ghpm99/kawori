package com.bot.KaworiSpring.discord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.service.MembroService;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

@Controller
public class MembroController {

	@Autowired
	MembroService membroService;
	@Autowired
	OperatorController operatorController;

	public void updateAllMembers(Guild guild) {

		guild.getMembers().forEach((member) -> {
			System.out.println("update member:" + member.getIdLong() + " from guild:" + member.getGuild().getIdLong());
			updateMember(member);
		});

	}

	public void updateMember(Member member) {
		Membro membro = membroService.findByIdAndIdGuild(member.getIdLong(), member.getGuild().getIdLong());
		if (membro == null) {
			membro = new Membro();
			membro.setBanned(false);
			membro.setGear(false);
			membro.setIdGuild(member.getGuild().getIdLong());
			membro.setIdUser(member.getIdLong());
		}
		if (membro.getFamilyName() == null) {
			membro.setFamilyName(member.getUser().getName());
		}
		membro.setNick(member.getNickname());
		operatorController.updateOperator(member.getUser());
		membroService.save(membro);
	}

}
