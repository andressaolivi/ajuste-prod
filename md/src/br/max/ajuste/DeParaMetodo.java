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
import org.apache.poi.ss.formula.functions.Na;

import java.math.BigDecimal;
import java.sql.ResultSet;

public class DeParaMetodo implements EventoProgramavelJava {
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
        String jadlog = "JADLOG PACKAGE";
        String sedex = "CORREIOS SEDEX";
        String pac = "CORREIOS PAC";
        String transfolha = "TRANSFOLHA STANDARD";
        String dissudes = "DISSUDES STANDARD";
        String gfl = "GFL STANDARD";
        String total2 = "TOTAL EXPRESS";
        String total = "TOTAL STANDARD";
        String loggi = "LOGGI STANDARD";
        String loggi_ex = "LOGGI EXPRESS";

        DynamicVO cabVO = (DynamicVO) persistenceEvent.getVo();
        String Metodo = null;

        if (cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3200)) == 0
                || cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3220)) == 0
                || cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3221)) == 0
                || cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3222)) == 0
                || cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3223)) == 0
                || cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3224)) == 0
        ) {

            if (cabVO.asString("BH_METODO") != null) {
                Metodo = cabVO.asString("BH_METODO");
            }
            if (Metodo != null) {
                JapeWrapper venDAO = JapeFactory.dao("Vendedor");
                JapeWrapper cabDAO = JapeFactory.dao("CabecalhoNota");
                JapeWrapper parDAO = JapeFactory.dao("Parceiro");
                DynamicVO venVO = venDAO.findOne("CODVEND=?", cabVO.asBigDecimal("CODVEND"));

                if ("S".equals(venVO.asString("AD_USATRANSP"))) {

                    if ("JADLOG - JADLOG PACKAGE".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", jadlog);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", jadlog).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("JADLOG".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", jadlog);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", jadlog).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("CORREIOS - CORREIOS SEDEX".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", sedex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", sedex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("SEDEX".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", sedex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", sedex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("EXPEDITED".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", sedex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", sedex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("CORREIOS - CORREIOS PAC".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", pac);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", pac).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", pac);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", pac).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("ECONOMICO BRASIL".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", pac);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", pac).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("TRANSFOLHA - TRANSFOLHA STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", transfolha);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", transfolha).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("TRANSFOLHA".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", transfolha);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", transfolha).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("PREMIUM 1".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", dissudes);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", dissudes).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("DISSUDES - DISSUDES STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", dissudes);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", dissudes).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("GFL - GFL STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", gfl);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", gfl).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (gfl.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", gfl);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", gfl).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (dissudes.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", dissudes);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", dissudes).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (transfolha.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", transfolha);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", transfolha).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (pac.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", pac);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", pac).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (sedex.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", sedex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", sedex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (jadlog.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", jadlog);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", jadlog).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (loggi.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", loggi);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", loggi).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("LOGGI - LOGGI STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", loggi);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", loggi).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (loggi_ex.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", loggi_ex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", loggi_ex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("LOGGI - LOGGI EXPRESS".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", loggi_ex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", loggi_ex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("FROTA INTERNA STANDARD".equals(cabVO.asString("BH_METODO")) || "ENTREGA EXPRESS".equals(cabVO.asString("BH_METODO")) || "PROPRIO".equals(cabVO.asString("BH_METODO")) || "ENTREGA 24H UTEIS".equals(cabVO.asString("BH_METODO")) || "FROTA INTERNA - FROTA INTERNA STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("CODPARC=?", 10982);
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    }
                    else if (total.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", total);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", total).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    }
                    else if (total2.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", total2);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", total2).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    }
                    else if ("TOTAL - TOTAL STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", total);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", total).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    }
                    else if ("TOTAL - TOTAL EXPRESS".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", total2);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", total2).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    }
                }
            }
        }
    }

    @Override
    public void afterUpdate(PersistenceEvent persistenceEvent) throws Exception {
        String jadlog = "JADLOG PACKAGE";
        String sedex = "CORREIOS SEDEX";
        String pac = "CORREIOS PAC";
        String transfolha = "TRANSFOLHA STANDARD";
        String dissudes = "DISSUDES STANDARD";
        String gfl = "GFL STANDARD";
        String total2 = "TOTAL EXPRESS";
        String total = "TOTAL STANDARD";
        String loggi = "LOGGI STANDARD";
        String loggi_ex = "LOGGI EXPRESS";

        DynamicVO cabVO = (DynamicVO) persistenceEvent.getVo();
        String Metodo = null;

        if (cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3200)) == 0
                || cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3220)) == 0
                || cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3221)) == 0
                || cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3222)) == 0
                || cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3223)) == 0
                || cabVO.asBigDecimal("CODTIPOPER").compareTo(new BigDecimal(3224)) == 0
        ) {

            if (cabVO.asString("BH_METODO") != null) {
                Metodo = cabVO.asString("BH_METODO");
            }
            if (Metodo != null) {
                JapeWrapper venDAO = JapeFactory.dao("Vendedor");
                JapeWrapper cabDAO = JapeFactory.dao("CabecalhoNota");
                JapeWrapper parDAO = JapeFactory.dao("Parceiro");
                DynamicVO venVO = venDAO.findOne("CODVEND=?", cabVO.asBigDecimal("CODVEND"));

                if ("S".equals(venVO.asString("AD_USATRANSP"))) {

                    if ("JADLOG - JADLOG PACKAGE".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", jadlog);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", jadlog).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("JADLOG".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", jadlog);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", jadlog).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("CORREIOS - CORREIOS SEDEX".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", sedex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", sedex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (loggi.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", loggi);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", loggi).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("LOGGI - LOGGI STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", loggi);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", loggi).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (loggi_ex.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", loggi_ex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", loggi_ex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("LOGGI - LOGGI EXPRESS".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", loggi_ex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", loggi_ex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("SEDEX".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", sedex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", sedex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("EXPEDITED".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", sedex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", sedex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("CORREIOS - CORREIOS PAC".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", pac);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", pac).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", pac);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", pac).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("ECONOMICO BRASIL".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", pac);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", pac).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("TRANSFOLHA - TRANSFOLHA STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", transfolha);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", transfolha).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("TRANSFOLHA".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", transfolha);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", transfolha).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("PREMIUM 1".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", dissudes);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", dissudes).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("DISSUDES - DISSUDES STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", dissudes);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", dissudes).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("GFL - GFL STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", gfl);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", gfl).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (gfl.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", gfl);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", gfl).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (dissudes.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", dissudes);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", dissudes).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (transfolha.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", transfolha);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", transfolha).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (pac.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", pac);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", pac).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (sedex.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", sedex);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", sedex).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if (jadlog.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", jadlog);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", jadlog).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    } else if ("FROTA INTERNA STANDARD".equals(cabVO.asString("BH_METODO")) || "ENTREGA EXPRESS".equals(cabVO.asString("BH_METODO")) || "PROPRIO".equals(cabVO.asString("BH_METODO")) || "ENTREGA 24H UTEIS".equals(cabVO.asString("BH_METODO")) || "FROTA INTERNA - FROTA INTERNA STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("CODPARC=?", 10982);
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    }                    else if (total.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", total);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", total).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    }
                    else if (total2.equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", total2);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", total2).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    }
                    else if ("TOTAL - TOTAL STANDARD".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", total);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", total).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    }
                    else if ("TOTAL - TOTAL EXPRESS".equals(cabVO.asString("BH_METODO"))) {
                        DynamicVO parVO = parDAO.findOne("AD_METODOSDEENVIO=?", total2);
                        cabDAO.prepareToUpdate(cabVO).set("BH_METODO", total2).update();
                        cabDAO.prepareToUpdate(cabVO).set("CODPARCTRANSP", parVO.asBigDecimal("CODPARC")).update();
                    }
                }
            }
        }
    }

    @Override
    public void afterDelete(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext transactionContext) throws Exception {

    }
}
