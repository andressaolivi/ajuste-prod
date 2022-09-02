package br.max.ajuste;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

import java.util.Date;

public class criacaoProd implements EventoProgramavelJava {
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
        DynamicVO parVO = (DynamicVO) persistenceEvent.getVo();
        JdbcWrapper jdbcWrapper = null;
        EntityFacade dwfEntityFacade = EntityFacadeFactory.getDWFFacade();
        jdbcWrapper = dwfEntityFacade.getJdbcWrapper();
        NativeSql qryite = new NativeSql(jdbcWrapper);
        qryite.setNamedParameter("CODPROD", parVO.asBigDecimal("CODPROD"));
        qryite.executeUpdate("UPDATE TGFPRO SET AD_DTCADASTRO = SYSDATE WHERE CODPROD = :CODPROD");

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
