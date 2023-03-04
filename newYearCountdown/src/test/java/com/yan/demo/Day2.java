package com.yan.demo;

import java.util.List;

/**
 * @description:
 * @author: lg
 * @create: 2022-01-07 16:33
 **/
public class Day2 {
	private String  date;
	private String name;

	@Override
	public String toString() {
		return "Day2{" +
				"date='" + date + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
