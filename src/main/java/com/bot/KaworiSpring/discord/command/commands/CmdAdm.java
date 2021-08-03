package com.bot.KaworiSpring.discord.command.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.AdventureFame;
import com.bot.KaworiSpring.model.Canal;
import com.bot.KaworiSpring.model.ColorBD;
import com.bot.KaworiSpring.model.GifBD;
import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.AdventureFameService;
import com.bot.KaworiSpring.service.CanalService;
import com.bot.KaworiSpring.service.ColorBDService;
import com.bot.KaworiSpring.service.GifBDService;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.NodeService;
import com.bot.KaworiSpring.service.TagService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class CmdAdm.
 */
@Component
public class CmdAdm extends Command {

	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;
	
	/** The canal service. */
	@Autowired
	private CanalService canalService;
	
	/** The tag service. */
	@Autowired
	private TagService tagService;
	
	/** The message controller. */
	@Autowired
	private MessageController messageController;
	
	/** The adventure fame service. */
	@Autowired
	private AdventureFameService adventureFameService;
	
	/** The color BD service. */
	@Autowired
	private ColorBDService colorBDService;
	
	/** The gif BD service. */
	@Autowired
	private GifBDService gifBDService;
	
	/** The node service. */
	@Autowired
	private NodeService nodeService;

