package com.bot.KaworiSpring.util;

// TODO: Auto-generated Javadoc
/**
 * The Enum Emojis.
 */
public enum Emojis {

	/** The one. */
	ONE(1, "1️⃣"), /** The two. */
 TWO(2, "2️⃣"), /** The three. */
 THREE(3, "3️⃣"), /** The four. */
 FOUR(4, "4️⃣"), /** The five. */
 FIVE(5, "5️⃣"), /** The six. */
 SIX(6, "6️⃣"), /** The seven. */
 SEVEN(7, "7️⃣"),
	
	/** The eight. */
	EIGHT(8, "8️⃣"), 
 /** The nine. */
 NINE(9, "9️⃣"), 
 /** The ten. */
 TEN(10, ""), 
 /** The zero. */
 ZERO(0, "0️⃣"), 
 /** The check ok. */
 CHECK_OK(11, "✅"), 
 /** The cancel. */
 CANCEL(12, "❎"), 
 /** The back. */
 BACK(13,"🔙"),
/** The first. */
FIRST(14, "🔚"),
/** The next. */
NEXT(15,"🔜");

	/** The id. */
	private int id;
	
	/** The emoji. */
	private String emoji;

	/**
	 * Instantiates a new emojis.
	 *
	 * @param id the id
	 * @param emoji the emoji
	 */
	Emojis(int id, String emoji) {
		this.id = id;
		this.emoji = emoji;
	}

	/**
	 * Gets the emoji.
	 *
	 * @return the emoji
	 */
	public String getEmoji() {
		return emoji;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the emojis.
	 *
	 * @param emote the emote
	 * @return the emojis
	 */
	public static Emojis getEmojis(String emote) {
		for (Emojis emoji : Emojis.values()) {
			if (emoji.emoji.equals(emote)) {
				return emoji;
			}
		}
		return null;
	}

	/**
	 * Gets the emoji.
	 *
	 * @param id the id
	 * @return the emoji
	 */
	public static Emojis getEmoji(int id) {
		for (Emojis emoji : Emojis.values()) {
			if (emoji.getId() == id) {
				return emoji;
			}
		}
		return null;
	}
}
