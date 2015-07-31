package com.jnu.data_provider;

import java.util.ArrayList;

public class data_provider {
	private ArrayList<String> citylist;
	private ArrayList<double[]> month_price;
	
	private final ArrayList<String[]> citys;
	private final String[] Provinces={"广东","广西","海南"};
	private final String[] size={"0.6~1.0","1.0~1.6",">1.6"};
	//每个地方当日三种价格
	double[] price_1={3.9, 3.5, 3.3, 3.6, 4.0, 4.2, 2.9, 3.5, 3.4, 3.8, 3.3, 3.4};
	double[] price_2={4.9, 5.1, 4.7, 4.8, 4.8, 5.0, 5.3, 5.6, 5.4, 5.5, 5.3, 5.2};
	double[] price_3={4.2, 5.4, 4.1, 4.9, 5.2, 5.3, 5.2, 5.6, 5.8, 5.9, 5.2, 5.6};
	public data_provider(){
		citylist=new ArrayList<String>();
		month_price=new ArrayList<double[]>();
		citys=new ArrayList<String[]>();
		citys.add(new String[]{"钦州","广州","深圳","佛山","东莞","中山","珠海"});
		citys.add(new String[]{"茂名"});
		citys.add(new String[]{"琼涂","海口","文昌","万宁"});
		
		for(int i=0;i<citys.size();i++){
			for(int j=0;j<citys.get(i).length;j++){
				citylist.add(citys.get(i)[j]);
			}
		}
		for(int i=0;i<price_2.length;i++){
			month_price.add(new double[]{price_1[i],price_2[i],price_3[i]});
		}
	}
	
	public ArrayList<String> get_citylist(){
		return citylist;
	}
	public ArrayList<double[]> get_daily_price(){
		return month_price;
	}
	public String[] get_Provinces(){
		return Provinces;
	}
	public ArrayList<String[]> get_citis(){
		return citys;
	}
	public String[] get_size(){
		return size;
	}
}
