package com.tvo.puzzle.util;

public class PagingUtil
{
	public static final String CURRENT_PAGE = "currentPage";
	public static final int RECORDS_PER_PAGE = 15;

	public static int getOffset(Integer currentPage, int limit, int totalRecords)
	{
		int offset = 0;
		if (currentPage == null)
		{
			currentPage = 0;
		}
		// Determine currentPage and calculate offset
		if ((currentPage <= 0) || (totalRecords < currentPage))
		{
			currentPage = 1;
		}
		offset = (currentPage - 1) * limit;
		if (currentPage > 1 && totalRecords <= offset)
		{
			offset = (getEndPage(totalRecords, limit) - 1) * limit;
		}
		return offset;
	}

	public static int getEndPage(int totalRecords, int limit)
	{
		int endPage;
		if ((totalRecords % limit) == 0)
		{
			endPage = totalRecords / limit;
		}
		else
		{
			Double dEndPage = Math.floor((totalRecords / limit));
			endPage = dEndPage.intValue() + 1;
		}
		return endPage;
	}
}
