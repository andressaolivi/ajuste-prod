package br.max.ajuste;

import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

import java.math.BigDecimal;

public class TravaPrecoOrig implements EventoProgramavelJava {
    @Override
    public void beforeInsert(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeUpdate(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeDelete(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent persistenceEvent) throws Exception {
        JdbcWrapper jdbcWrapper = null;
        EntityFacade dwfEntityFacade = EntityFacadeFactory.getDWFFacade();
        jdbcWrapper = dwfEntityFacade.getJdbcWrapper();
        NativeSql sql = new NativeSql(jdbcWrapper);
        DynamicVO iteVO = (DynamicVO) persistenceEvent.getVo();
        JapeWrapper cabDAO = JapeFactory.dao("CabecalhoNota");
        DynamicVO cabVO = cabDAO.findOne("NUNOTA = ?",iteVO.asBigDecimal("NUNOTA"));
        if(cabVO.asBigDecimal("CODTIPOPER").compareTo(BigDecimal.valueOf(3230)) == 0 || cabVO.asBigDecimal("CODTIPOPER").compareTo(BigDecimal.valueOf(2132)) == 0) {
            BigDecimal nunotaotig= null;
            if (cabVO.asBigDecimal("AD_NUNOTAORIGTROCSKU") != null){
                nunotaotig = cabVO.asBigDecimal("AD_NUNOTAORIGTROCSKU");
            }
            if(nunotaotig != null) {
                sql.setNamedParameter("NUNOTAORIG", nunotaotig);
                sql.setNamedParameter("NUNOTA", cabVO.asBigDecimal("NUNOTA"));
                sql.executeUpdate("UPDATE TGFITE SET VLRUNIT = (SELECT VLRUNIT FROM TGFITE WHERE NUNOTA = :NUNOTAORIG), " +
                        "QTDNEG = (SELECT QTDNEG FROM TGFITE WHERE NUNOTA = :NUNOTAORIG)" +
                        ", VLRTOT = (SELECT VLRTOT FROM TGFITE WHERE NUNOTA = :NUNOTAORIG)" +
                        "WHERE NUNOTA = :NUNOTA");
            }
        }
    }

    @Override
    public void afterUpdate(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void afterDelete(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext transactionContext) throws Exception {

    }
}
