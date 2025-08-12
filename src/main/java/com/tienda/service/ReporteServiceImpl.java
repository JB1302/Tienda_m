package com.tienda.service.impl;

import com.tienda.service.ReporteService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReporteServiceImpl implements ReporteService {

    //Para poder acceder a la base de Datos y asi generar el reporte
    @Autowired
    DataSource datasource;
    
    public ResponseEntity<Resource> generaReporte(
            String reporte,
            Map<String, Object> parametros,
            String tipo) throws IOException {
        try {

            //Se define el tipo de respuesta
            //Si se vera o se descargara
            String estilo;
            if ("vPdf".equals(tipo)) {
                estilo = "inline; ";
            } else {
                estilo = "attachment; ";
            }

            //La ruta dentro de "default package"
            String reportePath = "reportes";
            //En salida quedarà el reporte ya generado...
            ByteArrayOutputStream salida = new ByteArrayOutputStream();

            //Se define el lugar y acceso al archivo .jasper
            ClassPathResource fuente
                    = new ClassPathResource(
                            reportePath
                            + File.separator
                            + reporte
                            + ".jasper");            

            //Un objeto para leer efectivamente el reporte
            InputStream elReporte = fuente.getInputStream();

            //Se crea el reporte como tal
            var reporteJasper
                    = JasperFillManager
                            .fillReport(
                                    elReporte,
                                    parametros,
                                    datasource.getConnection());
            //El objeto genera una respuesta HTML
            MediaType mediaType = null;
            //Genera el archivo de salida en Texto
            String archivoSalida = "";
            //Uilizado para responder al usuario el reporte
            byte[] data;
            
            //Determinación de qué tipo de reporte se desea.
            if (null != tipo) switch (tipo) {
                
                //Exportar PDF
                case "Pdf", "vPdf" -> {
                    JasperExportManager.exportReportToPdfStream(
                            reporteJasper, 
                            salida);
                    
                    mediaType = MediaType.APPLICATION_PDF;
                    archivoSalida = reporte + ".pdf";
                }
                //Exportal Excel
                case "Xls" -> {
                    JRXlsxExporter exportador = new JRXlsxExporter();
                    exportador.setExporterInput(
                            new SimpleExporterInput(
                                    reporteJasper));
                    exportador.setExporterOutput(
                            new SimpleOutputStreamExporterOutput(
                                    salida));
                    SimpleXlsxReportConfiguration configuracion=
                            new SimpleXlsxReportConfiguration();
                    configuracion.setDetectCellType(true);
                    configuracion.setCollapseRowSpan(true);
                    exportador.setConfiguration(configuracion);
                    exportador.exportReport();
                    mediaType = MediaType.APPLICATION_OCTET_STREAM;
                    archivoSalida = reporte + ".xlsx";
                }
                //Exportar CSV
                case "Csv" -> {
                    JRCsvExporter exportador = new JRCsvExporter();
                    exportador.setExporterInput(
                            new SimpleExporterInput(
                                    reporteJasper));
                    exportador.setExporterOutput(
                            new SimpleWriterExporterOutput(
                                    salida));
                    exportador.exportReport();
                    mediaType = MediaType.TEXT_PLAIN;
                    archivoSalida = reporte + ".csv";
                    }
                default -> {
                    }
            }

            //En data se convierte el reporte de cualquier tipo en un arreglo de Bytes
            data = salida.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition",
                    estilo + "filename=\"" + archivoSalida + "\"");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(data.length)
                    .contentType(mediaType)
                    .body(
                            new InputStreamResource(
                                    new ByteArrayInputStream(data)));

        } catch (SQLException | JRException e) {
            e.printStackTrace();
            return null;
        }

    }

}
