package com.oracle.s202350104.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.oracle.s202350104.dao.FavoriteDao;
import com.oracle.s202350104.model.Favorite;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {
	
	private final FavoriteDao fad;
	
	@Override
	public List<Favorite> listFavorite(Favorite favorite) {
		List<Favorite> listFavorite = fad.listFavorite(favorite);
		
		if(listFavorite == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찜 목록 리스트가 존재하지 않습니다.");
			
		}
		return listFavorite;
	}

	@Override
	public int totalFavorite() {
		int totalFavorite = 0;
		totalFavorite = fad.totalFavorite();
		return totalFavorite;
	}

	@Override
	public int condTotalFavorite(Favorite favorite) {
		int condTotalFavorite = 0;
		condTotalFavorite = fad.condTotalFavorite(favorite);
		log.info("FavoriteServiceImpl condTotalFavorite ->" + condTotalFavorite);
		return condTotalFavorite;
	}

	@Override
	public List<Favorite> listSearchFavorite(Favorite favorite) {
		List<Favorite> listSearchFavorite = fad.listSearchFavorite(favorite);
		
		if(listSearchFavorite == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찜 목록 리스트가 존재하지 않습니다.");
		}
		return listSearchFavorite;
	}

	@Override
	public List<Favorite> getMyLikeList(Favorite favorite) {
		List<Favorite> getMyLikeList = fad.getMyLikeList(favorite);
		
		if(getMyLikeList == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "내 찜 목록 리스트가 존재하지 않습니다.");
		}
		
		return getMyLikeList;
	}

	@Override
	public int totalMyLikeList() {
		int totalMyLikeList = 0; 
		totalMyLikeList = fad.totalMyLikeList();
		
		return totalMyLikeList;
	}

	@Override
	public int deleteMyLikeList(int id) {
		int result = 0;
		result = fad.deleteMyLikeList(id);
		
		if(result <= 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return result;
	}

	@Override
	public int insertFavorite(Favorite favorite) {
		int result = 0;
		result = fad.insertFavorite(favorite);
		
		if(result <= 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return result;
	}

}
