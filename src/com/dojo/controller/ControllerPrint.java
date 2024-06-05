package com.dojo.controller;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author Ando Henri
 */
public class ControllerPrint {
 
    public static void print(JComponent panel){
        
//        Dimension panelSize = panel.getSize();
//        
//        Paper paper = new Paper();
//        paper.setSize(panelSize.width, panelSize.height);
//        paper.setImageableArea(0, 0, panelSize.width, panelSize.height);
        
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("Imprimante");
        
//        PageFormat pageFormat = pj.defaultPage();
//        pageFormat.setOrientation(PageFormat.REVERSE_LANDSCAPE);
//        pageFormat.setPaper(paper);
        
        pj.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)graphics;
                g2.translate(pageFormat.getImageableX() * 2, pageFormat.getImageableY() * 2);
                g2.scale(0.8, 0.76);
                panel.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });

        boolean result = pj.printDialog();
        if (result){
            try {
                pj.print();
            } catch (PrinterException ex) {
                Logger.getLogger(ControllerPrint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
