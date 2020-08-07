package com.bot.KaworiSpring.util;

import java.util.Comparator;

import com.bot.KaworiSpring.model.Gear;

public class GearSort {

	public static class GearSortByGS implements Comparator<Gear> {

		@Override
		public int compare(Gear o1, Gear o2) {
			int gs1 = Util.calculateGearScore(o1.getAp(), o1.getApAwak(), o1.getDp());
			int gs2 = Util.calculateGearScore(o2.getAp(), o2.getApAwak(), o2.getDp());
			return  gs2 - gs1;
			
		}

	}

	public static class GearSortByAp implements Comparator<Gear> {

		@Override
		public int compare(Gear o1, Gear o2) {
			// TODO Auto-generated method stub
			return  o2.getAp() - o1.getAp() ;
		}

	}

	public static class GearSortByApAwak implements Comparator<Gear> {

		@Override
		public int compare(Gear o1, Gear o2) {
			// TODO Auto-generated method stub
			return o2.getApAwak() - o1.getApAwak();
		}

	}

	public static class GearSortByDp implements Comparator<Gear> {

		@Override
		public int compare(Gear o1, Gear o2) {
			// TODO Auto-generated method stub
			return o2.getDp() - o1.getDp();
		}

	}

	public static class GearSortByLevel implements Comparator<Gear> {

		@Override
		public int compare(Gear o1, Gear o2) {
			// TODO Auto-generated method stub
			return o2.getLevel() - o1.getLevel();
		}

	}
}
