package com.iesalixar.playit.utils;

import java.util.Comparator;
import com.iesalixar.playit.model.Chapter;

public class seasonChapterCompareTo implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		int resultado;
		Chapter c1 = (Chapter) o1;
		Chapter c2 = (Chapter) o2;
		
		if (c1.getSeason()>c2.getSeason()){
            resultado=1;
        }else if (c2.getSeason()>c1.getSeason()){
            resultado=-1;
        }else{
        	return c1.getNumber().compareTo(c2.getNumber());
        }
		return resultado;
	}


}
