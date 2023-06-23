package com.nkxgen.spring.jdbc.Bal;

import java.util.Scanner;

public class FDIntrestcaluclation implements Accounts {
	double FDintrstRate;
	double FDAmnt;
	int period;
	int age;
	double Gen;
	double intrstRate;
	Scanner input = new Scanner(System.in);

	@Override
	public double calcIntrst(double amnt) {
		this.FDAmnt = amnt;
		System.out.println("Enter your FD days");
		period = input.nextInt();
		System.out.println("Enter the age of account holder ");
		age = input.nextInt();
		if (amnt < 10000000) {
			if (period >= 7 && period <= 14) {
				Gen = 0.0450;
			} else if (period >= 15 && period <= 29) {
				Gen = 0.0470;
			} else if (period >= 30 && period <= 45) {
				Gen = 0.0550;
			} else if (period >= 45 && period <= 60) {
				Gen = 0.0700;
			} else if (period >= 61 && period <= 184) {
				Gen = 0.0750;

			} else if (period >= 185 && period <= 365) {
				Gen = 0.0800;

			}

		} else {
			if (period >= 7 && period <= 14) {
				intrstRate = 0.065;
			} else if (period >= 15 && period <= 29) {
				intrstRate = 0.0675;
			} else if (period >= 30 && period <= 45) {
				intrstRate = 0.00675;
			} else if (period >= 45 && period <= 60) {
				intrstRate = 0.080;
			} else if (period >= 61 && period <= 184) {
				intrstRate = 0.0850;
			} else if (period >= 185 && period <= 365) {
				intrstRate = 0.10;
			}
		}
		return FDAmnt * FDintrstRate;
	}
}
