package br.max.ajuste;

import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;

import java.math.BigDecimal;

public class GravaNuNotaorig implements EventoProgramavelJava {
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
    DynamicVO varVO = (DynamicVO) persistenceEvent.getVo();
    JapeWrapper cabDAO = JapeFactory.dao("CabecalhoNota");
    DynamicVO cabVO = cabDAO.findOne("NUNOTA = ?",varVO.asBigDecimal("NUNOTAORIG"));
    DynamicVO cabVO1 = cabDAO.findOne("NUNOTA = ?",varVO.asBigDecimal("NUNOTA"));
    if(cabVO.asBigDecimal("CODTIPOPER").compareTo(BigDecimal.valueOf(3230)) == 0 || cabVO.asBigDecimal("CODTIPOPER").compareTo(BigDecimal.valueOf(2132)) == 0){
        cabDAO.prepareToUpdate(cabVO1).set("AD_NUNOTAORIGTROCSKU",cabVO.asBigDecimal("NUNOTA")).update();
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
