package com.oracle.s202350104.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s202350104.model.CommonCodes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Repository
@RequiredArgsConstructor
@Slf4j
public class CommonCodesDaoImpl implements CommonCodesDao {
	private final SqlSession session;
	
	@Override
	public List<CommonCodes> listCommonCodes() {
		List<CommonCodes> listCommonCodes = null;
		try {
			listCommonCodes = session.selectList("CommonCodesListAll");
			log.info("listCommonCodes.size" + listCommonCodes.size());
		} catch(Exception e) {
			log.info("{}",e.getMessage());
		}
		return listCommonCodes;
	}

	
	
	@Override
	public List<CommonCodes> listCommonCodes(CommonCodes commonCode) {
		List<CommonCodes> listCommonCodes = null;
		try {
			listCommonCodes = session.selectList("joCommonCodesListAll", commonCode);
			log.info("listCommonCodes.size" + listCommonCodes.size());
		} catch(Exception e) {
			log.info("{}",e.getMessage());
		}
		return listCommonCodes;
	}

	@Override
	public int totalCommonCode() {
		int totalCommonCode = 0;
		try {
			totalCommonCode = session.selectOne("joCommonCodeTotal");
		} catch (Exception e) {
			log.info("CommonCodeDaoImpl totalCommonCode() Exception ->" + e.getMessage());
		}
		
		return totalCommonCode;
	}



	@Override
	public int conTotalCommonCode(CommonCodes commonCode) {
		int conTotalCommonCode = 0;
		try {
			conTotalCommonCode = session.selectOne("joConTotalCommonCode", commonCode);
		} catch (Exception e) {
			log.info("CommonCodeDaoImpl joConTotalCommonCode() Exception ->" + e.getMessage());
		}
		
		return conTotalCommonCode;
	}



	@Override
	public List<CommonCodes> listSearchCommonCode(CommonCodes commonCode) {
		List<CommonCodes> listSearchCommonCode = null;
		try {
			listSearchCommonCode = session.selectList("joListSearchCommonCode", commonCode);
		} catch (Exception e) {
			log.info("CommonCodeDaoImpl joListSearchCommonCode() Exception ->" + e.getMessage());
		}
		
		return listSearchCommonCode;
	}

}