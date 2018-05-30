package com.dant.service;

import com.dant.database.TokenDAO;
import com.dant.entity.Token;
import com.dant.entity.dto.TokenDTO;

import java.sql.SQLException;

public class TokenService {

    private final TokenDAO dao = new TokenDAO();

    public boolean canUseService(TokenDTO dto) throws SQLException{
        Token currentInDataBaseToken = dao.getByKey(dto.getKey());
        return currentInDataBaseToken.isTimerGapValid();
    }

}
