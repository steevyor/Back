package com.dant.service;

import com.dant.database.TokenDAO;
import com.dant.entity.Token;
import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.SQLException;

public class TokenService {

    private final TokenDAO dao = new TokenDAO();

    public boolean canUseService(TokenDTO dto) throws SQLException{
        System.out.println("TokenService.canUseService : getting token Object by DTOKey");
        System.out.println(dto.getKey());
        try{
            Token currentInDataBaseToken = dao.getByKey(dto.getKey());
            System.out.println("TokenService.canUseService : recuperated Token :"+currentInDataBaseToken.getTokenKey());
            System.out.println("TokenService.canUseService : timer : "+currentInDataBaseToken.getTimer());
            System.out.println("TokenService.canUseService : "+currentInDataBaseToken.isTimerGapValid());
            return currentInDataBaseToken.isTimerGapValid();
        }
        catch (NullPointerException e){
            return false;
        }
    }

    public void updateTokenTimer(TokenDTO dto) throws SQLException{
        System.out.println("TokenService.updateTokenTimer : updating timer of token "+dto.getKey());
        dto.setCurrentTimeNow();
        System.out.println("TokenService.updateTokenTimer : new timer value = "+dto.getCurrentTime());
        System.out.println("TokenService.updateTokenTimer : Launching DAO.update");
        dao.update(new Token(dto.getKey(), dto.getCurrentTime()));
    }

    public Token save(TokenDTO dto, String userPseudo) throws SQLException{
        System.out.println("TokenService.save : saving token ("+dto.getKey() +"; " +dto.getCurrentTime() +")");
        Token token = new Token(dto.getKey(), dto.getCurrentTime());
        if(dao.get(userPseudo) != null){
            System.out.println("TokenService.save : token successfully saved, returning token");
            dao.update(dao.get(userPseudo));
            return dao.get(userPseudo);
        }else {
            dao.save(token, userPseudo);
            System.out.println("TokenService.save : token successfully saved, returning token");
            return token;
        }
    }

    public boolean doesTokenExists(TokenDTO tokenDTO) throws SQLException {
        System.out.println("TokenService.doesTokenExists : token =" + tokenDTO.getKey() + ";" + tokenDTO.getCurrentTime());
        System.out.println(tokenDTO.getPseudo());
        System.out.println("TokenService.doesTokenExists : testing if tokens already exists");
        System.out.println(tokenDTO.getPseudo());
        try{
            if(dao.get(tokenDTO.getPseudo()) != null){
                System.out.println("TokenService.doesTokenExists : TRUE ( found by pseudo ) ");
                return true;
            }
            if (dao.getByKey(tokenDTO.getKey()) != null) {
                System.out.println("TokenService.doesTokenExists : TRUE ( found by key ) ");
                return true;
            } else {
                System.out.println("TokenService.doesTokenExists : returning FALSE");
                return false;
            }
        }catch(NullPointerException e){
            return false;
        }catch(SQLException f){
            return false;
        }
    }
    public void deleteToken(TokenDTO tokenDTO) throws SQLException {
        System.out.println("TokenService.deleteToken : deleting token " +tokenDTO.getKey());
        try{
            System.out.println("Par key");
            dao.deleteByPseudo(tokenDTO.getPseudo());

        }catch(SQLException e){
            try{
                System.out.println("Par pseudo");
                dao.deleteByKey(tokenDTO.getKey());

            }catch(SQLException f){
                throw new SQLException();
            }
        }

        System.out.println("TokenService.deleteToken : token deleted");
    }
}
