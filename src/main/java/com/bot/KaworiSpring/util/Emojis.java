package com.bot.KaworiSpring.util;

public enum Emojis {

	ONE("1️⃣"),TWO("2️⃣"),THREE("3️⃣"),FOUR("4️⃣"),FIVE("5️⃣"),SIX("6️⃣"),SEVEN("7️⃣"),EIGHT("8️⃣"),NINE("9️⃣"),TEN(""),ZERO("0️⃣"),CHECK_OK("✅"),CANCEL("❎");
	
	private String emoji;
	
	Emojis(String emoji) {
		this.emoji = emoji;
	}
	
	public String getEmoji() {
		return emoji;
	}
	
	public static Emojis getEmojis(String emote) {
		for (Emojis emoji : Emojis.values()) {
			if(emoji.emoji.equals(emote)) {
				return emoji;
			}
		}
		return null;
	}
	
}
