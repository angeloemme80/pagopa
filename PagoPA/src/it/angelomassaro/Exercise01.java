package it.angelomassaro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise01 {

	public static void main(String[] args) {
		Exercise01 pagoPa = new Exercise01();
		//Integer[] i = pagoPa.exrcise01(new Object[]{1, 2, 3, new Object[]{4,5}, 6, 7});
		//Integer[] i = pagoPa.exrcise01(null);
		//Integer[] i = pagoPa.exrcise01(new Object[]{1, 2, 3, new Object[]{4,5}, 6, 7, 8});
		Integer[] i = pagoPa.exrcise01(new Object[]{1, 2, 3, new Object[]{4,5}, 6, 7, 8, new Object[]{}, new Object[]{new Object[]{12,13,14}}  });
		System.out.println(Arrays.toString(i));
	}
	
	public Integer[] exrcise01(Object[] arrayOfArray) {
		
		if (arrayOfArray == null) return null;
		List<Integer> returnList = new ArrayList<Integer>();
        for (Object obj : arrayOfArray) {
            if (obj instanceof Integer) {
            	returnList.add((Integer) obj);
            } else if (obj instanceof Object[]) {
            	returnList.addAll(Arrays.asList(exrcise01((Object[]) obj)));
            } else {
                throw new IllegalArgumentException("Input must be an integer array or an array of integer arrays");
            }
        }
        return returnList.toArray(new Integer[returnList.size()]);
		
	}
	

}
