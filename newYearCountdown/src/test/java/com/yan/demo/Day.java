package com.yan.demo;

import java.util.List;

/**
 * @description:
 * @author: lg
 * @create: 2022-01-07 16:33
 **/
public class Day {
	private String  listnumbaidu;
	private String year;
	private List<Day2> list;

	public String getListnumbaidu() {
		return listnumbaidu;
	}

	public void setListnumbaidu(String listnumbaidu) {
		this.listnumbaidu = listnumbaidu;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Day2> getList() {
		return list;
	}

	public void setList(List<Day2> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Day{" +
				"listnumbaidu='" + listnumbaidu + '\'' +
				", year='" + year + '\'' +
				", list=" + list +
				'}';
	}
}
