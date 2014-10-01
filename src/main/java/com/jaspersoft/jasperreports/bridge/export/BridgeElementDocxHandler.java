/*
 * Copyright (C) 2005 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * Licensed under commercial Jaspersoft Subscription License Agreement
 */
package com.jaspersoft.jasperreports.bridge.export;

import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.export.JRExporterGridCell;
import net.sf.jasperreports.engine.export.ooxml.GenericElementDocxHandler;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Giulio Toffoli
 */
public class BridgeElementDocxHandler implements GenericElementDocxHandler {
	
        private static final BridgeElementDocxHandler INSTANCE = new BridgeElementDocxHandler();
	private static final Log log = LogFactory.getLog(BridgeElementDocxHandler.class);
	
        
        public static BridgeElementDocxHandler getInstance()
	{
		return INSTANCE;
	}
        
	@Override
	public boolean toExport(JRGenericPrintElement element) {
		return true;
	}

	@Override
	public void exportElement(JRDocxExporterContext exporterContext,
			JRGenericPrintElement element, JRExporterGridCell gridCell) {
		if (log.isDebugEnabled()) {
			log.debug("Exporting to DOCX " + element);
		}
		
		try {
			JRPrintImage chartImage = BridgeElementImageProvider.getDefaultProvider().getImage(exporterContext.getJasperReportsContext(), element, false);
                        JRDocxExporter exporter = (JRDocxExporter) exporterContext.getExporterRef();
			exporter.exportImage(exporterContext.getTableHelper(), chartImage, gridCell);
		} catch (Exception e) {
			throw new JRRuntimeException(e);
		}
	}

	
}
