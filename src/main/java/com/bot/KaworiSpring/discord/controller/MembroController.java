package com.bot.KaworiSpring.discord.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.LogService;
import com.bot.KaworiSpring.service.MembroService;
import com.bot.KaworiSpring.service.TagService;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

// TODO: Auto-generated Javadoc
/**
 * The Class MembroController.
 */
@Controller
public class MembroController {

	/** The membro service. */
	@Autowired
	MembroService membroService;
	
	/** The operator controller. */
	@Autowired
	OperatorController operatorController;
	
	/** The tag service. */
	@Autowired
	TagService tagService;
	
	/** The log service. */
	@Autowired
	LogService logService;

	/**
	 * Update all members.
	 *
	 * @param guild the guild
	 */
	public void updateAllMembers(Guild guild) {	
		
		guild.loadMembers((member) -> {			
			logService.addEvent(new Log(new Date(), "Update member", member.getGuild().getId(), member.getId(), ""));
			updateMember(member);
		});	

	}

	/**
	 * Update member.
	 *
	 * @param member the member
	 */
	private void updateMember(Member member) {
		Membro membro = membroService.findByIdAndIdGuild(member.getId(), member.getGuild().getId());
		if (membro.isNewRecord()) {
			membro.setBanned(false);

			membro.setIdGuild(member.getGuild().getId());
			membro.setIdUser(member.getId());			
		}
		if (membro.getFamilyName() == null) {
			membro.setFamilyName(member.getUser().getName());
		}
		updateEditGear(member, membro);
		membro.setNick(member.getNickname());
		operatorController.updateOperator(member.getUser());
		membroService.save(membro);
	}

	/**
	 * Update edit gear.
	 *
	 * @param member the member
	 * @param membro the membro
	 */
	private void updateEditGear(Member member, Membro membro) {
		membro.setGear(checkRoleGear(member.getRoles()));
	}

	/**
	 * Check role gear.
	 *
	 * @param roles the roles
	 * @return true, if successful
	 */
	private boolean checkRoleGear(List<Role> roles) {
		boolean retorno = false;
		for (Role role : roles) {
			Tag tag = tagService.findByIdGuildAndIdRole(role.getGuild().getId(), role.getId());
			retorno = retorno || tag.isCmdBuild();

		}
		return retorno;
	}

	/**
	 * Update can gear member.
	 *
	 * @param member the member
	 */
	public void updateCanGearMember(Member member) {
		Membro membro = membroService.findByIdAndIdGuild(member.getId(), member.getGuild().getId());
		updateEditGear(member, membro);
		membroService.save(membro);
	}
	
	
}
