package com.tvo.puzzle.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author MinhTQ
 *
 */
public class CommonUtils {
  
	public static boolean isListEmpty(List list) {
		if (null == list || list.size() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(String string) {
		if (null == string || "".equals(string)) {
			return true;
		}
		return false;
	}
    
    
    public static Integer getMore(Integer count, int offset, int limit) {
        if (count > offset + limit) {
            return 1;
        }
        return 0;
    }
    

	public static int getOffset(Integer currentPage, int limit, int totalRecords) {
		int offset = 0;
		if (currentPage == null) {
			currentPage = 0;
		}
		// Determine currentPage and calculate offset
		if ((currentPage <= 0) || (totalRecords < currentPage)) {
			currentPage = 1;
		}
		offset = (currentPage - 1) * limit;
		if (currentPage > 1 && totalRecords <= offset) {
			offset = (getEndPage(totalRecords, limit) - 1) * limit;
		}
		return offset;
	}

	public static int getEndPage(int totalRecords, int limit) {
		int endPage;
		if ((totalRecords % limit) == 0) {
			endPage = totalRecords / limit;
		} else {
			Double dEndPage = Math.floor((totalRecords / limit));
			endPage = dEndPage.intValue() + 1;
		}
		return endPage;
	}
	
	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
