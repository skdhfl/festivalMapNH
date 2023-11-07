package com.oracle.s202350104.dao;

import java.util.List;

import com.oracle.s202350104.model.Spot;
import com.oracle.s202350104.model.SpotContent;

public interface SpotDao {

	List<SpotContent> listSpot(SpotContent spotContent);
	int               totalSpot();
	SpotContent       detailSpot(int contentId);
	int               insertSpot(SpotContent spotContent);
	int               updateSpot(SpotContent spot);

}
