package com.bot.KaworiSpring.discord.command.commands;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.service.GearService;
import com.bot.KaworiSpring.service.OperatorService;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdExcel extends Command {

	@Autowired
	private GearService gearService;
	@Autowired
	private OperatorService operatorService;

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		Workbook workbook = createExcel(event);
		byte[] data = createArrayByte(workbook);
		sendPrivateFile(event, data);
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "msg_excel_help";
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_excel_helpshort";
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_ADM;
	}

	private Workbook createExcel(MessageReceivedEvent event) {
		List<Gear> gears = gearService.findByIdGuild(event.getGuild().getIdLong());

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Gear");

		Row header = sheet.createRow(0);

		Cell headerDiscord = header.createCell(0);
		headerDiscord.setCellValue("Discord");

		Cell headerFamily = header.createCell(1);
		headerFamily.setCellValue("Nome de familia");

		Cell headerPersonagem = header.createCell(2);
		headerPersonagem.setCellValue("Personagem");

		Cell headerClass = header.createCell(3);
		headerClass.setCellValue("Class");

		Cell headerBattle = header.createCell(4);
		headerBattle.setCellValue("Battle mode");

		Cell headerAp = header.createCell(5);
		headerAp.setCellValue("AP");

		Cell headerAap = header.createCell(6);
		headerAap.setCellValue("AAP");

		Cell headerDp = header.createCell(7);
		headerDp.setCellValue("DP");

		Cell headerLvl = header.createCell(8);
		headerLvl.setCellValue("LvL");

		Cell headerScore = header.createCell(9);
		headerScore.setCellValue("GearScore");

		Cell headerActive = header.createCell(10);
		headerActive.setCellValue("Ativo");

		Cell headerLink = header.createCell(11);
		headerLink.setCellValue("Link");

		Cell headerNew = header.createCell(12);
		headerNew.setCellValue("New");
		
		Cell headerCan = header.createCell(13);
		headerCan.setCellValue("Can Use");

		for (int i = 0; i < gears.size(); i++) {
			Gear gear = gears.get(i);
			Operator user = operatorService.findById(gear.getPersonagem().getMembro().getIdUser());
			
			Row rowValue = sheet.createRow(i + 1);

			Cell valueDiscord = rowValue.createCell(0);
			valueDiscord.setCellValue(user.getName() + "#" + user.getDiscriminator() + "("
					+ gear.getPersonagem().getMembro().getNick() + ")");

			Cell valueFamily = rowValue.createCell(1);
			valueFamily.setCellValue(gear.getPersonagem().getMembro().getFamilyName());

			Cell valuePersonagem = rowValue.createCell(2);
			valuePersonagem.setCellValue(gear.getPersonagem().getName());

			Cell valueClass = rowValue.createCell(3);
			valueClass.setCellValue(gear.getPersonagem().getClasse());

			Cell valueBattle = rowValue.createCell(4);
			valueBattle.setCellValue(gear.getPersonagem().getBattleMode());

			Cell valueAp = rowValue.createCell(5);
			valueAp.setCellValue(String.valueOf(gear.getAp()));

			Cell valueAap = rowValue.createCell(6);
			valueAap.setCellValue(String.valueOf(gear.getApAwak()));

			Cell valueDp = rowValue.createCell(7);
			valueDp.setCellValue(String.valueOf(gear.getDp()));

			Cell valueLvl = rowValue.createCell(8);
			valueLvl.setCellValue(String.valueOf(gear.getLevel()));

			Cell valueScore = rowValue.createCell(9);
			valueScore.setCellValue(String.valueOf(gear.getScore()));

			Cell valueActive = rowValue.createCell(10);
			valueActive.setCellValue(String.valueOf(gear.isAtivo()));

			Cell valueLink = rowValue.createCell(11);
			valueLink.setCellValue(gear.getLink());

			Cell valueNew = rowValue.createCell(12);
			valueNew.setCellValue(String.valueOf(gear.isYoung()));
			
			Cell valueCan = rowValue.createCell(13);
			valueCan.setCellValue(String.valueOf(gear.getPersonagem().getMembro().isGear()));

		}

		return workbook;

	}

	private void sendPrivateFile(MessageReceivedEvent event, byte[] data) {
		event.getAuthor().openPrivateChannel().queue((privateChannel) -> {
			privateChannel.sendFile(data, event.getAuthor().getId() + " - " + event.getGuild().getId() + ".xlsx")
					.queue();
			;
		});
	}

	private byte[] createArrayByte(Workbook workbook) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			workbook.write(baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
}
