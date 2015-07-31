package com.jnu.data_provider;

import java.util.ArrayList;

public class data_provider {
	private ArrayList<String> citylist;
	private ArrayList<Double> month_price;
	String[] city={"钦州","茂名","琼涂","海口","文昌","万宁"};
	double[] price={4.9, 5.1, 4.7, 4.8, 4.8, 5.0, 5.3, 5.6, 5.4, 5.5, 5.3, 5.2};
	
	public data_provider(){
		citylist=new ArrayList<String>();
		month_price=new ArrayList<Double>();
		for(int i=0;i<city.length;i++){
			citylist.add(city[i]);
		}
		for(int i=0;i<price.length;i++){
			month_price.add(price[i]);
		}
	}
	
	public ArrayList<String> get_citylist(){
		return citylist;
	}
	public ArrayList<Double> get_month_price(){
		return month_price;
	}
}