	/**
	 * Action.
	 *
	 * @param args the args
	 * @param event the event
	 */
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		for (String arg : args) {
			checkCmd(arg, event);
		}
		if (!event.getMessage().getMentionedRoles().isEmpty()) {
			debugRole(event);
		}

	}

	/**
	 * Executed.
	 *
	 * @param success the success
	 * @param event the event
	 */
	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * Help.
	 *
	 * @return the string
	 */
	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "msg_adm_help";
	}

	/**
	 * Help short.
	 *
	 * @return the string
	 */
	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_adm_helpshort";
	}

	/**
	 * Gets the permissions.
	 *
	 * @return the permissions
	 */
	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_DEV;
	}

	/**
	 * Check cmd.
	 *
	 * @param arg the command
	 * @param event the event
	 */
	private void checkCmd(String arg, MessageReceivedEvent event) {
		switch (arg) {
		case "guild":
			debugGuild(event);
			break;
		case "channel":
			debugChannel(event);
			break;
		case "bot":
			debugBot(event);
			break;
		case "db":
			dbUpdate(event);
			break;
		default:

			break;
		}
	}

	/**
	 * Debug guild.
	 *
	 * @param event the event
	 */
	private void debugGuild(MessageReceivedEvent event) {
		Guilda guilda = guildaService.findById(event.getGuild().getId());
		event.getJDA().openPrivateChannelById(Util.idUserAdm).queue((privateChannel) -> {
			messageController.sendPrivateMessage(privateChannel,
					"ID: " + event.getGuild().getId() + "\nNome: " + event.getGuild().getName() + "\nBlock: "
							+ guilda.isBlock() + "\nMsg bem vindo: " + guilda.getDefaultWelcomeMessage()
							+ "\nDefault Channel: " + guilda.getDefaultTextChannel() + "\nRegiao: " + guilda.getRegion()
							+ "\nAtivo: " + guilda.isActive());
		});
	}

	/**
	 * Debug channel.
	 *
	 * @param event the event
	 */
	private void debugChannel(MessageReceivedEvent event) {
		Canal canal = canalService.findById(event.getChannel().getId());
		event.getJDA().openPrivateChannelById(Util.idUserAdm).queue((privateChannel) -> {			
			messageController.sendPrivateMessage(privateChannel, "Cadastrado BD: " + canal.getId());
			messageController.sendPrivateMessage(privateChannel, "Id: " + event.getChannel().getId() + "\nNome: "
					+ event.getChannel().getName() + "\nPode falar: " + canal.isSendMessage());

		});
	}

	/**
	 * Debug bot.
	 *
	 * @param event the event
	 */
	private void debugBot(MessageReceivedEvent event) {
		SelfUser bot = event.getJDA().getSelfUser();
		event.getJDA().openPrivateChannelById(Util.idUserAdm).queue((privateChannel) -> {
			messageController.sendPrivateMessage(privateChannel, "Id: " + bot.getId() + "\nNome: " + bot.getName()
					+ "\nGuilds: " + bot.getMutualGuilds().toString());
			messageController.sendPrivateMessage(privateChannel,
					"Guild: " + event.getGuild().getId() + "-" + event.getGuild().getName());
			List<Role> roles = event.getGuild().getMember(bot).getRoles();
			messageController.sendPrivateMessage(privateChannel, "Roles: " + roles.toString());
			roles.forEach((role) -> {
				msgRole(privateChannel, event.getGuild(), role);
			});

		});
	}

	/**
	 * Msg role.
	 *
	 * @param privateChannel the private channel
	 * @param guild the guild
	 * @param role the role
	 */
	private void msgRole(PrivateChannel privateChannel, Guild guild, Role role) {
		Tag tag = tagService.findByIdGuildAndIdRole(guild.getId(), role.getId());
		messageController.sendPrivateMessage(privateChannel,
				"Role: " + role.getId() + "-" + role.getName() + "\nPermissao: " + role.getPermissionsRaw()
						+ " Posicao: " + role.getPosition() + "\nPermissoes: " + role.getPermissions().toString()
						+ "\nBot Role: " + tag.isBotRole() + " Cmd: [Adm: " + tag.isCmdAdm() + "] [Build: "
						+ tag.isCmdBuild() + "] [Fun: " + tag.isCmdFun() + "] [NodeWar: " + tag.isCmdNodeWar()
						+ "] [Rank: " + tag.isCmdRank() + "] [Util: " + tag.isCmdUtil() + "]");
	}

	/**
	 * Debug role.
	 *
	 * @param event the event
	 */
	private void debugRole(MessageReceivedEvent event) {
		event.getJDA().openPrivateChannelById(Util.idUserAdm).queue((privateChannel) -> {
			event.getMessage().getMentionedRoles().forEach((role) -> {
				msgRole(privateChannel, event.getGuild(), role);
			});
		});
	}

	/**
	 * Data base update.
	 *
	 * @param event the event
	 */
	private void dbUpdate(MessageReceivedEvent event) {

		updateAdventureFame();
		updateColorbd();
		updateGifBD();
		updateNodeWar();
		System.out.println("Atualizou");
	}
	
	/**
	 * Update adventure fame.
	 */
	private void updateAdventureFame() {
		String adventureFame = "1,184,160,'160+','APAWAK'),(2,209,185,'185+','APAWAK'),(3,235,210,'210+','APAWAK'),(4,244,236,'236+','APAWAK'),(5,248,245,'245+','APAWAK'),(6,252,249,'249+','APAWAK'),(7,256,253,'253+','APAWAK'),(8,260,257,'257+','APAWAK'),(9,264,261,'261+','APAWAK'),(10,268,265,'265+','APAWAK'),(11,272,269,'269+','APAWAK'),(12,276,273,'273+','APAWAK'),(13,280,277,'277+','APAWAK'),(14,284,281,'281+','APAWAK'),(15,288,285,'285+','APAWAK'),(16,292,289,'289+','APAWAK'),(17,296,293,'293+','APAWAK'),(18,300,297,'297+','APAWAK'),(19,304,301,'301+','APAWAK'),(20,308,305,'305+','APAWAK'),(21,350,309,'309+','APAWAK'),(22,210,203,'203+','DP'),(23,217,211,'211+','DP'),(24,225,218,'218+','DP'),(25,232,226,'226+','DP'),(26,240,233,'233+','DP'),(27,247,241,'241+','DP'),(28,255,248,'248+','DP'),(29,262,256,'256+','DP'),(30,270,263,'263+','DP'),(31,277,271,'271+','DP'),(32,285,278,'278+','DP'),(33,292,286,'286+','DP'),(34,300,293,'293+','DP'),(35,308,301,'301+','DP'),(36,315,309,'309+','DP'),(37,323,316,'316+','DP'),(38,330,324,'324+','DP'),(39,338,331,'331+','DP'),(40,345,339,'339+','DP'),(41,500,346,'346+','DP'),(42,184,160,'160+','AP'),(43,209,185,'185+','AP'),(44,235,210,'210+','AP'),(45,244,236,'236+','AP'),(46,248,245,'245+','AP'),(47,252,249,'249+','AP'),(48,256,253,'253+','AP'),(49,260,257,'257+','AP'),(50,264,261,'261+','AP'),(51,268,265,'265+','AP'),(52,272,269,'269+','AP'),(53,276,273,'273+','AP'),(54,280,277,'277+','AP'),(55,284,281,'281+','AP'),(56,288,285,'285+','AP'),(57,292,289,'289+','AP'),(58,296,293,'293+','AP'),(59,300,297,'297+','AP'),(60,304,301,'301+','AP'),(61,308,305,'305+','AP'),(62,350,309,'309+','AP'";
		 String[] adventureFameSplit = adventureFame.split("\\),\\(");
		 for(String adventureFameSplited : adventureFameSplit) {
			 System.out.println("Atualizando Adventure Fame:" + adventureFameSplited);
			 String[] adventureFameString = adventureFameSplited.split(",");
			 AdventureFame adventureFameClass = new AdventureFame();
			 adventureFameClass.setMax(Integer.valueOf(adventureFameString[1]));
			 adventureFameClass.setMin(Integer.valueOf(adventureFameString[2]));
			 adventureFameClass.setName(adventureFameString[3].replaceAll("'", ""));
			 adventureFameClass.setType(adventureFameString[4].replaceAll("'", ""));
			 adventureFameService.save(adventureFameClass);
		 }
	}
	
	/**
	 * Update colorbd.
	 */
	private void updateColorbd() {
		String colorbd = "1,255,144,'AP',30),(2,226,43,'APAWAK',138),(3,60,20,'DP',220";
		String[] colorbdSplit = colorbd.split(("\\),\\("));
		for(String colorbdSplited : colorbdSplit) {
			System.out.println("Atualizando color bd:" + colorbdSplited);
			String[] colorbdString = colorbdSplited.split(",");
			ColorBD color = new ColorBD();
			color.setBlue(Integer.valueOf(colorbdString[1]));
			color.setGreen(Integer.valueOf(colorbdString[2]));
			color.setRed(Integer.valueOf(colorbdString[4]));
			color.setName(colorbdString[3].replaceAll("'", ""));
			colorBDService.save(color);
		}
	}
	
	/**
	 * Update gif BD.
	 */
	private void updateGifBD() {
		String gifBD = "1,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721370535350632478/85dcef131af84b515106955e142df54e.gif'),(2,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721370649830096904/r9aU2xv.gif'),(3,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721370709301133322/tumblr_0ac41aabc0866df3de4cd7f3d10192f1_f0a270a7_400.gif'),(4,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721370800262873158/tumblr_ba135f278ee2e3d38bf932768d0412be_eb92cedc_400.gif'),(5,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721374009622790264/e8cbdfdc01347b0f6da64cba230498c8.gif'),(6,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721370977170227290/fm49srQ.gif'),(7,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721371048758607922/f8f1fe7f38ac144820b5b57d8c0fc6a8.gif'),(8,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721375752305770546/d87c5cdd0a68caf2b6feeec0f7da7315.gif'),(9,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721371793612603422/2f23c53755a5c3494a7f54bbcf04d1cc.gif'),(10,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721371591321321580/original.gif'),(11,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721377870219444224/tenor.gif'),(12,'AWOO','https://cdn.discordapp.com/attachments/721365678413578302/721371878333349958/gFNUSfS.gif'),(13,'OWO','https://cdn.discordapp.com/attachments/721365738270490634/721374075376631828/edfb0dcbaeefff5785e08825b5ad72af.jpg'),(14,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721372563514589272/giphy.gif'),(15,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721380206161428490/fd4ffaeff5127cd7aaab3281d1f3656b.gif'),(16,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721380949597880401/08d3420d21c9506f2ec89e4e462f0cb6.gif'),(17,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721371422416437248/HealthyCornyAmazontreeboa-size_restricted.gif'),(18,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721375662807580682/FaroffMistyBackswimmer-small.gif'),(19,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721372632821530684/oOtoTSB.gif'),(20,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721372768914112622/tumblr_d81068e55cef3bfb81ba71573c2906a7_18d1fad3_640.gif'),(21,'FOX','https://cdn.discordapp.com/attachments/721365984270876792/721372927156551781/37a7a10382550c1cf67461f0b84aeefb.gif'),(22,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721372299571232828/giphy_1.gif'),(23,'TRAP','https://cdn.discordapp.com/attachments/721366015732219996/721371211929747486/b2359fa9f797a8c8df5af50fd332f610.gif'),(24,'ERROR','https://cdn.discordapp.com/attachments/721366038096379934/721371662418968606/16e32c2116af60f81b44b17dc0de1af7.gif'),(25,'EXPLOSION','https://cdn.discordapp.com/attachments/721366066487623761/721371298068037702/acf52ee11fb58ef8184f7b25fd73ed7d.gif'),(26,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721371372474859540/ea8a415b5533d169f336859c148cca71.gif'),(27,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721379488843300963/tumblr_mlrr92OyU21so32vdo1_500.gif'),(28,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721468879775334491/6.gif'),(29,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721468932019716206/12.gif'),(30,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721468940940738653/22.gif'),(31,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721468965066637432/21.gif'),(32,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721468974617067630/8.gif'),(33,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721468980086439956/19.gif'),(34,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721468992903970937/9.gif'),(35,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469002303537172/2.gif'),(36,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469019315634176/13.gif'),(37,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469025355300914/15.gif'),(38,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469026869706802/17.gif'),(39,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469029432426606/20.gif'),(40,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469029612781608/23.gif'),(41,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469030778535946/10.gif'),(42,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469030757826621/11.gif'),(43,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469037212860456/169.gif'),(44,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469037544210542/27.gif'),(45,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469038294728754/25.gif'),(46,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469104950738964/26.gif'),(47,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469106011897958/3.gif'),(48,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469113381158952/1.gif'),(49,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469115868643388/24.gif'),(50,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469121358987318/4.gif'),(51,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469124336943134/18.gif'),(52,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469128824717322/28.gif'),(53,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469138492588162/7.gif'),(54,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469141130805330/5.gif'),(55,'HUG','https://cdn.discordapp.com/attachments/721361526090498138/721469146730070158/14.gif'),(56,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469238321217717/10.gif'),(57,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469247519195246/9.gif'),(58,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469267211583518/17.gif'),(59,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469274983497818/13.gif'),(60,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469278574084146/11.gif'),(61,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469279689506816/7.gif'),(62,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469280352206915/16.gif'),(63,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469282495758406/18.gif'),(64,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469284672339998/8.gif'),(65,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469303706091530/20.gif'),(66,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469319006912582/14.gif'),(67,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469343673745528/22.gif'),(68,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469350258671616/29.gif'),(69,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469351097663558/25.gif'),(70,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469364569899019/15.gif'),(71,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469365857288212/21.gif'),(72,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469390104821800/28.gif'),(73,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469391010660372/27.gif'),(74,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469398916792390/26.gif'),(75,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469410707243008/1.gif'),(76,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469423411658752/2.gif'),(77,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469431016063001/12.gif'),(78,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469449948889128/6.gif'),(79,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469453547864098/24.gif'),(80,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469481854959756/23.gif'),(81,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469485361528832/4.gif'),(82,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469492613611531/5.gif'),(83,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469492605222992/19.gif'),(84,'SLAP','https://cdn.discordapp.com/attachments/721361577361801226/721469495734042654/3.gif'),(85,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721379299365617694/tumblr_n0epycDRs81rptvzpo1_500.gif'),(86,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721469590118596718/3.gif'),(87,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721469604387487834/4.gif'),(88,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721469604685414400/6.gif'),(89,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721469609693151303/9.gif'),(90,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721469616278339674/1.gif'),(91,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721469616274145370/7.gif'),(92,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721469627213021244/5.gif'),(93,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721469666643673088/10.gif'),(94,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721469670196248616/8.gif'),(95,'NOM','https://cdn.discordapp.com/attachments/721361636710940682/721469704874623066/2.gif'),(96,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469658217054309/1.gif'),(97,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469659865677894/2.gif'),(98,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469696154664990/4.gif'),(99,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469696305659975/6.gif'),(100,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469697618346096/7.gif'),(101,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469707064049704/11.gif'),(102,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469713527472228/14.gif'),(103,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469735035863111/9.gif'),(104,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469736139096084/13.gif'),(105,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469737162506260/10.gif'),(106,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469740123684964/8.gif'),(107,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469754480656434/3.gif'),(108,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469759107104859/5.gif'),(109,'CUDDLE','https://cdn.discordapp.com/attachments/721361707670438030/721469765285314630/12.gif'),(110,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721378466347352151/Saito-x-Louise.gif'),(111,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721379412012040242/tumblr_mjorciyHyZ1qa94xto1_500.gif'),(112,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469773208092672/1.gif'),(113,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469803629641728/5.gif'),(114,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469811561070612/2.gif'),(115,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469812877950986/6.gif'),(116,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469821857824778/7.gif'),(117,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469827390242948/3.gif'),(118,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469830452215878/8.gif'),(119,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469830833897637/10.gif'),(120,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469833023324260/4.gif'),(121,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469835581587486/12.gif'),(122,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469838253490236/9.gif'),(123,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469904414310551/14.gif'),(124,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469912689934396/13.gif'),(125,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469932243779594/11.gif'),(126,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469930758864964/19.gif'),(127,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469954230190080/17.gif'),(128,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469955543007232/20.gif'),(129,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469958332088380/21.gif'),(130,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469966066647121/22.gif'),(131,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469968381771906/16.gif'),(132,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469971238092920/15.gif'),(133,'KISS','https://cdn.discordapp.com/attachments/721361801161211974/721469976078450769/18.gif'),(134,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470055531020450/1.gif'),(135,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470057070329856/2.gif'),(136,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470065547018270/3.gif'),(137,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470068663386122/4.gif'),(138,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470077483876433/5.gif'),(139,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470081359675403/6.gif'),(140,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470100808663040/7.gif'),(141,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470105103499294/8.gif'),(142,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470119611596810/9.gif'),(143,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470143766724698/10.gif'),(144,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470150968213595/11.gif'),(145,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470172526804992/12.gif'),(146,'BITE','https://cdn.discordapp.com/attachments/721361825051967499/721470191636185148/13.gif'),(147,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721378139334508594/giphy_12.gif'),(148,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721378417223925780/unnamed.gif'),(149,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721378636048891984/75b410eb65b59d6b4b4ea6a427272aa783bdf7f7_hq.gif'),(150,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721378834242470038/uiJslsG.gif'),(151,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721379539682328596/tumblr_motrdeBqwr1rcuwtao1_500.gif'),(152,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721379931652882522/L3YqJXL.gif'),(153,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721380770681323560/1UFXVl3.gif'),(154,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721470212637196368/3.gif'),(155,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721470218047717467/2.gif'),(156,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721470231897178202/1.gif'),(157,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721470243372924969/4.gif'),(158,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721470255670624416/6.gif'),(159,'DANCE','https://cdn.discordapp.com/attachments/721365595051917353/721470278382911568/5.gif'),(160,'AWOO','https://cdn.discordapp.com/attachments/721365678413578302/721470285508903004/1.gif'),(161,'AWOO','https://cdn.discordapp.com/attachments/721365678413578302/721470287161589790/2.gif'),(162,'OWO','https://cdn.discordapp.com/attachments/721365738270490634/721470296246321162/1.gif'),(163,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721470341897125888/1.gif'),(164,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721470350290059366/3.gif'),(165,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721470368581157004/6.gif'),(166,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721470369084735518/5.gif'),(167,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721470399417811104/10.gif'),(168,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721470404736319568/4.gif'),(169,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721470407542177852/9.gif'),(170,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721470409341665300/8.gif'),(171,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721470409895182346/2.gif'),(172,'POKE','https://cdn.discordapp.com/attachments/721365758436966431/721470414588608582/7.gif'),(173,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721430665475653632/15920728638571212369374.gif'),(174,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721431728647831623/15920731081211268036328.gif'),(175,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721432186749976596/15920732305531227067744.gif'),(176,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470429566337024/2.gif'),(177,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470433807040552/3.gif'),(178,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470445337051216/1.gif'),(179,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470446620508211/4.gif'),(180,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470457479561306/5.gif'),(181,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470480481124463/6.gif'),(182,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470481483432017/7.gif'),(183,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470483475857538/10.gif'),(184,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470509589594242/13.gif'),(185,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470513909858344/9.gif'),(186,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470523464220682/8.gif'),(187,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470543894937671/11.gif'),(188,'LEWD','https://cdn.discordapp.com/attachments/721365783904518247/721470544045801612/12.gif'),(189,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470547665354832/2.gif'),(190,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470556158820502/5.gif'),(191,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470558864408726/6.gif'),(192,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470577931452526/7.gif'),(193,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470578745278594/1.gif'),(194,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470581928886322/4.gif'),(195,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470583245897868/3.gif'),(196,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470590468489226/10.gif'),(197,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470607614541874/8.gif'),(198,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470625113178212/11.gif'),(199,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470632243494912/9.gif'),(200,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470635867504650/13.gif'),(201,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470641446060101/14.gif'),(202,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470642708545566/12.gif'),(203,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470659405807636/16.gif'),(204,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470664799944824/17.gif'),(205,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470671661563975/18.gif'),(206,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470675457671339/19.gif'),(207,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470677646966905/15.gif'),(208,'BLUSH','https://cdn.discordapp.com/attachments/721365815613587536/721470688216744037/20.gif'),(209,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470700598329455/1.gif'),(210,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470705417453738/4.gif'),(211,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470722005794947/2.gif'),(212,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470732386697339/3.gif'),(213,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470745795887246/5.gif'),(214,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470760450916362/6.gif'),(215,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470761109422110/10.gif'),(216,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470788221272175/12.gif'),(217,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470788393501043/7.gif'),(218,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470788926177420/11.gif'),(219,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470797884948621/9.gif'),(220,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470821901533335/16.gif'),(221,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470822727942265/15.gif'),(222,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470834513936474/8.gif'),(223,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470840407064696/14.gif'),(224,'CONFUSED','https://cdn.discordapp.com/attachments/721365883519238206/721470845700145172/13.gif'),(225,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721378695671185448/FWC3f7x.gif'),(226,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721380110384758814/day_42.gif'),(227,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721380657489772594/1353449367837.gif'),(228,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470862859173909/4.gif'),(229,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470864326918264/2.gif'),(230,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470866562482286/3.gif'),(231,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470867825229924/5.gif'),(232,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470871901962310/1.gif'),(233,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470892844253244/6.gif'),(234,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470895612493845/8.gif'),(235,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470895574614036/7.gif'),(236,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470907981365354/11.gif'),(237,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470915254288514/10.gif'),(238,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470942429184030/14.gif'),(239,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470954697523382/13.gif'),(240,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470975815843940/12.gif'),(241,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470992081354833/17.gif'),(242,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721470999178117200/16.gif'),(243,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721471045134975017/19.gif'),(244,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721471045965709382/15.gif'),(245,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721471048221982840/18.gif'),(246,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721471051082498179/20.gif'),(247,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721471051455791124/9.gif'),(248,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721471053116735528/21.gif'),(249,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721471057630068756/22.gif'),(250,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721471066895155260/23.gif'),(251,'CRY','https://cdn.discordapp.com/attachments/721365910689939486/721471068279144528/24.gif'),(252,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721378946335113307/ujVOKxP.gif'),(253,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471120511074364/1.gif'),(254,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471122842976316/2.gif'),(255,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471128857608223/5.gif'),(256,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471134687821844/6.gif'),(257,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471149506035733/4.gif'),(258,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471152375201843/7.gif'),(259,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471153478041650/10.gif'),(260,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471167583748176/11.gif'),(261,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471179092918353/12.gif'),(262,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471180347015199/14.gif'),(263,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471210244014141/9.gif'),(264,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471218590417017/16.gif'),(265,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471229902585996/15.gif'),(266,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471235728474122/8.gif'),(267,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471240958771200/3.gif'),(268,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471241407430676/17.gif'),(269,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471252899823616/13.gif'),(270,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471258243498026/21.gif'),(271,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471261615718420/20.gif'),(272,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471264014991461/22.gif'),(273,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471274492231750/19.gif'),(274,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471281115037807/18.gif'),(275,'SAD','https://cdn.discordapp.com/attachments/721365929140813875/721471282209751100/23.gif'),(276,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471300081811496/2.gif'),(277,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471313864032356/5.gif'),(278,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471322869203065/1.gif'),(279,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471333107499028/4.gif'),(280,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471353768771584/10.gif'),(281,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471356335685723/8.gif'),(282,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471361066860574/7.gif'),(283,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471362488598568/6.gif'),(284,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471368729722900/11.gif'),(285,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471369187033168/3.gif'),(286,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471376220749916/13.gif'),(287,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471380402733106/15.gif'),(288,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471380704723044/9.gif'),(289,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471397725077554/16.gif'),(290,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471408345055332/14.gif'),(291,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471419296514118/19.gif'),(292,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471434026778684/23.gif'),(293,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471447683563592/12.gif'),(294,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471455757467688/18.gif'),(295,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471460698488842/17.gif'),(296,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471462766018590/20.gif'),(297,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471463055556628/22.gif'),(298,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471469275578368/24.gif'),(299,'PAT','https://cdn.discordapp.com/attachments/721365947524579401/721471476393574510/21.gif'),(300,'FOX','https://cdn.discordapp.com/attachments/721365984270876792/721471489274150932/4.gif'),(301,'FOX','https://cdn.discordapp.com/attachments/721365984270876792/721471500275679332/2.gif'),(302,'FOX','https://cdn.discordapp.com/attachments/721365984270876792/721471505980063835/3.gif'),(303,'FOX','https://cdn.discordapp.com/attachments/721365984270876792/721471514746028132/1.gif'),(304,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471630622326814/4.gif'),(305,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471650154938388/14.gif'),(306,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471665615143033/2.gif'),(307,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471740688990238/3.gif'),(308,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471750675890217/9.gif'),(309,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471777229897728/6.gif'),(310,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471788453724210/10.gif'),(311,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471812826824896/13.gif'),(312,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471820146016306/12.gif'),(313,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471834465239181/5.gif'),(314,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471864291197018/11.gif'),(315,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471889012162671/7.gif'),(316,'PUNCH','https://cdn.discordapp.com/attachments/721366001371054090/721471921526669432/8.gif'),(317,'TRAP','https://cdn.discordapp.com/attachments/721366015732219996/721471759081275422/5.gif'),(318,'TRAP','https://cdn.discordapp.com/attachments/721366015732219996/721471791650045972/2.gif'),(319,'TRAP','https://cdn.discordapp.com/attachments/721366015732219996/721471808255164416/8.gif'),(320,'TRAP','https://cdn.discordapp.com/attachments/721366015732219996/721471826416369745/1.gif'),(321,'TRAP','https://cdn.discordapp.com/attachments/721366015732219996/721471844758061146/7.gif'),(322,'TRAP','https://cdn.discordapp.com/attachments/721366015732219996/721471878119817347/3.gif'),(323,'TRAP','https://cdn.discordapp.com/attachments/721366015732219996/721471927952343081/4.gif'";
		String[] gifBDSplit = gifBD.split("\\),\\(");
		for(String gifBDSplited : gifBDSplit) {
			System.out.println("Atualizando Gif Bd:"+gifBDSplited);
			String[] gifBDString = gifBDSplited.split(",");
			GifBD gif = new GifBD();
			gif.setType(gifBDString[1].replaceAll("'", ""));
			gif.setUrl(gifBDString[2].replaceAll("'", ""));
			gifBDService.save(gif);
		}
	}

	/**
	 * Update node war.
	 */
	private void updateNodeWar() {
		String nodeWar = "1,'Calpheon 1',1,0,25,'name_nw_115','SA','T1A'),(2,'Calpheon 1',1,3,30,'name_nw_128','SA','T3'),(3,'Calpheon 1',1,3,60,'name_nw_141','SA','T2'),(4,'Valencia 1',1,10,100,'name_nw_24','SA','T4'),(5,'Valencia 1',1,0,55,'name_nw_27','SA','T1A'),(6,'Calpheon 1',1,5,55,'name_nw_3','SA','T3'),(7,'Valencia 1',1,5,55,'name_nw_34','SA','T3'),(8,'Valencia 1',1,3,45,'name_nw_48','SA','T2'),(9,'Valencia 1',1,3,60,'name_nw_49','SA','T2'),(10,'Mediah 1',1,3,30,'name_nw_53','SA','T4'),(11,'Mediah 1',1,5,100,'name_nw_58','SA','T3'),(12,'Mediah 1',1,3,30,'name_nw_71','SA','T2'),(13,'Serendia 1',1,4,40,'name_nw_89','SA','T3'),(14,'Calpheon 1',1,10,100,'name_nw_91','SA','T4'),(15,'Balenos 1',1,0,55,'name_nw_92','SA','T1M'),(16,'Balenos 1',1,5,100,'name_nw_93','SA','T3'),(17,'Serendia 1',1,0,25,'name_nw_99','SA','T1M'),(18,'Calpheon 1',2,3,60,'name_nw_0','SA','T2'),(19,'Balenos 1',2,3,60,'name_nw_106','SA','T2'),(20,'Valencia 1',2,3,30,'name_nw_11','SA','T2'),(21,'Serendia 1',2,0,25,'name_nw_112','SA','T1A'),(22,'Calpheon 1',2,0,40,'name_nw_116','SA','T1M'),(23,'Calpheon 1',2,5,100,'name_nw_123','SA','T3'),(24,'Calpheon 1',2,3,30,'name_nw_127','SA','T4'),(25,'Calpheon 1',2,0,55,'name_nw_134','SA','T1M'),(26,'Calpheon 1',2,3,45,'name_nw_136','SA','T2'),(27,'Calpheon 1',2,0,25,'name_nw_143','SA','T1A'),(28,'Balenos 1',2,4,40,'name_nw_149','SA','T3'),(29,'Balenos 1',2,0,25,'name_nw_155','SA','T1M'),(30,'Valencia 1',2,0,40,'name_nw_20','SA','T1M'),(31,'Valencia 1',2,5,55,'name_nw_46','SA','T3'),(32,'Mediah 1',2,0,25,'name_nw_50','SA','T1M'),(33,'Mediah 1',2,3,45,'name_nw_57','SA','T2'),(34,'Mediah 1',2,3,30,'name_nw_59','SA','T2'),(35,'Mediah 1',2,10,100,'name_nw_67','SA','T4'),(36,'Serendia 1',2,5,100,'name_nw_80','SA','T3'),(37,'Serendia 1',2,3,30,'name_nw_87','SA','T2'),(38,'Balenos 1',2,0,40,'name_nw_94','SA','T1I'),(39,'Serendia 1',3,3,45,'name_nw_105','SA','T2'),(40,'Serendia 1',3,0,25,'name_nw_110','SA','T1A'),(41,'Calpheon 1',3,0,55,'name_nw_113','SA','T1M'),(42,'Calpheon 1',3,0,25,'name_nw_120','SA','T1M'),(43,'Calpheon 1',3,0,40,'name_nw_130','SA','T1M'),(44,'Calpheon 1',3,4,40,'name_nw_135','SA','T3'),(45,'Calpheon 1',3,3,30,'name_nw_142','SA','T2'),(46,'Balenos 1',3,3,45,'name_nw_146','SA','T2'),(47,'Calpheon 1',3,5,55,'name_nw_15','SA','T3'),(48,'Valencia 1',3,5,100,'name_nw_32','SA','T3'),(49,'Valencia 1',3,3,45,'name_nw_44','SA','T2'),(50,'Mediah 1',3,5,55,'name_nw_60','SA','T3'),(51,'Mediah 1',3,3,60,'name_nw_74','SA','T2'),(52,'Calpheon 1',3,10,100,'name_nw_83','SA','T4'),(53,'Serendia 1',4,0,40,'name_nw_101','SA','T1M'),(54,'Balenos 1',4,0,25,'name_nw_102','SA','T1A'),(55,'Serendia 1',4,5,55,'name_nw_107','SA','T3'),(56,'Calpheon 1',4,3,30,'name_nw_119','SA','T2'),(57,'Calpheon 1',4,0,55,'name_nw_131','SA','T1A'),(58,'Valencia 1',4,0,25,'name_nw_14','SA','T1I'),(59,'Calpheon 1',4,3,60,'name_nw_158','SA','T2'),(60,'Valencia 1',4,0,55,'name_nw_19','SA','T1M'),(61,'Valencia 1',4,3,30,'name_nw_36','SA','T3'),(62,'Calpheon 1',4,3,45,'name_nw_4','SA','T2'),(63,'Mediah 1',4,5,55,'name_nw_55','SA','T3'),(64,'Serendia 1',4,3,30,'name_nw_62','SA','T2'),(65,'Mediah 1',4,0,40,'name_nw_76','SA','T1M'),(66,'Mediah 1',4,3,60,'name_nw_77','SA','T2'),(67,'Balenos 1',4,10,100,'name_nw_98','SA','T4'),(68,'Calpheon 1',5,3,30,'name_nw_124','SA','T2'),(69,'Calpheon 1',5,3,45,'name_nw_126','SA','T2'),(70,'Valencia 1',5,0,40,'name_nw_13','SA','T1M'),(71,'Calpheon 1',5,4,40,'name_nw_133','SA','T3'),(72,'Calpheon 1',5,5,55,'name_nw_145','SA','T3'),(73,'Balenos 1',5,0,55,'name_nw_151','SA','T1M'),(74,'Calpheon 1',5,5,55,'name_nw_153','SA','T3'),(75,'Valencia 1',5,0,25,'name_nw_26','SA','T1M'),(76,'Valencia 1',5,3,30,'name_nw_43','SA','T2'),(77,'Valencia 1',5,10,100,'name_nw_45','SA','T4'),(78,'Calpheon 1',5,0,55,'name_nw_5','SA','T1A'),(79,'Mediah 1',5,3,45,'name_nw_56','SA','T2'),(80,'Mediah 1',5,5,100,'name_nw_73','SA','T3'),(81,'Serendia 1',5,3,30,'name_nw_82','SA','T3'),(82,'Serendia 1',5,0,40,'name_nw_84','SA','T1M'),(83,'Serendia 1',5,10,100,'name_nw_90','SA','T4'),(84,'Balenos 1',5,3,45,'name_nw_97','SA','T2'),(85,'Serendia 1',6,0,55,'name_nw_109','SA','T1A'),(86,'Calpheon 1',6,3,60,'name_nw_118','SA','T2'),(87,'Calpheon 1',6,3,30,'name_nw_129','SA','T2'),(88,'Calpheon 1',6,0,40,'name_nw_137','SA','T1I'),(89,'Calpheon 1',6,10,100,'name_nw_139','SA','T4'),(90,'Balenos 1',6,0,25,'name_nw_148','SA','T1M'),(91,'Calpheon 1',6,3,60,'name_nw_156','SA','T2'),(92,'Serendia 1',6,10,100,'name_nw_16','SA','T4'),(93,'Valencia 1',6,4,40,'name_nw_18','SA','T3'),(94,'Calpheon 1',6,5,100,'name_nw_2','SA','T3'),(95,'Valencia 1',6,5,100,'name_nw_25','SA','T3'),(96,'Valencia 1',6,3,60,'name_nw_30','SA','T2'),(97,'Valencia 1',6,3,30,'name_nw_37','SA','T2'),(98,'Valencia 1',6,0,55,'name_nw_41','SA','T1A'),(99,'Mediah 1',6,5,55,'name_nw_51','SA','T3'),(100,'Mediah 1',6,3,30,'name_nw_63','SA','T2'),(101,'Mediah 1',6,0,25,'name_nw_64','SA','T1M'),(102,'Mediah 1',6,10,100,'name_nw_68','SA','T4'),(103,'Mediah 1',6,3,60,'name_nw_72','SA','T2'),(104,'Serendia 1',6,5,55,'name_nw_78','SA','T3'),(105,'Serendia 1',6,0,40,'name_nw_81','SA','T1M'),(106,'Serendia 1',6,3,30,'name_nw_85','SA','T2'),(107,'Balenos 1',6,0,55,'name_nw_95','SA','T1A'),(108,'Valencia 1',7,100,100,'name_nw_10','SA','S'),(109,'Balenos 1',7,100,100,'name_nw_6','SA','S'),(110,'Serendia 1',7,100,100,'name_nw_7','SA','S'),(111,'Calpheon 1',7,100,100,'name_nw_8','SA','S'),(112,'Mediah 1',7,100,100,'name_nw_9','SA','S'";
		String[] nodeWarSplit = nodeWar.split("\\),\\(");
		for(String nodeWarSplited : nodeWarSplit) {
			System.out.println("Atualizando Node:" + nodeWarSplited);
			String[] nodeWarString = nodeWarSplited.split(",");
			Node node = new Node();
			node.setChannel(nodeWarString[1].replaceAll("'", ""));
			node.setDayOfWeek(Integer.valueOf(nodeWarString[2]));
			node.setLimitHeroi(Integer.valueOf(nodeWarString[3]));
			node.setLimitPlayer(Integer.valueOf(nodeWarString[4]));
			node.setName(nodeWarString[5].replaceAll("'", ""));
			node.setServer(nodeWarString[6].replaceAll("'", ""));
			node.setTier(nodeWarString[7].replaceAll("'", ""));
			nodeService.save(node);
		}
		
	
	}
	
}
