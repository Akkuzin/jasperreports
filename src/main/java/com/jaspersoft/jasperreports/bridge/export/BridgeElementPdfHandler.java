/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperreports.bridge.export;

import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.export.GenericElementPdfHandler;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterContext;

/**
 *
 * @author Giulio Toffoli (gtoffoli@tibco.com)
 */
public class BridgeElementPdfHandler implements GenericElementPdfHandler
{
	private static final BridgeElementPdfHandler INSTANCE = new BridgeElementPdfHandler();
	
	public static BridgeElementPdfHandler getInstance()
	{
		return INSTANCE;
	}
	
	public void exportElement(
		JRPdfExporterContext exporterContext,
		JRGenericPrintElement element
		)
	{
		try
		{
			JRPdfExporter exporter = (JRPdfExporter)exporterContext.getExporter();
                        JRPrintImage printImage = BridgeElementImageProvider.getDefaultProvider().getImage(exporterContext.getJasperReportsContext(), element, true);
                        exporter.exportImage(printImage);
		} catch (JRRuntimeException ex) {
                    throw ex;
                } catch (Exception ex) {
                    throw new JRRuntimeException(ex);
                }
	}

	public boolean toExport(JRGenericPrintElement element) {
		return true;
	}

}
