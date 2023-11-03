package com.oracle.s202350104.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s202350104.model.Areas;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AreasDaoImpl implements AreasDao {

	private final SqlSession session;

	@Override
	public List<Areas> listAreas() {
		List<Areas> listAreas = null;
		try {
			listAreas = session.selectList("AreasListAll");
		} catch(Exception e) {
			log.info("{}",e.getMessage());
		}
		return listAreas;
	}

	@Override
	public List<Areas> listAreas(Areas area) {
		List<Areas> listAreas = null;
		try {
			listAreas = session.selectList("joAreasListAll", area);
			log.info("AreasDaoImpl listAreas size()->" + listAreas.size());			
		} catch (Exception e) {
			log.info("{}", e.getMessage());
		}
		return listAreas;
	}

	@Override
	public int totalAreaCode() {
		int totalAreaCode = 0;
		try {
			totalAreaCode = session.selectOne("joTotalAreaCode");
			log.info("AreasDaoImpl totalAreaCode ->" + totalAreaCode);
		} catch (Exception e) {
			log.info("{}", e.getMessage());
		} 
		return totalAreaCode;
	}

	@Override
	public int conTotalAreaCode(Areas area) {
		int conTotalAreaCode = 0;
		try {
			conTotalAreaCode = session.selectOne("joConTotalAreaCode", area);
		} catch (Exception e) {
			log.info("AreasDaoImpl joConTotalAreaCode() Exception ->" + e.getMessage());
		}
		
		return conTotalAreaCode;
	}

	@Override
	public List<Areas> listSearchAreaCode(Areas area) {
		List<Areas> listSearchAreaCode = null;
		try {
			listSearchAreaCode = session.selectList("joListSearchAreaCode", area);
		} catch (Exception e) {
			log.info("AreasDaoImpl joListSearchAreaCode() Exception ->" + e.getMessage());
		}
		
		return listSearchAreaCode;
	}

	
}
