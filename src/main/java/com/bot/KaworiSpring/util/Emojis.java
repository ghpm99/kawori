package com.bot.KaworiSpring.util;

public enum Emojis {

	ONE(1, "1️⃣"), TWO(2, "2️⃣"), THREE(3, "3️⃣"), FOUR(4, "4️⃣"), FIVE(5, "5️⃣"), SIX(6, "6️⃣"), SEVEN(7, "7️⃣"),
	EIGHT(8, "8️⃣"), NINE(9, "9️⃣"), TEN(10, ""), ZERO(0, "0️⃣"), CHECK_OK(11, "✅"), CANCEL(12, "❎"), BACK(13,"🔙"),FIRST(14, "🔚"),NEXT(15,"🔜");

	private int id;
	private String emoji;

	Emojis(int id, String emoji) {
		this.id = id;
		this.emoji = emoji;
	}

	public String getEmoji() {
		return emoji;
	}

	public int getId() {
		return id;
	}

	public static Emojis getEmojis(String emote) {
		for (Emojis emoji : Emojis.values()) {
			if (emoji.emoji.equals(emote)) {
				return emoji;
			}
		}
		return null;
	}

	public static Emojis getEmoji(int id) {
		for (Emojis emoji : Emojis.values()) {
			if (emoji.getId() == id) {
				return emoji;
			}
		}
		return null;
	}
}
