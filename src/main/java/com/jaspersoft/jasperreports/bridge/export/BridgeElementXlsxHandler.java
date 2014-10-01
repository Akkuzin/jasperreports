/*
 * Copyright (C) 2005 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * Licensed under commercial Jaspersoft Subscription License Agreement
 */
package com.jaspersoft.jasperreports.bridge.export;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.export.JRExporterGridCell;
import net.sf.jasperreports.engine.export.ooxml.GenericElementXlsxHandler;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporterContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Giulio Toffoli (gtoffoli@tibco.com)
 */
public class BridgeElementXlsxHandler implements GenericElementXlsxHandler {
	
	private static final BridgeElementXlsxHandler INSTANCE = new BridgeElementXlsxHandler();
	private static final Log log = LogFactory.getLog(BridgeElementXlsxHandler.class);
	
        
        public static BridgeElementXlsxHandler getInstance()
	{
		return INSTANCE;
	}

        @Override
	public boolean toExport(JRGenericPrintElement element) {
		return true;
	}
        
	@Override
	public void exportElement(JRXlsxExporterContext exporterContext,
			JRGenericPrintElement element, JRExporterGridCell gridCell,
			int colIndex, int rowIndex) throws JRException {
		if (log.isDebugEnabled()) {
			log.debug("Exporting to XLSX " + element);
		}
		
		try {
			JRPrintImage chartImage = BridgeElementImageProvider.getDefaultProvider().getImage(exporterContext.getJasperReportsContext(), element, false);
			JRXlsxExporter exporter = (JRXlsxExporter) exporterContext.getExporter();
			exporter.exportImage(chartImage, gridCell, colIndex, rowIndex, 0, 0, null);// TODO lucianc is this OK?
		} catch (Exception e) {
			throw new JRRuntimeException(e);
		}
	}

        @Override
        public JRPrintImage getImage(JRXlsxExporterContext jrxec, JRGenericPrintElement jrgpe) throws JRException {
            return BridgeElementImageProvider.getDefaultProvider().getImage(jrxec.getJasperReportsContext(), jrgpe, true);
        }

	
}
