package com.bot.KaworiSpring.util;

import java.util.Comparator;

import com.bot.KaworiSpring.model.Gear;

// TODO: Auto-generated Javadoc
/**
 * The Class GearSort.
 */
public class GearSort {

	/**
	 * The Class GearSortByGS.
	 */
	public static class GearSortByGS implements Comparator<Gear> {

		/**
		 * Compare.
		 *
		 * @param o1 the o 1
		 * @param o2 the o 2
		 * @return the int
		 */
		@Override
		public int compare(Gear o1, Gear o2) {
			int gs1 = Util.calculateGearScore(o1.getAp(), o1.getApAwak(), o1.getDp());
			int gs2 = Util.calculateGearScore(o2.getAp(), o2.getApAwak(), o2.getDp());
			return  gs2 - gs1;
			
		}

	}

	/**
	 * The Class GearSortByAp.
	 */
	public static class GearSortByAp implements Comparator<Gear> {

		/**
		 * Compare.
		 *
		 * @param o1 the o 1
		 * @param o2 the o 2
		 * @return the int
		 */
		@Override
		public int compare(Gear o1, Gear o2) {
			// TODO Auto-generated method stub
			return  o2.getAp() - o1.getAp() ;
		}

	}

	/**
	 * The Class GearSortByApAwak.
	 */
	public static class GearSortByApAwak implements Comparator<Gear> {

		/**
		 * Compare.
		 *
		 * @param o1 the o 1
		 * @param o2 the o 2
		 * @return the int
		 */
		@Override
		public int compare(Gear o1, Gear o2) {
			// TODO Auto-generated method stub
			return o2.getApAwak() - o1.getApAwak();
		}

	}

	/**
	 * The Class GearSortByDp.
	 */
	public static class GearSortByDp implements Comparator<Gear> {

		/**
		 * Compare.
		 *
		 * @param o1 the o 1
		 * @param o2 the o 2
		 * @return the int
		 */
		@Override
		public int compare(Gear o1, Gear o2) {
			// TODO Auto-generated method stub
			return o2.getDp() - o1.getDp();
		}

	}

	/**
	 * The Class GearSortByLevel.
	 */
	public static class GearSortByLevel implements Comparator<Gear> {

		/**
		 * Compare.
		 *
		 * @param o1 the o 1
		 * @param o2 the o 2
		 * @return the int
		 */
		@Override
		public int compare(Gear o1, Gear o2) {
			// TODO Auto-generated method stub
			return o2.getLevel() - o1.getLevel();
		}

	}
}
