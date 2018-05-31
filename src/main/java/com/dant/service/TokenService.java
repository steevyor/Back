package com.dant.service;

import com.dant.database.TokenDAO;
import com.dant.entity.Token;
import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;

import java.sql.SQLException;

public class TokenService {

    private final TokenDAO dao = new TokenDAO();

    public boolean canUseService(TokenDTO dto) throws SQLException{
        System.out.println("TokenService.canUseService : getting token Object by DTOKey");
        Token currentInDataBaseToken = dao.getByKey(dto.getKey());
        System.out.println("TokenService.canUseService : recuperated Token :"+currentInDataBaseToken.getTokenKey());
        System.out.println("TokenService.canUseService : timer : "+currentInDataBaseToken.getTimer());
        System.out.println("TokenService.canUseService : "+currentInDataBaseToken.isTimerGapValid());
        return currentInDataBaseToken.isTimerGapValid();
    }

    public void updateTokenTimer(TokenDTO dto) throws SQLException{
        System.out.println("TokenService.updateTokenTimer : updating timer of token "+dto.getKey());
        dto.setCurrentTimeNow();
        System.out.println("TokenService.updateTokenTimer : new timer value = "+dto.getCurrentTime());
        System.out.println("TokenService.updateTokenTimer : Launching DAO.update");
        dao.update(new Token(dto.getKey(), dto.getCurrentTime()));
    }

    public Token save(Token token, String userPseudo) throws SQLException{
        System.out.println("TokenService.save : saving token ("+token.getTokenKey() +"; " +token.getTimer() +")");
        dao.save(token, userPseudo);
        System.out.println("TokenService.save : token successfully saved, returning token");
        return token;
    }
}
