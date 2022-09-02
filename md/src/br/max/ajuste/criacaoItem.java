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

public class criacaoItem implements EventoProgramavelJava {
    @Override
    public void beforeInsert(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeUpdate(PersistenceEvent persistenceEvent) throws Exception {
        DynamicVO cabVO = (DynamicVO) persistenceEvent.getVo();

        if (new BigDecimal(3102).compareTo(cabVO.asBigDecimal("CODTIPOPER")) == 0) {

            Boolean confirmando = (Boolean) JapeSession.getProperty("CabecalhoNota.confirmando.nota");
            Boolean confirma = null;

            if (confirmando != null) {
                confirma = confirmando;
            }
            if (confirma != null) {

                JdbcWrapper jdbcWrapper = null;
                EntityFacade dwfEntityFacade = EntityFacadeFactory.getDWFFacade();
                jdbcWrapper = dwfEntityFacade.getJdbcWrapper();
                NativeSql query = new NativeSql(jdbcWrapper);
                query.setNamedParameter("NUNOTA", cabVO.asBigDecimal("NUNOTA"));
                query.executeUpdate("UPDATE TGFITE SET RESERVA = 'S',ATUALESTOQUE = 1 WHERE NUNOTA =:NUNOTA");

            }
        }
    }

    @Override
    public void beforeDelete(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent persistenceEvent) throws Exception {

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
