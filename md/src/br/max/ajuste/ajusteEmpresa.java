package br.max.ajuste;

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
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.ResultSet;

public class ajusteEmpresa implements EventoProgramavelJava {
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
        DynamicVO cabVO = (DynamicVO) persistenceEvent.getVo();
        if (cabVO.asString("TIPMOV").equals("P") && cabVO.asBigDecimal("CODVEND").compareTo(new BigDecimal(14)) == 0) {
            JdbcWrapper jdbcWrapper = null;
            EntityFacade dwfEntityFacade = EntityFacadeFactory.getDWFFacade();
            jdbcWrapper = dwfEntityFacade.getJdbcWrapper();
            JapeWrapper filaDAO = JapeFactory.dao("BHMKTFila");
            DynamicVO filaVO = filaDAO.findOne("BH_CODEMKT = ? AND CODSTATUS = 0", cabVO.asString("BH_CODMKT"));
            JSONObject obj = new JSONObject(filaVO.asString("RESPONSE"));
            JSONObject objFrete = obj.getJSONObject("frete");
            JSONArray objArray = objFrete.getJSONArray("centrosDistribuicao");
            int n = objArray.length();
            for (int i = 0; i < n; ++i) {
                JSONObject conteudo = objArray.getJSONObject(i);
                if(conteudo.getInt("centroDistribuicaoId") == 33){
                    NativeSql sql = new NativeSql(jdbcWrapper);
                    sql.setNamedParameter("NUNOTA", cabVO.asBigDecimal("NUNOTA"));
                    sql.executeUpdate("UPDATE TGFCAB SET CODEMP = 5, SERIENOTA = 1 WHERE NUNOTA = :NUNOTA");
                }
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
